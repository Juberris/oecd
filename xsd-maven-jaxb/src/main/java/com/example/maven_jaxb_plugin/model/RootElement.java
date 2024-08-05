package com.example.maven_jaxb_plugin.model;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rootElement", namespace = "http://www.example.com/namespace")
@XmlType(propOrder = {"element1", "element2"})
public class RootElement {
    private String element1;
    private String element2;

    @XmlElement(namespace = "http://www.example.com/namespace")
    public String getElement1() {
        return element1;
    }

    public void setElement1(String element1) {
        this.element1 = element1;
    }

    @XmlElement(namespace = "http://www.example.com/namespace")
    public String getElement2() {
        return element2;
    }

    public void setElement2(String element2) {
        this.element2 = element2;
    }
}
