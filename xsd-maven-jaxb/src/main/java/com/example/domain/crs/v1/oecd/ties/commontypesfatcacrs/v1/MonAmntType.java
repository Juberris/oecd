//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CurrCodeType;


/**
 * 
 * This data type is to be used whenever monetary amounts are to be communicated. Such amounts shall be given
 * including two fractional digits of the main currency unit. The code for the currency in which the value is expressed has to be
 * taken from the ISO codelist 4217 and added in attribute currCode.
 * 
 * 
 * <p>Clase Java para MonAmnt_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MonAmnt_Type"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:oecd:ties:commontypesfatcacrs:v1&gt;TwoDigFract_Type"&gt;
 *       &lt;attribute name="currCode" use="required" type="{urn:oecd:ties:isocrstypes:v1}currCode_Type" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonAmnt_Type", propOrder = {
    "value"
})
public class MonAmntType {

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "currCode", required = true)
    protected CurrCodeType currCode;

    /**
     * 
     * 				Data type for any kind of numeric data with two decimal fraction digits, especially monetary amounts.
     * 			
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad currCode.
     * 
     * @return
     *     possible object is
     *     {@link CurrCodeType }
     *     
     */
    public CurrCodeType getCurrCode() {
        return currCode;
    }

    /**
     * Define el valor de la propiedad currCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrCodeType }
     *     
     */
    public void setCurrCode(CurrCodeType value) {
        this.currCode = value;
    }

}
