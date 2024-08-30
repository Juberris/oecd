//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.crs.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.fatca.v1.CorrectablePoolReportType;


/**
 * <p>Clase Java para CrsBody_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrsBody_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReportingFI" type="{urn:oecd:ties:crs:v1}CorrectableOrganisationParty_Type"/&gt;
 *         &lt;element name="ReportingGroup" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Sponsor" type="{urn:oecd:ties:crs:v1}CorrectableOrganisationParty_Type" minOccurs="0"/&gt;
 *                   &lt;element name="Intermediary" type="{urn:oecd:ties:crs:v1}CorrectableOrganisationParty_Type" minOccurs="0"/&gt;
 *                   &lt;element name="AccountReport" type="{urn:oecd:ties:crs:v1}CorrectableAccountReport_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="PoolReport" type="{urn:oecd:ties:fatca:v1}CorrectablePoolReport_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "CrsBody_Type", propOrder = {
    "reportingFI",
    "reportingGroup"
})
public class CrsBodyType {

    @XmlElement(name = "ReportingFI", required = true)
    protected CorrectableOrganisationPartyType reportingFI;
    @XmlElement(name = "ReportingGroup", required = true)
    protected List<ReportingGroup> reportingGroup;

    /**
     * Obtiene el valor de la propiedad reportingFI.
     * 
     * @return
     *     possible object is
     *     {@link CorrectableOrganisationPartyType }
     *     
     */
    public CorrectableOrganisationPartyType getReportingFI() {
        return reportingFI;
    }

    /**
     * Define el valor de la propiedad reportingFI.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrectableOrganisationPartyType }
     *     
     */
    public void setReportingFI(CorrectableOrganisationPartyType value) {
        this.reportingFI = value;
    }

    /**
     * Gets the value of the reportingGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportingGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportingGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportingGroup }
     * 
     * 
     */
    public List<ReportingGroup> getReportingGroup() {
        if (reportingGroup == null) {
            reportingGroup = new ArrayList<ReportingGroup>();
        }
        return this.reportingGroup;
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
     *         &lt;element name="Sponsor" type="{urn:oecd:ties:crs:v1}CorrectableOrganisationParty_Type" minOccurs="0"/&gt;
     *         &lt;element name="Intermediary" type="{urn:oecd:ties:crs:v1}CorrectableOrganisationParty_Type" minOccurs="0"/&gt;
     *         &lt;element name="AccountReport" type="{urn:oecd:ties:crs:v1}CorrectableAccountReport_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="PoolReport" type="{urn:oecd:ties:fatca:v1}CorrectablePoolReport_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "sponsor",
        "intermediary",
        "accountReport",
        "poolReport"
    })
    public static class ReportingGroup {

        @XmlElement(name = "Sponsor")
        protected CorrectableOrganisationPartyType sponsor;
        @XmlElement(name = "Intermediary")
        protected CorrectableOrganisationPartyType intermediary;
        @XmlElement(name = "AccountReport")
        protected List<CorrectableAccountReportType> accountReport;
        @XmlElement(name = "PoolReport")
        protected List<CorrectablePoolReportType> poolReport;

        /**
         * Obtiene el valor de la propiedad sponsor.
         * 
         * @return
         *     possible object is
         *     {@link CorrectableOrganisationPartyType }
         *     
         */
        public CorrectableOrganisationPartyType getSponsor() {
            return sponsor;
        }

        /**
         * Define el valor de la propiedad sponsor.
         * 
         * @param value
         *     allowed object is
         *     {@link CorrectableOrganisationPartyType }
         *     
         */
        public void setSponsor(CorrectableOrganisationPartyType value) {
            this.sponsor = value;
        }

        /**
         * Obtiene el valor de la propiedad intermediary.
         * 
         * @return
         *     possible object is
         *     {@link CorrectableOrganisationPartyType }
         *     
         */
        public CorrectableOrganisationPartyType getIntermediary() {
            return intermediary;
        }

        /**
         * Define el valor de la propiedad intermediary.
         * 
         * @param value
         *     allowed object is
         *     {@link CorrectableOrganisationPartyType }
         *     
         */
        public void setIntermediary(CorrectableOrganisationPartyType value) {
            this.intermediary = value;
        }

        /**
         * Gets the value of the accountReport property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the accountReport property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAccountReport().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CorrectableAccountReportType }
         * 
         * 
         */
        public List<CorrectableAccountReportType> getAccountReport() {
            if (accountReport == null) {
                accountReport = new ArrayList<CorrectableAccountReportType>();
            }
            return this.accountReport;
        }

        /**
         * Gets the value of the poolReport property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the poolReport property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPoolReport().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CorrectablePoolReportType }
         * 
         * 
         */
        public List<CorrectablePoolReportType> getPoolReport() {
            if (poolReport == null) {
                poolReport = new ArrayList<CorrectablePoolReportType>();
            }
            return this.poolReport;
        }

    }

}
