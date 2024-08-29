//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2;

import cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crsstf.v5.OECDNameTypeEnumType;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The user must spread the data about the name of a party over up to six elements. The container element for this will be 'NameFix'. 
 * 
 * <p>Clase Java para NamePerson_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NamePerson_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrecedingTitle" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *         &lt;element name="Title" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="FirstName"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MiddleName" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NamePrefix" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LastName"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="GenerationIdentifier" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Suffix" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="GeneralSuffix" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="nameType" type="{urn:oecd:ties:crsstf:v5}OECDNameType_EnumType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamePerson_Type", propOrder = {
    "precedingTitle",
    "title",
    "firstName",
    "middleName",
    "namePrefix",
    "lastName",
    "generationIdentifier",
    "suffix",
    "generalSuffix"
})
public class NamePersonType {

    @XmlElement(name = "PrecedingTitle")
    protected String precedingTitle;
    @XmlElement(name = "Title")
    protected List<String> title;
    @XmlElement(name = "FirstName", required = true)
    protected NamePersonType.FirstName firstName;
    @XmlElement(name = "MiddleName")
    protected List<MiddleName> middleName;
    @XmlElement(name = "NamePrefix")
    protected NamePersonType.NamePrefix namePrefix;
    @XmlElement(name = "LastName", required = true)
    protected NamePersonType.LastName lastName;
    @XmlElement(name = "GenerationIdentifier")
    protected List<String> generationIdentifier;
    @XmlElement(name = "Suffix")
    protected List<String> suffix;
    @XmlElement(name = "GeneralSuffix")
    protected String generalSuffix;
    @XmlAttribute(name = "nameType")
    protected OECDNameTypeEnumType nameType;

    /**
     * Obtiene el valor de la propiedad precedingTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecedingTitle() {
        return precedingTitle;
    }

    /**
     * Define el valor de la propiedad precedingTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecedingTitle(String value) {
        this.precedingTitle = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTitle() {
        if (title == null) {
            title = new ArrayList<String>();
        }
        return this.title;
    }

    /**
     * Obtiene el valor de la propiedad firstName.
     * 
     * @return
     *     possible object is
     *     {@link FirstName }
     *     
     */
    public FirstName getFirstName() {
        return firstName;
    }

    /**
     * Define el valor de la propiedad firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link FirstName }
     *     
     */
    public void setFirstName(FirstName value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the middleName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiddleName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MiddleName }
     * 
     * 
     */
    public List<MiddleName> getMiddleName() {
        if (middleName == null) {
            middleName = new ArrayList<MiddleName>();
        }
        return this.middleName;
    }

    /**
     * Obtiene el valor de la propiedad namePrefix.
     * 
     * @return
     *     possible object is
     *     {@link NamePrefix }
     *     
     */
    public NamePrefix getNamePrefix() {
        return namePrefix;
    }

    /**
     * Define el valor de la propiedad namePrefix.
     * 
     * @param value
     *     allowed object is
     *     {@link NamePrefix }
     *     
     */
    public void setNamePrefix(NamePrefix value) {
        this.namePrefix = value;
    }

    /**
     * Obtiene el valor de la propiedad lastName.
     * 
     * @return
     *     possible object is
     *     {@link LastName }
     *     
     */
    public LastName getLastName() {
        return lastName;
    }

    /**
     * Define el valor de la propiedad lastName.
     * 
     * @param value
     *     allowed object is
     *     {@link LastName }
     *     
     */
    public void setLastName(LastName value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the generationIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generationIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenerationIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenerationIdentifier() {
        if (generationIdentifier == null) {
            generationIdentifier = new ArrayList<String>();
        }
        return this.generationIdentifier;
    }

    /**
     * Gets the value of the suffix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suffix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuffix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSuffix() {
        if (suffix == null) {
            suffix = new ArrayList<String>();
        }
        return this.suffix;
    }

    /**
     * Obtiene el valor de la propiedad generalSuffix.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneralSuffix() {
        return generalSuffix;
    }

    /**
     * Define el valor de la propiedad generalSuffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneralSuffix(String value) {
        this.generalSuffix = value;
    }

    /**
     * Obtiene el valor de la propiedad nameType.
     * 
     * @return
     *     possible object is
     *     {@link OECDNameTypeEnumType }
     *     
     */
    public OECDNameTypeEnumType getNameType() {
        return nameType;
    }

    /**
     * Define el valor de la propiedad nameType.
     * 
     * @param value
     *     allowed object is
     *     {@link OECDNameTypeEnumType }
     *     
     */
    public void setNameType(OECDNameTypeEnumType value) {
        this.nameType = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class FirstName {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "xnlNameType")
        protected String xnlNameType;

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
         * Obtiene el valor de la propiedad xnlNameType.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Define el valor de la propiedad xnlNameType.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class LastName {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "xnlNameType")
        protected String xnlNameType;

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
         * Obtiene el valor de la propiedad xnlNameType.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Define el valor de la propiedad xnlNameType.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class MiddleName {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "xnlNameType")
        protected String xnlNameType;

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
         * Obtiene el valor de la propiedad xnlNameType.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Define el valor de la propiedad xnlNameType.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NamePrefix {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "xnlNameType")
        protected String xnlNameType;

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
         * Obtiene el valor de la propiedad xnlNameType.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Define el valor de la propiedad xnlNameType.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }

}
