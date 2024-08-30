//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.csm.v1;

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
 *     &lt;enumeration value="CRSMessageStatus"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CrsMessageTypeIndic_EnumType")
@XmlEnum
public enum CrsMessageTypeIndicEnumType {

    @XmlEnumValue("CRSMessageStatus")
    CRS_MESSAGE_STATUS("CRSMessageStatus");
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
