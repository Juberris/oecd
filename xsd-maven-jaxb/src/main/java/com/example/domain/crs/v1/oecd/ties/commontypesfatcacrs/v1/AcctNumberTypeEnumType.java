//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AcctNumberType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="AcctNumberType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OECD601"/&gt;
 *     &lt;enumeration value="OECD602"/&gt;
 *     &lt;enumeration value="OECD603"/&gt;
 *     &lt;enumeration value="OECD604"/&gt;
 *     &lt;enumeration value="OECD605"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AcctNumberType_EnumType")
@XmlEnum
public enum AcctNumberTypeEnumType {


    /**
     * IBAN
     * 
     */
    @XmlEnumValue("OECD601")
    OECD_601("OECD601"),

    /**
     * OBAN
     * 
     */
    @XmlEnumValue("OECD602")
    OECD_602("OECD602"),

    /**
     * ISIN
     * 
     */
    @XmlEnumValue("OECD603")
    OECD_603("OECD603"),

    /**
     * OSIN
     * 
     */
    @XmlEnumValue("OECD604")
    OECD_604("OECD604"),

    /**
     * Other
     * 
     */
    @XmlEnumValue("OECD605")
    OECD_605("OECD605");
    private final String value;

    AcctNumberTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AcctNumberTypeEnumType fromValue(String v) {
        for (AcctNumberTypeEnumType c: AcctNumberTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
