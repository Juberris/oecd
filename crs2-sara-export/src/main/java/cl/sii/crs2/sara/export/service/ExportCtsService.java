package cl.sii.crs2.sara.export.service;

import cl.sii.crs2.sara.export.service.exportDB.CrsExportService;
import cl.sii.crs2.sara.export.util.Cts2ExportScript;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import static cl.sii.crs2.sara.export.util.Xml.read;

@Service
@Slf4j
public class ExportCtsService {
    private final JdbcTemplate jdbcTemplate;

    @Value("${crs.export.data.dir}")
    private String dataDir;

    private Path rootLocation;

    @Autowired
    CrsExportService exportService;


    public ExportCtsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void setRootLocation() {
        Path payloadPath = Paths.get(dataDir + "//payloads-gen");
      //  boolean result= FileUtils.deleteQuietly(inboxPath.toFile());

        if (!Files.exists(payloadPath)) {
            try {
                Files.createDirectories(payloadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.rootLocation = payloadPath;
    }

    public void process(){
        this.setRootLocation();
        initDataBase();
        File dir = null;
        String actualFile = "";
        try {
            dir = new File(String.valueOf(this.rootLocation)).getCanonicalFile();
            FileFilter fileFilter = new WildcardFileFilter(new String[]{"*.xml"});
            File[] files = dir.listFiles(fileFilter);
            int count = files != null ? files.length : 0;
            int total=count;
            for (final File fileEntry : Objects.requireNonNull(dir.listFiles(fileFilter))){
                actualFile=fileEntry.getName();
                log.info(String.format("Se encontro archivo: %s ", actualFile));
                File file = new File(fileEntry.getAbsolutePath());
                byte[]  fileBytes=convertFileToByteArray(new File(fileEntry.getAbsolutePath()));
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setNamespaceAware(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(inputStream);

                    XPathFactory xPathFactory = XPathFactory.newInstance();
                    XPath xpath = xPathFactory.newXPath();
                    XPathExpression expr = xpath.compile("//*[local-name()='CRS_OECD']");
                    document.getDocumentElement().normalize();
                    NodeList crsNodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
                    String crsContent = "";
                    for (int i = 0; i < crsNodes.getLength(); i++) {
                        Node crsNode = crsNodes.item(i);
                        crsContent = nodeToString(crsNode);
                    }
                    // byte[] pyld = convertFileToByteArray(new File(fileEntry.getAbsolutePath()));
                    //ByteArrayOutputStream Bpyld =getOECDPyld(pyld,actualFile);
                    InputStream payload = new ByteArrayInputStream(crsContent.getBytes());

                procesarPayload(payload,"CRS",actualFile);
                }catch (SAXException | ParserConfigurationException | XPathExpressionException e) {
                    log.warn("Error en la lectura de archivos :{} == {}", actualFile, e.getMessage());
                }
                System.out.println("Faltan "+ count-- + " de " + total );
            }


            System.out.println("GUARDANDO EN BASE DE DATOS");
            exportService.saveAll();

            log.info("########## PROCESO FINALIZADO #########");

        } catch (Exception e) {
            log.info("Error en la lectura de archivos :{}", e.getMessage());
        }
    }

    private  String nodeToString(Node node) throws Exception {
        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(node);
        java.io.StringWriter stringWriter = new java.io.StringWriter();
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(stringWriter);
        transformer.transform(source, result);
        return stringWriter.getBuffer().toString(); // Devuelve el nodo con sus tags
    }

    public void procesarPayload(InputStream xmlInputStream, String type, String filename ){
        // 1. Convertir InputStream a Document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlInputStream);

            // 2. Configurar el Transformer para formatear la salida
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // 3. Convertir Document a String
            StringWriter writer = new StringWriter();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            // Imprimir el contenido XML formateado
            //System.out.println(writer.toString());

            String contextPath=getContextPath(document,type);
            // Normalizar el documento XML (opcional, pero recomendado)
            document.getDocumentElement().normalize();
            JAXBContext context = JAXBContext.newInstance(contextPath);
            xmlInputStream.reset();
            Unmarshaller unmarshaller = context.createUnmarshaller();
            if (type.equals("CRS")){
                Object crsoecd= unmarshaller.unmarshal(xmlInputStream);
                String version=contextPath.contains("v1")?"v1":"v2";
                exportService.initCrsExportService(crsoecd,version, filename);
                exportService.process();
            }


        } catch (SAXParseException | JAXBException p){
            log.warn("Archivo mal formado...");
        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
           log.error(e.getMessage());
        }

    }


    String getContextPath(Document document, String type){
        String outContextPath="";
        Element rootElement = document.getDocumentElement();
        NodeList nodeList = document.getElementsByTagName("*");
        String element="";
        if(type.equals("CRS")){
            String targetValue  = "urn:oecd:ties:crs:";
            element=hasAttributeWithValue(nodeList, targetValue);
            if(element.contains("v1")){
                outContextPath="cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1";
            }else{
                outContextPath="cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2";
            }
        } else if (type.equals("CBC")) {
            String targetValue  = "urn:oecd:ties:cbc:";
            element=hasAttributeWithValue(nodeList, targetValue);

            if(element.contains("v1")){
                outContextPath="cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.cbc.v1";
            }else{
                outContextPath="cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.cbc.v2";
            }

        }
        return outContextPath;
    }

    void initDataBase(){
        jdbcTemplate.execute("DELETE FROM ACCOUNTHOLDER_NATIONALY");
        jdbcTemplate.execute("DELETE FROM ACCOUNTHOLDER_RCC");
        jdbcTemplate.execute("DELETE FROM ACCOUNTHOLDER_TIN");
        jdbcTemplate.execute("DELETE FROM MESSAGE_CORRMESSAGE");
        jdbcTemplate.execute("DELETE FROM MESSAGE_SPEC");
        jdbcTemplate.execute("DELETE FROM CRS_ADDRESS");
        jdbcTemplate.execute("DELETE FROM CRS_REPORTING_FI");
        jdbcTemplate.execute("DELETE FROM CRS_PAYMENT");
        jdbcTemplate.execute("DELETE FROM CRS_CONTROLLING_PERSON");
        jdbcTemplate.execute("DELETE FROM CRS_ACCOUNT_REPORT");
        jdbcTemplate.execute("DELETE FROM CRS_ACCOUNT_HOLDER");


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

    byte[] convertFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            // Leer el archivo y escribirlo en el ByteArrayOutputStream
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }

 private ByteArrayOutputStream getOECDPyld(byte[] pyldSign, String filename){
     try (ByteArrayOutputStream data = new ByteArrayOutputStream()) {
         Cts2ExportScript.modify(new OutputStreamWriter(data), new Cts2ExportScript.XmlMod() {
             @Override
             public boolean include(String path) {
                 return path.contains("_OECD");
             }
         }, l -> {

             try {
                 read(new BOMInputStream(new ByteArrayInputStream(pyldSign)), l);
             } catch (Exception e) {
                 log.error("ERROR FILE==> {} ", filename);
             }
         });
        return data;
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
 }
}
