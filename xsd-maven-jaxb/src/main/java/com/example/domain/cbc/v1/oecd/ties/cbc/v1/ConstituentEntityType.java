//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package com.example.domain.cbc.v1.oecd.ties.cbc.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * <p>Clase Java para ConstituentEntity_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConstituentEntity_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ConstEntity" type="{urn:oecd:ties:cbc:v1}OrganisationParty_Type"/&gt;
 *         &lt;element name="IncorpCountryCode" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type" minOccurs="0"/&gt;
 *         &lt;element name="BizActivities" type="{urn:oecd:ties:cbc:v1}CbcBizActivityType_EnumType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="OtherEntityInfo" type="{urn:oecd:ties:cbc:v1}StringMaxLengthForLongText_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConstituentEntity_Type", propOrder = {
    "constEntity",
    "incorpCountryCode",
    "bizActivities",
    "otherEntityInfo"
})
public class ConstituentEntityType {

    @XmlElement(name = "ConstEntity", required = true)
    protected OrganisationPartyType constEntity;
    @XmlElement(name = "IncorpCountryCode")
    @XmlSchemaType(name = "string")
    protected CountryCodeType incorpCountryCode;
    @XmlElement(name = "BizActivities", required = true)
    @XmlSchemaType(name = "string")
    protected List<CbcBizActivityTypeEnumType> bizActivities;
    @XmlElement(name = "OtherEntityInfo")
    protected String otherEntityInfo;

    /**
     * Obtiene el valor de la propiedad constEntity.
     * 
     * @return
     *     possible object is
     *     {@link OrganisationPartyType }
     *     
     */
    public OrganisationPartyType getConstEntity() {
        return constEntity;
    }

    /**
     * Define el valor de la propiedad constEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationPartyType }
     *     
     */
    public void setConstEntity(OrganisationPartyType value) {
        this.constEntity = value;
    }

    /**
     * Obtiene el valor de la propiedad incorpCountryCode.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getIncorpCountryCode() {
        return incorpCountryCode;
    }

    /**
     * Define el valor de la propiedad incorpCountryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setIncorpCountryCode(CountryCodeType value) {
        this.incorpCountryCode = value;
    }

    /**
     * Gets the value of the bizActivities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bizActivities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBizActivities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CbcBizActivityTypeEnumType }
     * 
     * 
     */
    public List<CbcBizActivityTypeEnumType> getBizActivities() {
        if (bizActivities == null) {
            bizActivities = new ArrayList<CbcBizActivityTypeEnumType>();
        }
        return this.bizActivities;
    }

    /**
     * Obtiene el valor de la propiedad otherEntityInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherEntityInfo() {
        return otherEntityInfo;
    }

    /**
     * Define el valor de la propiedad otherEntityInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherEntityInfo(String value) {
        this.otherEntityInfo = value;
    }

}
