//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.24 a las 04:41:47 PM CLT 
//


package cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BinaryEncodingScheme_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="BinaryEncodingScheme_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NONE"/&gt;
 *     &lt;enumeration value="BASE64"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "BinaryEncodingScheme_EnumType")
@XmlEnum
public enum BinaryEncodingSchemeEnumType {


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

    BinaryEncodingSchemeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BinaryEncodingSchemeEnumType fromValue(String v) {
        for (BinaryEncodingSchemeEnumType c: BinaryEncodingSchemeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
