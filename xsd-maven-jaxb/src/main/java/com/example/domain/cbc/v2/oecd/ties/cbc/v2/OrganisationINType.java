//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package com.example.domain.cbc.v2.oecd.ties.cbc.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * This is the identification number/identification code for the Entity in question. As the identifier may be not strictly numeric, it is just defined as a string of characters. Attribute 'issuedBy' is required to designate the issuer of the identifier.  Attribute 'INType' defines the type of identification number. 
 * 
 * <p>Clase Java para OrganisationIN_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OrganisationIN_Type"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:oecd:ties:cbcstf:v5&gt;StringMin1Max200_Type"&gt;
 *       &lt;attribute name="issuedBy" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type" /&gt;
 *       &lt;attribute name="INType" type="{urn:oecd:ties:cbcstf:v5}StringMin1Max200_Type" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationIN_Type", propOrder = {
    "value"
})
public class OrganisationINType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "issuedBy")
    protected CountryCodeType issuedBy;
    @XmlAttribute(name = "INType")
    protected String inType;

    /**
     * Defines a string with minimum length 1 and maximum length of 200
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad issuedBy.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getIssuedBy() {
        return issuedBy;
    }

    /**
     * Define el valor de la propiedad issuedBy.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setIssuedBy(CountryCodeType value) {
        this.issuedBy = value;
    }

    /**
     * Obtiene el valor de la propiedad inType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINType() {
        return inType;
    }

    /**
     * Define el valor de la propiedad inType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINType(String value) {
        this.inType = value;
    }

}
