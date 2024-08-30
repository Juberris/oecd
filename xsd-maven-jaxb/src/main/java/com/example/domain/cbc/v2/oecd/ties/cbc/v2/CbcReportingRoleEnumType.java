//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package com.example.domain.cbc.v2.oecd.ties.cbc.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CbcReportingRole_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CbcReportingRole_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CBC701"/&gt;
 *     &lt;enumeration value="CBC702"/&gt;
 *     &lt;enumeration value="CBC703"/&gt;
 *     &lt;enumeration value="CBC704"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CbcReportingRole_EnumType")
@XmlEnum
public enum CbcReportingRoleEnumType {


    /**
     * Ultimate Parent Entity
     * 
     */
    @XmlEnumValue("CBC701")
    CBC_701("CBC701"),

    /**
     * Surrogate Parent Entity
     * 
     */
    @XmlEnumValue("CBC702")
    CBC_702("CBC702"),

    /**
     * Local Filing in the framework of an international exchange
     * 
     */
    @XmlEnumValue("CBC703")
    CBC_703("CBC703"),

    /**
     * Local Filing with Incomplete Information
     * 
     */
    @XmlEnumValue("CBC704")
    CBC_704("CBC704");
    private final String value;

    CbcReportingRoleEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CbcReportingRoleEnumType fromValue(String v) {
        for (CbcReportingRoleEnumType c: CbcReportingRoleEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
