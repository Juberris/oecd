//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.csm.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OriginalMessage_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OriginalMessage_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OriginalMessageRefID" type="{urn:oecd:ties:csm:v1}StringMax200Type" minOccurs="0"/&gt;
 *         &lt;element name="FileMetaData" type="{urn:oecd:ties:csm:v1}FileMetaData_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginalMessage_Type", propOrder = {
    "originalMessageRefID",
    "fileMetaData"
})
public class OriginalMessageType {

    @XmlElement(name = "OriginalMessageRefID")
    protected String originalMessageRefID;
    @XmlElement(name = "FileMetaData")
    protected FileMetaDataType fileMetaData;

    /**
     * Obtiene el valor de la propiedad originalMessageRefID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalMessageRefID() {
        return originalMessageRefID;
    }

    /**
     * Define el valor de la propiedad originalMessageRefID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalMessageRefID(String value) {
        this.originalMessageRefID = value;
    }

    /**
     * Obtiene el valor de la propiedad fileMetaData.
     * 
     * @return
     *     possible object is
     *     {@link FileMetaDataType }
     *     
     */
    public FileMetaDataType getFileMetaData() {
        return fileMetaData;
    }

    /**
     * Define el valor de la propiedad fileMetaData.
     * 
     * @param value
     *     allowed object is
     *     {@link FileMetaDataType }
     *     
     */
    public void setFileMetaData(FileMetaDataType value) {
        this.fileMetaData = value;
    }

}
