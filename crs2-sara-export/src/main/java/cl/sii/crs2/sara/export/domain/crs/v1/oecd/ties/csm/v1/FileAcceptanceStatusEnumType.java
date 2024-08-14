//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.csm.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


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
