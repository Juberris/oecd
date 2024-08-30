//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package com.example.domain.cbc.v1.oecd.ties.cbc.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CbcBizActivityType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CbcBizActivityType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CBC501"/&gt;
 *     &lt;enumeration value="CBC502"/&gt;
 *     &lt;enumeration value="CBC503"/&gt;
 *     &lt;enumeration value="CBC504"/&gt;
 *     &lt;enumeration value="CBC505"/&gt;
 *     &lt;enumeration value="CBC506"/&gt;
 *     &lt;enumeration value="CBC507"/&gt;
 *     &lt;enumeration value="CBC508"/&gt;
 *     &lt;enumeration value="CBC509"/&gt;
 *     &lt;enumeration value="CBC510"/&gt;
 *     &lt;enumeration value="CBC511"/&gt;
 *     &lt;enumeration value="CBC512"/&gt;
 *     &lt;enumeration value="CBC513"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CbcBizActivityType_EnumType")
@XmlEnum
public enum CbcBizActivityTypeEnumType {


    /**
     * Research and Development
     * 
     */
    @XmlEnumValue("CBC501")
    CBC_501("CBC501"),

    /**
     * Holding or Managing intellectual property
     * 
     */
    @XmlEnumValue("CBC502")
    CBC_502("CBC502"),

    /**
     * Purchasing or Procurement
     * 
     */
    @XmlEnumValue("CBC503")
    CBC_503("CBC503"),

    /**
     * Manufacturing or Production
     * 
     */
    @XmlEnumValue("CBC504")
    CBC_504("CBC504"),

    /**
     * Sales, Marketing or Distribution
     * 
     */
    @XmlEnumValue("CBC505")
    CBC_505("CBC505"),

    /**
     * Administrative, Management or Support Services
     * 
     */
    @XmlEnumValue("CBC506")
    CBC_506("CBC506"),

    /**
     * Provision of Services to unrelated parties
     * 
     */
    @XmlEnumValue("CBC507")
    CBC_507("CBC507"),

    /**
     * Internal Group Finance
     * 
     */
    @XmlEnumValue("CBC508")
    CBC_508("CBC508"),

    /**
     * Regulated Financial Services
     * 
     */
    @XmlEnumValue("CBC509")
    CBC_509("CBC509"),

    /**
     * Insurance
     * 
     */
    @XmlEnumValue("CBC510")
    CBC_510("CBC510"),

    /**
     * Holding shares or other equity instruments
     * 
     */
    @XmlEnumValue("CBC511")
    CBC_511("CBC511"),

    /**
     * Dormant
     * 
     */
    @XmlEnumValue("CBC512")
    CBC_512("CBC512"),

    /**
     * Other
     * 
     */
    @XmlEnumValue("CBC513")
    CBC_513("CBC513");
    private final String value;

    CbcBizActivityTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CbcBizActivityTypeEnumType fromValue(String v) {
        for (CbcBizActivityTypeEnumType c: CbcBizActivityTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
