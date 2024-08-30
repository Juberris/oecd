//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.csm.v1;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para FileMetaData_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FileMetaData_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CTSTransmissionID" type="{urn:oecd:ties:csm:v1}StringMax200Type" minOccurs="0"/&gt;
 *         &lt;element name="CTSSendingTimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="UncompressedFileSizeKBQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileMetaData_Type", propOrder = {
    "ctsTransmissionID",
    "ctsSendingTimeStamp",
    "uncompressedFileSizeKBQty"
})
public class FileMetaDataType {

    @XmlElement(name = "CTSTransmissionID")
    protected String ctsTransmissionID;
    @XmlElement(name = "CTSSendingTimeStamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ctsSendingTimeStamp;
    @XmlElement(name = "UncompressedFileSizeKBQty")
    protected BigInteger uncompressedFileSizeKBQty;

    /**
     * Obtiene el valor de la propiedad ctsTransmissionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCTSTransmissionID() {
        return ctsTransmissionID;
    }

    /**
     * Define el valor de la propiedad ctsTransmissionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCTSTransmissionID(String value) {
        this.ctsTransmissionID = value;
    }

    /**
     * Obtiene el valor de la propiedad ctsSendingTimeStamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCTSSendingTimeStamp() {
        return ctsSendingTimeStamp;
    }

    /**
     * Define el valor de la propiedad ctsSendingTimeStamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCTSSendingTimeStamp(XMLGregorianCalendar value) {
        this.ctsSendingTimeStamp = value;
    }

    /**
     * Obtiene el valor de la propiedad uncompressedFileSizeKBQty.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUncompressedFileSizeKBQty() {
        return uncompressedFileSizeKBQty;
    }

    /**
     * Define el valor de la propiedad uncompressedFileSizeKBQty.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUncompressedFileSizeKBQty(BigInteger value) {
        this.uncompressedFileSizeKBQty = value;
    }

}
