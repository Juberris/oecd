//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.fatca.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FatcaAcctPoolReportType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="FatcaAcctPoolReportType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FATCA201"/&gt;
 *     &lt;enumeration value="FATCA202"/&gt;
 *     &lt;enumeration value="FATCA203"/&gt;
 *     &lt;enumeration value="FATCA204"/&gt;
 *     &lt;enumeration value="FATCA205"/&gt;
 *     &lt;enumeration value="FATCA206"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FatcaAcctPoolReportType_EnumType")
@XmlEnum
public enum FatcaAcctPoolReportTypeEnumType {


    /**
     * Recalcitrant account holders with US Indicia
     * 
     */
    @XmlEnumValue("FATCA201")
    FATCA_201("FATCA201"),

    /**
     * Recalcitrant account holders without US Indicia
     * 
     */
    @XmlEnumValue("FATCA202")
    FATCA_202("FATCA202"),

    /**
     * Dormant accounts
     * 
     */
    @XmlEnumValue("FATCA203")
    FATCA_203("FATCA203"),

    /**
     * Non-participating foreign financial institutions
     * 
     */
    @XmlEnumValue("FATCA204")
    FATCA_204("FATCA204"),

    /**
     * Recalcitrant account holders that are US persons
     * 
     */
    @XmlEnumValue("FATCA205")
    FATCA_205("FATCA205"),

    /**
     * Recalcitrant account holders that are passive NFFEs
     * 
     */
    @XmlEnumValue("FATCA206")
    FATCA_206("FATCA206");
    private final String value;

    FatcaAcctPoolReportTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FatcaAcctPoolReportTypeEnumType fromValue(String v) {
        for (FatcaAcctPoolReportTypeEnumType c: FatcaAcctPoolReportTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
