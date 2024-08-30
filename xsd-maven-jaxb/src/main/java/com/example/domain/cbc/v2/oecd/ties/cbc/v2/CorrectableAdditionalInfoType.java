//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package com.example.domain.cbc.v2.oecd.ties.cbc.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.cbcstf.v5.DocSpecType;
import cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.cbcstf.v5.StringMin1Max4000WithLangType;
import cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * <p>Clase Java para CorrectableAdditionalInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CorrectableAdditionalInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocSpec" type="{urn:oecd:ties:cbcstf:v5}DocSpec_Type"/&gt;
 *         &lt;element name="OtherInfo" type="{urn:oecd:ties:cbcstf:v5}StringMin1Max4000WithLang_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ResCountryCode" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SummaryRef" type="{urn:oecd:ties:cbc:v2}CbcSummaryListElementsType_EnumType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectableAdditionalInfo_Type", propOrder = {
    "docSpec",
    "otherInfo",
    "resCountryCode",
    "summaryRef"
})
public class CorrectableAdditionalInfoType {

    @XmlElement(name = "DocSpec", required = true)
    protected DocSpecType docSpec;
    @XmlElement(name = "OtherInfo", required = true)
    protected List<StringMin1Max4000WithLangType> otherInfo;
    @XmlElement(name = "ResCountryCode")
    @XmlSchemaType(name = "string")
    protected List<CountryCodeType> resCountryCode;
    @XmlElement(name = "SummaryRef")
    @XmlSchemaType(name = "string")
    protected List<CbcSummaryListElementsTypeEnumType> summaryRef;

    /**
     * Obtiene el valor de la propiedad docSpec.
     * 
     * @return
     *     possible object is
     *     {@link DocSpecType }
     *     
     */
    public DocSpecType getDocSpec() {
        return docSpec;
    }

    /**
     * Define el valor de la propiedad docSpec.
     * 
     * @param value
     *     allowed object is
     *     {@link DocSpecType }
     *     
     */
    public void setDocSpec(DocSpecType value) {
        this.docSpec = value;
    }

    /**
     * Gets the value of the otherInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringMin1Max4000WithLangType }
     * 
     * 
     */
    public List<StringMin1Max4000WithLangType> getOtherInfo() {
        if (otherInfo == null) {
            otherInfo = new ArrayList<StringMin1Max4000WithLangType>();
        }
        return this.otherInfo;
    }

    /**
     * Gets the value of the resCountryCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resCountryCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResCountryCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryCodeType }
     * 
     * 
     */
    public List<CountryCodeType> getResCountryCode() {
        if (resCountryCode == null) {
            resCountryCode = new ArrayList<CountryCodeType>();
        }
        return this.resCountryCode;
    }

    /**
     * Gets the value of the summaryRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the summaryRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSummaryRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CbcSummaryListElementsTypeEnumType }
     * 
     * 
     */
    public List<CbcSummaryListElementsTypeEnumType> getSummaryRef() {
        if (summaryRef == null) {
            summaryRef = new ArrayList<CbcSummaryListElementsTypeEnumType>();
        }
        return this.summaryRef;
    }

}
