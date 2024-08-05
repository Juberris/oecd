package com.example.maven_jaxb_plugin.util;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;
import org.springframework.stereotype.Component;

@Component
public class CustomValidationEventHandler implements ValidationEventHandler {


    @Override
    public boolean handleEvent(ValidationEvent validationEvent) {
        ValidationEventLocator locator = validationEvent.getLocator();
        System.out.println("Field Path: " + getFieldPath(locator));
        System.out.println("Message: " + validationEvent.getMessage());
        return true; // true para continuar el unmarshalling, false para detenerlo
    }

    private String getFieldPath(ValidationEventLocator locator) {
        StringBuilder fieldPath = new StringBuilder();
        if (locator.getObject() != null) {
            fieldPath.append(locator.getObject().getClass().getSimpleName());
            if (locator.getNode() != null) {
                fieldPath.append(".").append(locator.getNode().getLocalName());
            }
        }
        return fieldPath.toString();
    }
}
