package com.example.maven_jaxb_plugin;

import com.example.maven_jaxb_plugin.model.Persona;
import com.example.maven_jaxb_plugin.model.RootElement;
import com.example.maven_jaxb_plugin.util.CustomValidationEventHandler;
import com.example.maven_jaxb_plugin.util.XmlUtil;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class ExtractFieldPathxml {

    public static void main(String[] args) throws JAXBException {

        try {
            Persona persona = new Persona();
            persona.setNombre("Juan");
            persona.setEdad(30);

            String xml = XmlUtil.toXml(persona);
            System.out.println("XML:\n" + xml);

            Persona personaFromXml = XmlUtil.fromXml(xml, Persona.class);
            System.out.println("Nombre desde XML: " + personaFromXml.getNombre());
        } catch (JAXBException e) {
            e.printStackTrace();
        }


// Crear el contexto JAXB
      /*  JAXBContext jaxbContext = JAXBContext.newInstance(RootElement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
        unmarshaller.setEventHandler(validationEventHandler);

        // XML de ejemplo a deserializar
        String xmlString = "<rootElement xmlns=\"http://www.example.com/namespace\">" +
                "<element1>Value1</element1>" +
                "<element2>Value2</element2>" +
                "</rootElement>";

        StringReader reader = new StringReader(xmlString);
        RootElement rootElement = (RootElement) unmarshaller.unmarshal(reader);
    */
    }

}
