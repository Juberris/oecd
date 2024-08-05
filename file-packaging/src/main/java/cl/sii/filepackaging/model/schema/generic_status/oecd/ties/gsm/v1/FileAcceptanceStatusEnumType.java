//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FileAcceptanceStatus_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="FileAcceptanceStatus_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Accepted"/&gt;
 *     &lt;enumeration value="Rejected"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FileAcceptanceStatus_EnumType")
@XmlEnum
public enum FileAcceptanceStatusEnumType {


    /**
     * The file was accepted by the receiver
     * 
     */
    @XmlEnumValue("Accepted")
    ACCEPTED("Accepted"),

    /**
     * The file was rejected by the receiver
     * 
     */
    @XmlEnumValue("Rejected")
    REJECTED("Rejected");
    private final String value;

    FileAcceptanceStatusEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FileAcceptanceStatusEnumType fromValue(String v) {
        for (FileAcceptanceStatusEnumType c: FileAcceptanceStatusEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
