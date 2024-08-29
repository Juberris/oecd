//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v2.oecd.ties.crs.v2;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Clase Java para AccountHolder_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AccountHolder_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Individual" type="{urn:oecd:ties:crs:v2}PersonParty_Type"/&gt;
 *           &lt;sequence&gt;
 *             &lt;element name="Organisation" type="{urn:oecd:ties:crs:v2}OrganisationParty_Type"/&gt;
 *             &lt;element name="AcctHolderType" type="{urn:oecd:ties:crs:v2}CrsAcctHolderType_EnumType"/&gt;
 *           &lt;/sequence&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountHolder_Type", propOrder = {
    "individual",
    "organisation",
    "acctHolderType"
})
public class AccountHolderType {

    @XmlElement(name = "Individual")
    protected PersonPartyType individual;
    @XmlElement(name = "Organisation")
    protected OrganisationPartyType organisation;
    @XmlElement(name = "AcctHolderType")
    @XmlSchemaType(name = "string")
    protected CrsAcctHolderTypeEnumType acctHolderType;

    /**
     * Obtiene el valor de la propiedad individual.
     * 
     * @return
     *     possible object is
     *     {@link PersonPartyType }
     *     
     */
    public PersonPartyType getIndividual() {
        return individual;
    }

    /**
     * Define el valor de la propiedad individual.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonPartyType }
     *     
     */
    public void setIndividual(PersonPartyType value) {
        this.individual = value;
    }

    /**
     * Obtiene el valor de la propiedad organisation.
     * 
     * @return
     *     possible object is
     *     {@link OrganisationPartyType }
     *     
     */
    public OrganisationPartyType getOrganisation() {
        return organisation;
    }

    /**
     * Define el valor de la propiedad organisation.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationPartyType }
     *     
     */
    public void setOrganisation(OrganisationPartyType value) {
        this.organisation = value;
    }

    /**
     * Obtiene el valor de la propiedad acctHolderType.
     * 
     * @return
     *     possible object is
     *     {@link CrsAcctHolderTypeEnumType }
     *     
     */
    public CrsAcctHolderTypeEnumType getAcctHolderType() {
        return acctHolderType;
    }

    /**
     * Define el valor de la propiedad acctHolderType.
     * 
     * @param value
     *     allowed object is
     *     {@link CrsAcctHolderTypeEnumType }
     *     
     */
    public void setAcctHolderType(CrsAcctHolderTypeEnumType value) {
        this.acctHolderType = value;
    }

}
