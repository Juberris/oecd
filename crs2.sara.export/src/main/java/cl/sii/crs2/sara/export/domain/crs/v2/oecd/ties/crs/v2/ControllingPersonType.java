//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Clase Java para ControllingPerson_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ControllingPerson_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Individual" type="{urn:oecd:ties:crs:v2}PersonParty_Type"/&gt;
 *         &lt;element name="CtrlgPersonType" type="{urn:oecd:ties:crs:v2}CrsCtrlgPersonType_EnumType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ControllingPerson_Type", propOrder = {
    "individual",
    "ctrlgPersonType"
})
public class ControllingPersonType {

    @XmlElement(name = "Individual", required = true)
    protected PersonPartyType individual;
    @XmlElement(name = "CtrlgPersonType")
    @XmlSchemaType(name = "string")
    protected CrsCtrlgPersonTypeEnumType ctrlgPersonType;

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
     * Obtiene el valor de la propiedad ctrlgPersonType.
     * 
     * @return
     *     possible object is
     *     {@link CrsCtrlgPersonTypeEnumType }
     *     
     */
    public CrsCtrlgPersonTypeEnumType getCtrlgPersonType() {
        return ctrlgPersonType;
    }

    /**
     * Define el valor de la propiedad ctrlgPersonType.
     * 
     * @param value
     *     allowed object is
     *     {@link CrsCtrlgPersonTypeEnumType }
     *     
     */
    public void setCtrlgPersonType(CrsCtrlgPersonTypeEnumType value) {
        this.ctrlgPersonType = value;
    }

}
