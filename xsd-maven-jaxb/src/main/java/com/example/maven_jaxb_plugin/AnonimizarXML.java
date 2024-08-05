package com.example.maven_jaxb_plugin;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AnonimizarXML {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        String fileName="xml/STF_Ficticio.stf";
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();

        Path tempFile = Files.createTempFile("temp-", ".xml");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(tempFile.toFile());
        doc.getDocumentElement().normalize();

        String[] sensitiveTags = {"AddressFree", "NameFree", "Street", "PartyId"};
        for (String tag : sensitiveTags) {
            NodeList nodeList = doc.getElementsByTagName(tag);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                element.setTextContent("XXXXXXXX");
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(tempFile.toFile());
        transformer.transform(source, result);

        // Leer el archivo temporal anonimizadp
        String anonimizedXml = new String(Files.readAllBytes(tempFile));

        System.out.println(anonimizedXml);
    }
}
