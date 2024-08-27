package cl.sii.crs2.sara.export.service;

import cl.sii.crs2.sara.export.client.SaraClient;
import cl.sii.crs2.sara.export.entities.CountryInfo;
import cl.sii.crs2.sara.export.model.SubmissionRequest;
import cl.sii.crs2.sara.export.model.SubmissionResponse;
import cl.sii.crs2.sara.export.repository.crs.CrsAccountRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsControllingPersonRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsFIRepository;
import cl.sii.crs2.sara.export.repository.crs.CrsPaymentRepository;
import cl.sii.crs2.sara.export.util.Cts2ExportScript;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jakarta.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static cl.sii.crs2.sara.export.util.Xml.read;


@Service
@Slf4j
public class ExportService {
    @Autowired
    SaraClient client;

    @Autowired
    CRSOECD1 crsoecd1;

    @Autowired
    CRSOECD2 crsoecd2;

    @Value("${cl.sii.sara.crs.api.uri}")
    String url;


    @Value("${crs.export.data.dir}")
    private String dataDir;

    @Value("${dir.main}")
    String pathMain;

    @Autowired
    JsonCountryInfoService jsonFileService;

    @Autowired
    CrsAccountService crsAccountService;

    @Autowired
    CsvService csvService;

    @Autowired
    CrsAccountRepository accountRepository;
    @Autowired
    CrsControllingPersonRepository controllingPersonRepository;
    @Autowired
    CrsPaymentRepository paymentRepository;
    @Autowired
    CrsFIRepository fiRepository;

    private Path rootLocation;
    private File inboxDir;
    private Boolean isError;

      private void setRootLocation() {
        Path inboxPath = Paths.get(dataDir + "//payloads");
        boolean result= FileUtils.deleteQuietly(inboxPath.toFile());

        if (!Files.exists(inboxPath)) {
            try {
                Files.createDirectories(inboxPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.rootLocation = inboxPath;
    }


    @Transactional
    public void updateRelationships(){
        Map<String, CountryInfo> countryInfoMap =null;
        CtsExchangeRelationship r=new CtsExchangeRelationship(pathMain);
        try {
            r.loadExchangeRelationships(true);
            countryInfoMap =jsonFileService.readJsonFile(pathMain+"/exchange_relationships.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(countryInfoMap !=null){
            jsonFileService.saveCountryInfo(countryInfoMap);
            System.out.println("Data saved successfully!" );
        }
    }

    public List<CountryInfo> getCountryInfo(){
          return jsonFileService.listCountryInfo();
    }

    public void getSaraSubmissions(String from, String to) {
        setRootLocation();

        SubmissionRequest req = obtenerRequest(from, to);
        AtomicReference<Integer> nPayloads = new AtomicReference<>(0);
        AtomicReference<Integer> currentPayload = new AtomicReference<>(0);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonRequest = objectMapper.writeValueAsString(req);

            Map map = objectMapper.readValue(jsonRequest, Map.class);
            SubmissionResponse submissionResponse = client.getSubmission(map);
            nPayloads.set((int) submissionResponse.getData().stream().filter(i -> i.getStatus() != null)
                    .filter(i -> i.getStatus().equals("ACCEPTED")).count());

            submissionResponse.getData().stream().filter(i -> i.getStatus() != null)
                    .filter(i -> i.getStatus().equals("ACCEPTED"))
                    .forEach(i -> {
                        //inboxDir = new File(dataDir);
                        this.isError = false;
                        currentPayload.getAndUpdate(value -> value + 1);
                        currentPayload.set(currentPayload.get());

                        log.info("Descargando==> {} - {}", i.getSenderId(), i.getDataPayloadId());
//if( i.getDataPayloadId().equals("f96f1ad7-59ef-4149-9b47-fc6fa2222e77")) {
                        String payloadResponse = client.getPayload(i.getSenderId(), i.getSubjectId(), i.getReceptionId(), i.getDataPayloadId(), i.getPeriod());

                        try (ByteArrayOutputStream data = new ByteArrayOutputStream()) {
                            Cts2ExportScript.modify(new OutputStreamWriter(data), new Cts2ExportScript.XmlMod() {
                                @Override
                                public boolean include(String path) {
                                    return path.contains("_OECD");
                                }
                            }, l -> {

                                try {
                                    read(new BOMInputStream(new ByteArrayInputStream(payloadResponse.getBytes())), l);
                                } catch (Exception e) {
                                    log.error("ERROR FILE==> {} - {}", i.getSenderId(), i.getDataPayloadId());
                                    this.isError = true;
                                    File payFile = this.store(payloadResponse.getBytes(), "ERR-" + i.getSenderId() + "-" + i.getDataPayloadId() + ".xml");

                                }
                            });

                            if (!this.isError) {
                                File payFile = this.store(data.toByteArray(), i.getSenderId() + "-" + i.getDataPayloadId() + ".xml");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
//}
                        log.info("Faltan :{}", nPayloads.get() - currentPayload.get());
                    });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

public void process(){

    this.rootLocation=Paths.get(dataDir + "//payloads");
    initDataBase();
    File dir = null;
    String actualFile = "";
    try {
        dir = new File(String.valueOf(this.rootLocation)).getCanonicalFile();
        FileFilter fileFilter = new WildcardFileFilter(new String[]{"*.xml"});
        File[] files = dir.listFiles(fileFilter);
        int count = files != null ? files.length : 0;
        Integer v1=0;
        Integer v2=0;
        for (final File fileEntry : Objects.requireNonNull(dir.listFiles(fileFilter))){
            actualFile=fileEntry.getName();
            log.info(String.format("Se encontro archivo: %s ", actualFile));

            try {

                String contextPath=getVersion(fileEntry.getAbsolutePath());
                JAXBContext context = JAXBContext.newInstance(contextPath);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                File xmlFile = new File(fileEntry.getAbsolutePath());

                if (contextPath.contains("v1")){
                    v1++;
                    crsoecd1.process(unmarshaller,xmlFile);
                }else{
                    v2++;
                    crsoecd2.process(unmarshaller,xmlFile);
                }
            }catch (ParserConfigurationException | TransformerException | SAXException | JAXBException e) {
                log.error("Error en parseo xml, archivo ==> {} - {}", actualFile, e.getMessage());
            }
            System.out.println("Faltan "+ count--);
        }

        crsAccountService.updateAgreement();
        log.info("Cuenta version 1: {}", v1);
        log.info("Cuenta version 2: {}", v2);
        csvService.saveCsv();
        log.info("========= END =========");
    } catch (IOException e) {
        log.info("Error en la lectura de archivos :{}", e.getMessage());
    }
}

    String getVersion(String pathXml) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        String outContextPath="cl.sii.crs2.sara.export.domain.crs.v2.gen.";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(pathXml);
        NodeList nodeList = document.getElementsByTagName("*");
        String targetValue  = "urn:oecd:ties:crs:";
        String element=hasAttributeWithValue(nodeList, targetValue);

        if (element.contains("v1")){
            outContextPath="cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1";
        }else{
            outContextPath="cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2";
        }

        return outContextPath;

    }

    /**
     *
      * @param from yyyy-mm-dd
     * @param to yyyy-mm-dd
     * @return  request REST
     */
    SubmissionRequest obtenerRequest(String from, String to) {
        SubmissionRequest req = new SubmissionRequest();
        req.getSender().setSubjectId("s12485811");
        req.setPayloadType("CRS2_DOM");
        req.setFrom(from + "T00:00:00.000Z");
        req.setTo(to + "T23:59:59.999Z");
        req.setLast(false);
        return req;
    }


    File store(byte[] fileBytes, String filename) {
        String pathFilename = filename;

        if (this.isError) {
            Path errorPath = Paths.get(this.rootLocation + "//ERROR");
            pathFilename = "ERROR//" + filename;
            if (!Files.exists(errorPath)) {
                try {
                    Files.createDirectories(errorPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {

            Path filePath = this.rootLocation.resolve(pathFilename);
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(fileBytes);
            }
            return filePath.toFile();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    private static String hasAttributeWithValue(NodeList nodeList, String value) {
        // Iterar sobre los elementos y buscar el valor en los atributos
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) node;
                for (int h = 0; h < nodeElement.getAttributes().getLength(); h++) {
                    if (nodeElement.getAttributes().item(h).getNodeValue().contains(value)) {
                        if (nodeElement.getAttributes().item(h).getNodeValue().length()<25) {
                            return nodeElement.getAttributes().item(h).getNodeValue();
                        }
                    }
                }
            }
        }

        return "";
    }

    void initDataBase(){
       accountRepository.deleteAll();
       controllingPersonRepository.deleteAll();
       paymentRepository.deleteAll();
       fiRepository.deleteAll();
    }
}
