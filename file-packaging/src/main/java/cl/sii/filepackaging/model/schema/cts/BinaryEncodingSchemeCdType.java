//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:24 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BinaryEncodingSchemeCdType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="BinaryEncodingSchemeCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="BASE64"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BinaryEncodingSchemeCdType")
@XmlEnum
public enum BinaryEncodingSchemeCdType {


    /**
     * No Special Encoding
     * 
     */
    NONE("NONE"),

    /**
     * Base64 Encoded
     * 
     */
    @XmlEnumValue("BASE64")
    BASE_64("BASE64");
    private final String value;

    BinaryEncodingSchemeCdType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BinaryEncodingSchemeCdType fromValue(String v) {
        for (BinaryEncodingSchemeCdType c: BinaryEncodingSchemeCdType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
