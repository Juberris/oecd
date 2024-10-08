//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AddressType;
import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.TINType;
import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType;

import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * This container brings together all data about a person as a party. Name and address are required components and each can
 * be present more than once to enable as complete a description as possible. Whenever possible one or more identifiers (TIN
 * etc) should be added as well as a residence country code. Additional data that describes and identifies the party can be
 * given. The code for the legal type according to the OECD codelist must be added. The structures of
 * all of the subelements are defined elsewhere in this schema.
 * 
 * <p>Clase Java para PersonParty_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PersonParty_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResCountryCode" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="TIN" type="{urn:oecd:ties:commontypesfatcacrs:v2}TIN_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{urn:oecd:ties:crs:v2}NamePerson_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Address" type="{urn:oecd:ties:commontypesfatcacrs:v2}Address_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Nationality" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BirthInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="City" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *                   &lt;element name="CitySubentity" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *                   &lt;element name="CountryInfo" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;choice&gt;
 *                             &lt;element name="CountryCode" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type"/&gt;
 *                             &lt;element name="FormerCountryName" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type"/&gt;
 *                           &lt;/choice&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonParty_Type", propOrder = {
    "resCountryCode",
    "tin",
    "name",
    "address",
    "nationality",
    "birthInfo"
})
public class PersonPartyType {

    @XmlElement(name = "ResCountryCode", required = true)
    @XmlSchemaType(name = "string")
    protected List<CountryCodeType> resCountryCode;
    @XmlElement(name = "TIN")
    protected List<TINType> tin;
    @XmlElement(name = "Name", required = true)
    protected List<NamePersonType> name;
    @XmlElement(name = "Address", required = true)
    protected List<AddressType> address;
    @XmlElement(name = "Nationality")
    @XmlSchemaType(name = "string")
    protected List<CountryCodeType> nationality;
    @XmlElement(name = "BirthInfo")
    protected PersonPartyType.BirthInfo birthInfo;

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
     * Gets the value of the tin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTIN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TINType }
     * 
     * 
     */
    public List<TINType> getTIN() {
        if (tin == null) {
            tin = new ArrayList<TINType>();
        }
        return this.tin;
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamePersonType }
     * 
     * 
     */
    public List<NamePersonType> getName() {
        if (name == null) {
            name = new ArrayList<NamePersonType>();
        }
        return this.name;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressType }
     * 
     * 
     */
    public List<AddressType> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressType>();
        }
        return this.address;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nationality property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNationality().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryCodeType }
     * 
     * 
     */
    public List<CountryCodeType> getNationality() {
        if (nationality == null) {
            nationality = new ArrayList<CountryCodeType>();
        }
        return this.nationality;
    }

    /**
     * Obtiene el valor de la propiedad birthInfo.
     * 
     * @return
     *     possible object is
     *     {@link BirthInfo }
     *     
     */
    public BirthInfo getBirthInfo() {
        return birthInfo;
    }

    /**
     * Define el valor de la propiedad birthInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthInfo }
     *     
     */
    public void setBirthInfo(BirthInfo value) {
        this.birthInfo = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="City" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
     *         &lt;element name="CitySubentity" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
     *         &lt;element name="CountryInfo" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;choice&gt;
     *                   &lt;element name="CountryCode" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type"/&gt;
     *                   &lt;element name="FormerCountryName" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type"/&gt;
     *                 &lt;/choice&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "birthDate",
        "city",
        "citySubentity",
        "countryInfo"
    })
    public static class BirthInfo {

        @XmlElement(name = "BirthDate")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar birthDate;
        @XmlElement(name = "City")
        protected String city;
        @XmlElement(name = "CitySubentity")
        protected String citySubentity;
        @XmlElement(name = "CountryInfo")
        protected PersonPartyType.BirthInfo.CountryInfo countryInfo;

        /**
         * Obtiene el valor de la propiedad birthDate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getBirthDate() {
            return birthDate;
        }

        /**
         * Define el valor de la propiedad birthDate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setBirthDate(XMLGregorianCalendar value) {
            this.birthDate = value;
        }

        /**
         * Obtiene el valor de la propiedad city.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * Define el valor de la propiedad city.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

        /**
         * Obtiene el valor de la propiedad citySubentity.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCitySubentity() {
            return citySubentity;
        }

        /**
         * Define el valor de la propiedad citySubentity.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCitySubentity(String value) {
            this.citySubentity = value;
        }

        /**
         * Obtiene el valor de la propiedad countryInfo.
         * 
         * @return
         *     possible object is
         *     {@link CountryInfo }
         *     
         */
        public CountryInfo getCountryInfo() {
            return countryInfo;
        }

        /**
         * Define el valor de la propiedad countryInfo.
         * 
         * @param value
         *     allowed object is
         *     {@link CountryInfo }
         *     
         */
        public void setCountryInfo(CountryInfo value) {
            this.countryInfo = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;choice&gt;
         *         &lt;element name="CountryCode" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type"/&gt;
         *         &lt;element name="FormerCountryName" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type"/&gt;
         *       &lt;/choice&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "countryCode",
            "formerCountryName"
        })
        public static class CountryInfo {

            @XmlElement(name = "CountryCode")
            @XmlSchemaType(name = "string")
            protected CountryCodeType countryCode;
            @XmlElement(name = "FormerCountryName")
            protected String formerCountryName;

            /**
             * Obtiene el valor de la propiedad countryCode.
             * 
             * @return
             *     possible object is
             *     {@link CountryCodeType }
             *     
             */
            public CountryCodeType getCountryCode() {
                return countryCode;
            }

            /**
             * Define el valor de la propiedad countryCode.
             * 
             * @param value
             *     allowed object is
             *     {@link CountryCodeType }
             *     
             */
            public void setCountryCode(CountryCodeType value) {
                this.countryCode = value;
            }

            /**
             * Obtiene el valor de la propiedad formerCountryName.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormerCountryName() {
                return formerCountryName;
            }

            /**
             * Define el valor de la propiedad formerCountryName.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormerCountryName(String value) {
                this.formerCountryName = value;
            }

        }

    }

}
