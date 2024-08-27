//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.cbc.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CbcSummaryListElementsType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CbcSummaryListElementsType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CBC601"/&gt;
 *     &lt;enumeration value="CBC602"/&gt;
 *     &lt;enumeration value="CBC603"/&gt;
 *     &lt;enumeration value="CBC604"/&gt;
 *     &lt;enumeration value="CBC605"/&gt;
 *     &lt;enumeration value="CBC606"/&gt;
 *     &lt;enumeration value="CBC607"/&gt;
 *     &lt;enumeration value="CBC608"/&gt;
 *     &lt;enumeration value="CBC609"/&gt;
 *     &lt;enumeration value="CBC610"/&gt;
 *     &lt;enumeration value="CBC611"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CbcSummaryListElementsType_EnumType")
@XmlEnum
public enum CbcSummaryListElementsTypeEnumType {


    /**
     * Revenues - Unrelated
     * 
     */
    @XmlEnumValue("CBC601")
    CBC_601("CBC601"),

    /**
     * Revenues - Related
     * 
     */
    @XmlEnumValue("CBC602")
    CBC_602("CBC602"),

    /**
     * Revenues - Total
     * 
     */
    @XmlEnumValue("CBC603")
    CBC_603("CBC603"),

    /**
     * Profit or Loss
     * 
     */
    @XmlEnumValue("CBC604")
    CBC_604("CBC604"),

    /**
     * Tax Paid
     * 
     */
    @XmlEnumValue("CBC605")
    CBC_605("CBC605"),

    /**
     * Tax Accrued
     * 
     */
    @XmlEnumValue("CBC606")
    CBC_606("CBC606"),

    /**
     * Capital
     * 
     */
    @XmlEnumValue("CBC607")
    CBC_607("CBC607"),

    /**
     * Earnings
     * 
     */
    @XmlEnumValue("CBC608")
    CBC_608("CBC608"),

    /**
     * Number of Employees
     * 
     */
    @XmlEnumValue("CBC609")
    CBC_609("CBC609"),

    /**
     * Assets
     * 
     */
    @XmlEnumValue("CBC610")
    CBC_610("CBC610"),

    /**
     * Name of MNE Group
     * 
     */
    @XmlEnumValue("CBC611")
    CBC_611("CBC611");
    private final String value;

    CbcSummaryListElementsTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CbcSummaryListElementsTypeEnumType fromValue(String v) {
        for (CbcSummaryListElementsTypeEnumType c: CbcSummaryListElementsTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
