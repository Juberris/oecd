//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.24 a las 04:41:47 PM CLT 
//


package com.example.maven_jaxb_plugin.model.schema.wrapper.oecd.ties.cfw.v1;



import com.example.maven_jaxb_plugin.model.schema.wrapper.oecd.ties.cfw.isocfwtypes.v1.CountryCodeType;
import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="TransmittingCountry" type="{urn:oecd:ties:cfw:isocfwtypes:v1}CountryCode_Type"/&gt;
 *         &lt;element name="ReceivingCountry" type="{urn:oecd:ties:cfw:isocfwtypes:v1}CountryCode_Type"/&gt;
 *         &lt;element name="MessageType" type="{urn:oecd:ties:cfw:v1}MessageType_EnumType"/&gt;
 *         &lt;element name="Warning" type="{urn:oecd:ties:cfw:v1}StringMin1Max4000_Type" minOccurs="0"/&gt;
 *         &lt;element name="Contact" type="{urn:oecd:ties:cfw:v1}StringMin1Max4000_Type" minOccurs="0"/&gt;
 *         &lt;element name="MessageRefId" type="{urn:oecd:ties:cfw:v1}StringMin1Max170_Type"/&gt;
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
    "transmittingCountry",
    "receivingCountry",
    "messageType",
    "warning",
    "contact",
    "messageRefId",
    "timestamp"
})
public class MessageSpecType {

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
    @XmlElement(name = "Timestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;

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
