//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.stf.v4;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OECDDocTypeIndic_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="OECDDocTypeIndic_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OECD0"/&gt;
 *     &lt;enumeration value="OECD1"/&gt;
 *     &lt;enumeration value="OECD2"/&gt;
 *     &lt;enumeration value="OECD3"/&gt;
 *     &lt;enumeration value="OECD10"/&gt;
 *     &lt;enumeration value="OECD11"/&gt;
 *     &lt;enumeration value="OECD12"/&gt;
 *     &lt;enumeration value="OECD13"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OECDDocTypeIndic_EnumType")
@XmlEnum
public enum OECDDocTypeIndicEnumType {


    /**
     * Resend Data
     * 
     */
    @XmlEnumValue("OECD0")
    OECD_0("OECD0"),

    /**
     * New Data
     * 
     */
    @XmlEnumValue("OECD1")
    OECD_1("OECD1"),

    /**
     * Corrected Data
     * 
     */
    @XmlEnumValue("OECD2")
    OECD_2("OECD2"),

    /**
     * Deletion of Data
     * 
     */
    @XmlEnumValue("OECD3")
    OECD_3("OECD3"),

    /**
     * Resend Test Data
     * 
     */
    @XmlEnumValue("OECD10")
    OECD_10("OECD10"),

    /**
     * New Test Data
     * 
     */
    @XmlEnumValue("OECD11")
    OECD_11("OECD11"),

    /**
     * Corrected Test Data
     * 
     */
    @XmlEnumValue("OECD12")
    OECD_12("OECD12"),

    /**
     * Deletion of Test Data
     * 
     */
    @XmlEnumValue("OECD13")
    OECD_13("OECD13");
    private final String value;

    OECDDocTypeIndicEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OECDDocTypeIndicEnumType fromValue(String v) {
        for (OECDDocTypeIndicEnumType c: OECDDocTypeIndicEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
