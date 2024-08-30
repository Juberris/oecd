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
 * <p>Clase Java para UltimateParentEntityRole_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="UltimateParentEntityRole_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CBC801"/&gt;
 *     &lt;enumeration value="CBC802"/&gt;
 *     &lt;enumeration value="CBC803"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UltimateParentEntityRole_EnumType")
@XmlEnum
public enum UltimateParentEntityRoleEnumType {


    /**
     * Ultimate Parent Entity
     * 
     */
    @XmlEnumValue("CBC801")
    CBC_801("CBC801"),

    /**
     * Reporting Entity
     * 
     */
    @XmlEnumValue("CBC802")
    CBC_802("CBC802"),

    /**
     * Both (Ultimate Parent Entity and Reporting Entity)
     * 
     */
    @XmlEnumValue("CBC803")
    CBC_803("CBC803");
    private final String value;

    UltimateParentEntityRoleEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UltimateParentEntityRoleEnumType fromValue(String v) {
        for (UltimateParentEntityRoleEnumType c: UltimateParentEntityRoleEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
