//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.cbc.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * 
 * This container brings together all data about an organisation as a party. Name and address are required components and each can
 * be present more than once to enable as complete a description as possible. Whenever possible one or more identifiers (TIN
 * etc) should be added as well as a residence country code. Additional data that describes and identifies the party can be
 * given . The code for the legal type according to the OECD codelist must be added. The structures of
 * all of the subelements are defined elsewhere in this schema.
 * 
 * <p>Clase Java para OrganisationParty_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OrganisationParty_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResCountryCode" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="TIN" type="{urn:oecd:ties:cbc:v2}TIN_Type"/&gt;
 *         &lt;element name="IN" type="{urn:oecd:ties:cbc:v2}OrganisationIN_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{urn:oecd:ties:cbc:v2}NameOrganisation_Type" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Address" type="{urn:oecd:ties:cbc:v2}Address_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationParty_Type", propOrder = {
    "resCountryCode",
    "tin",
    "in",
    "name",
    "address"
})
public class OrganisationPartyType {

    @XmlElement(name = "ResCountryCode", required = true)
    @XmlSchemaType(name = "string")
    protected List<CountryCodeType> resCountryCode;
    @XmlElement(name = "TIN", required = true)
    protected TINType tin;
    @XmlElement(name = "IN")
    protected List<OrganisationINType> in;
    @XmlElement(name = "Name", required = true)
    protected List<NameOrganisationType> name;
    @XmlElement(name = "Address")
    protected List<AddressType> address;

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
     * Obtiene el valor de la propiedad tin.
     * 
     * @return
     *     possible object is
     *     {@link TINType }
     *     
     */
    public TINType getTIN() {
        return tin;
    }

    /**
     * Define el valor de la propiedad tin.
     * 
     * @param value
     *     allowed object is
     *     {@link TINType }
     *     
     */
    public void setTIN(TINType value) {
        this.tin = value;
    }

    /**
     * Gets the value of the in property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the in property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationINType }
     * 
     * 
     */
    public List<OrganisationINType> getIN() {
        if (in == null) {
            in = new ArrayList<OrganisationINType>();
        }
        return this.in;
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
     * {@link NameOrganisationType }
     * 
     * 
     */
    public List<NameOrganisationType> getName() {
        if (name == null) {
            name = new ArrayList<NameOrganisationType>();
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

}
