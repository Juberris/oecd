//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.cbc.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CbcMessageTypeIndic_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CbcMessageTypeIndic_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CBC401"/&gt;
 *     &lt;enumeration value="CBC402"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CbcMessageTypeIndic_EnumType")
@XmlEnum
public enum CbcMessageTypeIndicEnumType {


    /**
     * The message contains new information
     * 
     */
    @XmlEnumValue("CBC401")
    CBC_401("CBC401"),

    /**
     * The message contains corrections for previously sent information
     * 
     */
    @XmlEnumValue("CBC402")
    CBC_402("CBC402");
    private final String value;

    CbcMessageTypeIndicEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CbcMessageTypeIndicEnumType fromValue(String v) {
        for (CbcMessageTypeIndicEnumType c: CbcMessageTypeIndicEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
