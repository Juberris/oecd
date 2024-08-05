package cl.sii.filepackaging.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureObject {

    @XmlAttribute(name = "Id")
    private String id;

    @XmlValue
    private String content;

    // Getters y setters
    // ...
}
