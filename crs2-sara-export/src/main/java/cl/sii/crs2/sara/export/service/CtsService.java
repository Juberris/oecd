package cl.sii.crs2.sara.export.service;

import cl.sii.crs2.sara.export.entities.cts.Cts2Cache;
import cl.sii.crs2.sara.export.repository.cts.Cts2CacheRepository;
import cl.sii.crs2.sara.export.repository.cts.Cts2DPRepository;
import cl.sii.crs2.sara.export.service.exportDB.CRSOECD1_Export;
import cl.sii.crs2.sara.export.service.exportDB.CRSOECD2_Export;
import cl.sii.crs2.sara.export.service.exportDB.CrsExportService;
import cl.sii.crs2.sara.export.util.PackageZipHandler;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.InflaterInputStream;

@Service
@Slf4j
public class CtsService {

    @Autowired
    Cts2DPRepository cts2DPRepository;

    @Autowired
    Cts2CacheRepository cts2CacheRepository;

    @Autowired
    CrsExportService exportService;


    @Autowired
    PackageZipHandler pzh;

    @Autowired
    CRSOECD1_Export crsoecd1;

    @Autowired
    CRSOECD2_Export crsoecd2;

    @Transactional
    public void getPayload(){

        // SE PREFIERE ADAPTAR EL REPOSITORY PARA NO USAR FILTER
        // cts2DPRepository.findAllByOrderByDatetimeAsc().stream().filter(c->c.getIsStatus()==0).forEach(cts2DP -> {


        AtomicInteger total=new AtomicInteger();
        total.set(cts2DPRepository.findAllByOrderByDatetimeAsc().size());
        AtomicInteger k= new AtomicInteger();
        cts2DPRepository.findAllByOrderByDatetimeAsc().forEach(cts2DP -> {

            cts2DPRepository.findByFilename(cts2DP.getFilename()).ifPresent(cts -> {
                System.out.println(cts.getFilename());
                String key=cts.getFilename().replace(".zip", "").concat("_pyldClr");
                Cts2Cache cache=cts2CacheRepository.findByKey(key).orElse(null);
                try {
                    byte[] bytes=null;
                    if (cache!=null) {
                         byte[] signedPyld=decompress(cache.getData());
                         bytes = pzh.getUnsignedPayload(signedPyld);

                    }else{
                        //IR A BUSCAR EL ARCHIVO ENCRIPTADO EN DISCO
                        pzh.getPayloadFile(cts);
                        if(pzh.getPyld()!=null) {
                            bytes = pzh.getPyld();
                        }
                    }

                    if (bytes!=null) {
                        InputStream payload = new ByteArrayInputStream(bytes);
                        procesarPayload(payload, cts.getType(), cts.getFilename());
                    }else{
                        log.warn("Unprocessable file {}", cts.getFilename());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error",e);
                }

            });
            k.getAndIncrement();
            int saldo= total.get() - k.get();
            System.out.println("Falta "+ saldo + " de " + total.get());
        });
        System.out.println("GUARDANDO EN BASE DE DATOS");
        exportService.saveAll();

        System.out.println("########## PROCESO FINALIZADO #########");
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
               if (contextPath.contains("v1")){
                   crsoecd1.process(unmarshaller,xmlInputStream, filename);
               }else{
                   crsoecd2.process(unmarshaller,xmlInputStream, filename);
               }
           }


       } catch (Exception e) {
           e.printStackTrace();
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
    public static byte[] decompress(byte[] bytes) throws Exception {
        try (InflaterInputStream iis = new InflaterInputStream(new ByteArrayInputStream(bytes))) {
            return IOUtils.toByteArray(iis);
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

}
