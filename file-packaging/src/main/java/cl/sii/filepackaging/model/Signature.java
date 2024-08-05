package cl.sii.filepackaging.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Signature {

    @XmlAttribute(name = "Id")
    private String id;

    @XmlElement(name = "SignedInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private SignedInfo signedInfo;

    @XmlElement(name = "SignatureValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private String signatureValue;

    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private KeyInfo keyInfo;

    @XmlElement(name = "Object", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private SignatureObject signatureObject;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class SignedInfo {

    @XmlElement(name = "CanonicalizationMethod", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private CanonicalizationMethod canonicalizationMethod;

    @XmlElement(name = "SignatureMethod", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private SignatureMethod signatureMethod;

    @XmlElement(name = "Reference", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private Reference reference;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class CanonicalizationMethod {

    @XmlAttribute(name = "Algorithm")
    private String algorithm;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class SignatureMethod {

    @XmlAttribute(name = "Algorithm")
    private String algorithm;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Reference {

    @XmlAttribute(name = "URI")
    private String uri;

    @XmlElement(name = "Transforms", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private Transforms transforms;

    @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private DigestMethod digestMethod;

    @XmlElement(name = "DigestValue", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private String digestValue;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Transforms {

    @XmlElement(name = "Transform", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private List<Transform> transformList;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Transform {

    @XmlAttribute(name = "Algorithm")
    private String algorithm;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class DigestMethod {

    @XmlAttribute(name = "Algorithm")
    private String algorithm;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class KeyInfo {

    @XmlElement(name = "X509Data", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private X509Data x509Data;

    // Getters y setters
    // ...
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class X509Data {

    @XmlElement(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private String x509Certificate;

    // Getters y setters
    // ...
}

