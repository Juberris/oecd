//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.cbc.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ReportingEntity_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReportingEntity_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Entity" type="{urn:oecd:ties:cbc:v1}OrganisationParty_Type"/&gt;
 *         &lt;element name="ReportingRole" type="{urn:oecd:ties:cbc:v1}CbcReportingRole_EnumType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportingEntity_Type", propOrder = {
    "entity",
    "reportingRole"
})
@XmlSeeAlso({
    CorrectableReportingEntityType.class
})
public class ReportingEntityType {

    @XmlElement(name = "Entity", required = true)
    protected OrganisationPartyType entity;
    @XmlElement(name = "ReportingRole", required = true)
    @XmlSchemaType(name = "string")
    protected CbcReportingRoleEnumType reportingRole;

    /**
     * Obtiene el valor de la propiedad entity.
     * 
     * @return
     *     possible object is
     *     {@link OrganisationPartyType }
     *     
     */
    public OrganisationPartyType getEntity() {
        return entity;
    }

    /**
     * Define el valor de la propiedad entity.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationPartyType }
     *     
     */
    public void setEntity(OrganisationPartyType value) {
        this.entity = value;
    }

    /**
     * Obtiene el valor de la propiedad reportingRole.
     * 
     * @return
     *     possible object is
     *     {@link CbcReportingRoleEnumType }
     *     
     */
    public CbcReportingRoleEnumType getReportingRole() {
        return reportingRole;
    }

    /**
     * Define el valor de la propiedad reportingRole.
     * 
     * @param value
     *     allowed object is
     *     {@link CbcReportingRoleEnumType }
     *     
     */
    public void setReportingRole(CbcReportingRoleEnumType value) {
        this.reportingRole = value;
    }

}
