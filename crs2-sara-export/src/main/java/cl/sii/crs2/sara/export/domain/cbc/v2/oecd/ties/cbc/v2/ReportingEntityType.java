//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v2.oecd.ties.cbc.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Entity" type="{urn:oecd:ties:cbc:v2}OrganisationParty_Type"/&gt;
 *         &lt;element name="NameMNEGroup" type="{urn:oecd:ties:cbcstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *         &lt;element name="ReportingRole" type="{urn:oecd:ties:cbc:v2}CbcReportingRole_EnumType"/&gt;
 *         &lt;element name="ReportingPeriod"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
@XmlType(name = "ReportingEntity_Type", propOrder = {
    "entity",
    "nameMNEGroup",
    "reportingRole",
    "reportingPeriod"
})
@XmlSeeAlso({
    CorrectableReportingEntityType.class
})
public class ReportingEntityType {

    @XmlElement(name = "Entity", required = true)
    protected OrganisationPartyType entity;
    @XmlElement(name = "NameMNEGroup")
    protected String nameMNEGroup;
    @XmlElement(name = "ReportingRole", required = true)
    @XmlSchemaType(name = "string")
    protected CbcReportingRoleEnumType reportingRole;
    @XmlElement(name = "ReportingPeriod", required = true)
    protected ReportingEntityType.ReportingPeriod reportingPeriod;

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
     * Obtiene el valor de la propiedad nameMNEGroup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameMNEGroup() {
        return nameMNEGroup;
    }

    /**
     * Define el valor de la propiedad nameMNEGroup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameMNEGroup(String value) {
        this.nameMNEGroup = value;
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

    /**
     * Obtiene el valor de la propiedad reportingPeriod.
     * 
     * @return
     *     possible object is
     *     {@link ReportingPeriod }
     *     
     */
    public ReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    /**
     * Define el valor de la propiedad reportingPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportingPeriod }
     *     
     */
    public void setReportingPeriod(ReportingPeriod value) {
        this.reportingPeriod = value;
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
     *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
        "startDate",
        "endDate"
    })
    public static class ReportingPeriod {

        @XmlElement(name = "StartDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar startDate;
        @XmlElement(name = "EndDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar endDate;

        /**
         * Obtiene el valor de la propiedad startDate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getStartDate() {
            return startDate;
        }

        /**
         * Define el valor de la propiedad startDate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setStartDate(XMLGregorianCalendar value) {
            this.startDate = value;
        }

        /**
         * Obtiene el valor de la propiedad endDate.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEndDate() {
            return endDate;
        }

        /**
         * Define el valor de la propiedad endDate.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEndDate(XMLGregorianCalendar value) {
            this.endDate = value;
        }

    }

}
