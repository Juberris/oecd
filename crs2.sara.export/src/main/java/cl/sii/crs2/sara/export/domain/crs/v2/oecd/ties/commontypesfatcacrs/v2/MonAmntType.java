//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CurrCodeType;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;


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
 *     &lt;extension base="&lt;urn:oecd:ties:commontypesfatcacrs:v2&gt;TwoDigFract_Type"&gt;
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
