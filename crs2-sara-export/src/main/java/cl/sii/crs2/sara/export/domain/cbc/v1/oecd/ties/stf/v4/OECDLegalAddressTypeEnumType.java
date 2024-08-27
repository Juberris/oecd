//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.stf.v4;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OECDLegalAddressType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="OECDLegalAddressType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="OECD301"/&gt;
 *     &lt;enumeration value="OECD302"/&gt;
 *     &lt;enumeration value="OECD303"/&gt;
 *     &lt;enumeration value="OECD304"/&gt;
 *     &lt;enumeration value="OECD305"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OECDLegalAddressType_EnumType")
@XmlEnum
public enum OECDLegalAddressTypeEnumType {


    /**
     * residentialOrBusiness
     * 
     */
    @XmlEnumValue("OECD301")
    OECD_301("OECD301"),

    /**
     * residential
     * 
     */
    @XmlEnumValue("OECD302")
    OECD_302("OECD302"),

    /**
     * business
     * 
     */
    @XmlEnumValue("OECD303")
    OECD_303("OECD303"),

    /**
     * registeredOffice
     * 
     */
    @XmlEnumValue("OECD304")
    OECD_304("OECD304"),

    /**
     * unspecified
     * 
     */
    @XmlEnumValue("OECD305")
    OECD_305("OECD305");
    private final String value;

    OECDLegalAddressTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OECDLegalAddressTypeEnumType fromValue(String v) {
        for (OECDLegalAddressTypeEnumType c: OECDLegalAddressTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
