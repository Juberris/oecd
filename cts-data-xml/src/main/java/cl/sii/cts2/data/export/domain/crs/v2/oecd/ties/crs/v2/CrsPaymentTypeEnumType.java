//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrsPaymentType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CrsPaymentType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CRS501"/&gt;
 *     &lt;enumeration value="CRS502"/&gt;
 *     &lt;enumeration value="CRS503"/&gt;
 *     &lt;enumeration value="CRS504"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CrsPaymentType_EnumType")
@XmlEnum
public enum CrsPaymentTypeEnumType {


    /**
     * Dividends
     * 
     */
    @XmlEnumValue("CRS501")
    CRS_501("CRS501"),

    /**
     * Interest
     * 
     */
    @XmlEnumValue("CRS502")
    CRS_502("CRS502"),

    /**
     * Gross Proceeds/Redemptions
     * 
     */
    @XmlEnumValue("CRS503")
    CRS_503("CRS503"),

    /**
     * Other - CRS
     * 
     */
    @XmlEnumValue("CRS504")
    CRS_504("CRS504");
    private final String value;

    CrsPaymentTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrsPaymentTypeEnumType fromValue(String v) {
        for (CrsPaymentTypeEnumType c: CrsPaymentTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
