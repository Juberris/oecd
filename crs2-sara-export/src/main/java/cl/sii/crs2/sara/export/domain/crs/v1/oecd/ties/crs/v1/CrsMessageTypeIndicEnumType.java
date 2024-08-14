//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrsMessageTypeIndic_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CrsMessageTypeIndic_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CRS701"/&gt;
 *     &lt;enumeration value="CRS702"/&gt;
 *     &lt;enumeration value="CRS703"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CrsMessageTypeIndic_EnumType")
@XmlEnum
public enum CrsMessageTypeIndicEnumType {


    /**
     * The message contains new information
     * 
     */
    @XmlEnumValue("CRS701")
    CRS_701("CRS701"),

    /**
     * The message contains corrections for previously sent information
     * 
     */
    @XmlEnumValue("CRS702")
    CRS_702("CRS702"),

    /**
     * The message advises there is no data to report
     * 
     */
    @XmlEnumValue("CRS703")
    CRS_703("CRS703");
    private final String value;

    CrsMessageTypeIndicEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrsMessageTypeIndicEnumType fromValue(String v) {
        for (CrsMessageTypeIndicEnumType c: CrsMessageTypeIndicEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
