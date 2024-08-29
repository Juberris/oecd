//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.cts2.data.export.domain.cbc.v1.oecd.ties.cbc.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import cl.sii.cts2.data.export.domain.cbc.v1.oecd.ties.isocbctypes.v1.CountryCodeType;


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
 *     &lt;extension base="&lt;urn:oecd:ties:cbc:v1&gt;String1MinLength_Type"&gt;
 *       &lt;attribute name="issuedBy" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type" /&gt;
 *       &lt;attribute name="INType" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
     * Introduce a min length
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
