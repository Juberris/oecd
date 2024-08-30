//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package com.example.domain.crs.v2.oecd.ties.crs.v2;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType;

import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * Information in the message header identifies the Tax Administration that is sending the message.  It specifies when the message was created, what period (normally a year) the report is for, and the nature of the report (original, corrected, supplemental, etc).
 * 
 * <p>Clase Java para MessageSpec_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MessageSpec_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SendingCompanyIN" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *         &lt;element name="TransmittingCountry" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type"/&gt;
 *         &lt;element name="ReceivingCountry" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type"/&gt;
 *         &lt;element name="MessageType" type="{urn:oecd:ties:crs:v2}MessageType_EnumType"/&gt;
 *         &lt;element name="Warning" type="{urn:oecd:ties:crsstf:v5}StringMin1Max4000_Type" minOccurs="0"/&gt;
 *         &lt;element name="Contact" type="{urn:oecd:ties:crsstf:v5}StringMin1Max4000_Type" minOccurs="0"/&gt;
 *         &lt;element name="MessageRefId" type="{urn:oecd:ties:crsstf:v5}StringMin1Max170_Type"/&gt;
 *         &lt;element name="MessageTypeIndic" type="{urn:oecd:ties:crs:v2}CrsMessageTypeIndic_EnumType"/&gt;
 *         &lt;element name="CorrMessageRefId" type="{urn:oecd:ties:crsstf:v5}StringMin1Max170_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ReportingPeriod" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageSpec_Type", propOrder = {
    "sendingCompanyIN",
    "transmittingCountry",
    "receivingCountry",
    "messageType",
    "warning",
    "contact",
    "messageRefId",
    "messageTypeIndic",
    "corrMessageRefId",
    "reportingPeriod",
    "timestamp"
})
public class MessageSpecType {

    @XmlElement(name = "SendingCompanyIN")
    protected String sendingCompanyIN;
    @XmlElement(name = "TransmittingCountry", required = true)
    @XmlSchemaType(name = "string")
    protected CountryCodeType transmittingCountry;
    @XmlElement(name = "ReceivingCountry", required = true)
    @XmlSchemaType(name = "string")
    protected CountryCodeType receivingCountry;
    @XmlElement(name = "MessageType", required = true)
    @XmlSchemaType(name = "string")
    protected MessageTypeEnumType messageType;
    @XmlElement(name = "Warning")
    protected String warning;
    @XmlElement(name = "Contact")
    protected String contact;
    @XmlElement(name = "MessageRefId", required = true)
    protected String messageRefId;
    @XmlElement(name = "MessageTypeIndic", required = true)
    @XmlSchemaType(name = "string")
    protected CrsMessageTypeIndicEnumType messageTypeIndic;
    @XmlElement(name = "CorrMessageRefId")
    protected List<String> corrMessageRefId;
    @XmlElement(name = "ReportingPeriod", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar reportingPeriod;
    @XmlElement(name = "Timestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;

    /**
     * Obtiene el valor de la propiedad sendingCompanyIN.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendingCompanyIN() {
        return sendingCompanyIN;
    }

    /**
     * Define el valor de la propiedad sendingCompanyIN.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendingCompanyIN(String value) {
        this.sendingCompanyIN = value;
    }

    /**
     * Obtiene el valor de la propiedad transmittingCountry.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getTransmittingCountry() {
        return transmittingCountry;
    }

    /**
     * Define el valor de la propiedad transmittingCountry.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setTransmittingCountry(CountryCodeType value) {
        this.transmittingCountry = value;
    }

    /**
     * Obtiene el valor de la propiedad receivingCountry.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getReceivingCountry() {
        return receivingCountry;
    }

    /**
     * Define el valor de la propiedad receivingCountry.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setReceivingCountry(CountryCodeType value) {
        this.receivingCountry = value;
    }

    /**
     * Obtiene el valor de la propiedad messageType.
     * 
     * @return
     *     possible object is
     *     {@link MessageTypeEnumType }
     *     
     */
    public MessageTypeEnumType getMessageType() {
        return messageType;
    }

    /**
     * Define el valor de la propiedad messageType.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageTypeEnumType }
     *     
     */
    public void setMessageType(MessageTypeEnumType value) {
        this.messageType = value;
    }

    /**
     * Obtiene el valor de la propiedad warning.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Define el valor de la propiedad warning.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarning(String value) {
        this.warning = value;
    }

    /**
     * Obtiene el valor de la propiedad contact.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact() {
        return contact;
    }

    /**
     * Define el valor de la propiedad contact.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Obtiene el valor de la propiedad messageRefId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageRefId() {
        return messageRefId;
    }

    /**
     * Define el valor de la propiedad messageRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageRefId(String value) {
        this.messageRefId = value;
    }

    /**
     * Obtiene el valor de la propiedad messageTypeIndic.
     * 
     * @return
     *     possible object is
     *     {@link CrsMessageTypeIndicEnumType }
     *     
     */
    public CrsMessageTypeIndicEnumType getMessageTypeIndic() {
        return messageTypeIndic;
    }

    /**
     * Define el valor de la propiedad messageTypeIndic.
     * 
     * @param value
     *     allowed object is
     *     {@link CrsMessageTypeIndicEnumType }
     *     
     */
    public void setMessageTypeIndic(CrsMessageTypeIndicEnumType value) {
        this.messageTypeIndic = value;
    }

    /**
     * Gets the value of the corrMessageRefId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the corrMessageRefId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorrMessageRefId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCorrMessageRefId() {
        if (corrMessageRefId == null) {
            corrMessageRefId = new ArrayList<String>();
        }
        return this.corrMessageRefId;
    }

    /**
     * Obtiene el valor de la propiedad reportingPeriod.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReportingPeriod() {
        return reportingPeriod;
    }

    /**
     * Define el valor de la propiedad reportingPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReportingPeriod(XMLGregorianCalendar value) {
        this.reportingPeriod = value;
    }

    /**
     * Obtiene el valor de la propiedad timestamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Define el valor de la propiedad timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

}
