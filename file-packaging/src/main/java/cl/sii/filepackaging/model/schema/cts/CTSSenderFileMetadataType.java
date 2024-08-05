//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:24 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;Component xmlns="urn:oecd:ctssenderfilemetadata" xmlns:iso="urn:oecd:ties:isoctstypes:v1" xmlns:xmime="http://www.w3.org/2005/05/xmlmime" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;
 * 					&lt;DictionaryEntryNm&gt;CTS Sender File Metadata Type&lt;/DictionaryEntryNm&gt;
 * 					&lt;MajorVersionNum&gt;1&lt;/MajorVersionNum&gt;
 * 					&lt;MinorVersionNum&gt;0&lt;/MinorVersionNum&gt;
 * 					&lt;VersionEffectiveBeginDt&gt;2016-09-01&lt;/VersionEffectiveBeginDt&gt;
 * 					&lt;VersionDescriptionTxt&gt;Initial Version&lt;/VersionDescriptionTxt&gt;
 * 					&lt;Description&gt;Type for a group that defines the information contained in the CTS Sender File Metadata&lt;/Description&gt;
 * 				&lt;/Component&gt;
 * </pre>
 * 
 * 			
 * 
 * <p>Clase Java para CTSSenderFileMetadataType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CTSSenderFileMetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}CTSSenderCountryCd"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}CTSReceiverCountryCd"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}CTSCommunicationTypeCd"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}SenderFileId"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}FileFormatCd" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}BinaryEncodingSchemeCd" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}FileCreateTs" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}TaxYear" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}FileRevisionInd" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}OriginalCTSTransmissionId" minOccurs="0"/>
 *         &lt;element ref="{urn:oecd:ctssenderfilemetadata}SenderContactEmailAddressTxt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTSSenderFileMetadataType", propOrder = {
    "ctsSenderCountryCd",
    "ctsReceiverCountryCd",
    "ctsCommunicationTypeCd",
    "senderFileId",
    "fileFormatCd",
    "binaryEncodingSchemeCd",
    "fileCreateTs",
    "taxYear",
    "fileRevisionInd",
    "originalCTSTransmissionId",
    "senderContactEmailAddressTxt"
})
public class CTSSenderFileMetadataType {

    @XmlElement(name = "CTSSenderCountryCd", required = true)
    protected String ctsSenderCountryCd;
    @XmlElement(name = "CTSReceiverCountryCd", required = true)
    protected String ctsReceiverCountryCd;
    @XmlElement(name = "CTSCommunicationTypeCd", required = true)
    @XmlSchemaType(name = "string")
    protected CTSCommunicationTypeCdType ctsCommunicationTypeCd;
    @XmlElement(name = "SenderFileId", required = true)
    protected String senderFileId;
    @XmlElement(name = "FileFormatCd")
    @XmlSchemaType(name = "string")
    protected FileFormatCdType fileFormatCd;
    @XmlElement(name = "BinaryEncodingSchemeCd")
    @XmlSchemaType(name = "string")
    protected BinaryEncodingSchemeCdType binaryEncodingSchemeCd;
    @XmlElement(name = "FileCreateTs")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fileCreateTs;
    @XmlElement(name = "TaxYear")
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar taxYear;
    @XmlElement(name = "FileRevisionInd")
    protected Boolean fileRevisionInd;
    @XmlElement(name = "OriginalCTSTransmissionId")
    protected String originalCTSTransmissionId;
    @XmlElement(name = "SenderContactEmailAddressTxt")
    protected String senderContactEmailAddressTxt;

    /**
     * Obtiene el valor de la propiedad ctsSenderCountryCd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCTSSenderCountryCd() {
        return ctsSenderCountryCd;
    }

    /**
     * Define el valor de la propiedad ctsSenderCountryCd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCTSSenderCountryCd(String value) {
        this.ctsSenderCountryCd = value;
    }

    /**
     * Obtiene el valor de la propiedad ctsReceiverCountryCd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCTSReceiverCountryCd() {
        return ctsReceiverCountryCd;
    }

    /**
     * Define el valor de la propiedad ctsReceiverCountryCd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCTSReceiverCountryCd(String value) {
        this.ctsReceiverCountryCd = value;
    }

    /**
     * Obtiene el valor de la propiedad ctsCommunicationTypeCd.
     * 
     * @return
     *     possible object is
     *     {@link CTSCommunicationTypeCdType }
     *     
     */
    public CTSCommunicationTypeCdType getCTSCommunicationTypeCd() {
        return ctsCommunicationTypeCd;
    }

    /**
     * Define el valor de la propiedad ctsCommunicationTypeCd.
     * 
     * @param value
     *     allowed object is
     *     {@link CTSCommunicationTypeCdType }
     *     
     */
    public void setCTSCommunicationTypeCd(CTSCommunicationTypeCdType value) {
        this.ctsCommunicationTypeCd = value;
    }

    /**
     * Obtiene el valor de la propiedad senderFileId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderFileId() {
        return senderFileId;
    }

    /**
     * Define el valor de la propiedad senderFileId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderFileId(String value) {
        this.senderFileId = value;
    }

    /**
     * Obtiene el valor de la propiedad fileFormatCd.
     * 
     * @return
     *     possible object is
     *     {@link FileFormatCdType }
     *     
     */
    public FileFormatCdType getFileFormatCd() {
        return fileFormatCd;
    }

    /**
     * Define el valor de la propiedad fileFormatCd.
     * 
     * @param value
     *     allowed object is
     *     {@link FileFormatCdType }
     *     
     */
    public void setFileFormatCd(FileFormatCdType value) {
        this.fileFormatCd = value;
    }

    /**
     * Obtiene el valor de la propiedad binaryEncodingSchemeCd.
     * 
     * @return
     *     possible object is
     *     {@link BinaryEncodingSchemeCdType }
     *     
     */
    public BinaryEncodingSchemeCdType getBinaryEncodingSchemeCd() {
        return binaryEncodingSchemeCd;
    }

    /**
     * Define el valor de la propiedad binaryEncodingSchemeCd.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryEncodingSchemeCdType }
     *     
     */
    public void setBinaryEncodingSchemeCd(BinaryEncodingSchemeCdType value) {
        this.binaryEncodingSchemeCd = value;
    }

    /**
     * Obtiene el valor de la propiedad fileCreateTs.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFileCreateTs() {
        return fileCreateTs;
    }

    /**
     * Define el valor de la propiedad fileCreateTs.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFileCreateTs(XMLGregorianCalendar value) {
        this.fileCreateTs = value;
    }

    /**
     * Obtiene el valor de la propiedad taxYear.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTaxYear() {
        return taxYear;
    }

    /**
     * Define el valor de la propiedad taxYear.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTaxYear(XMLGregorianCalendar value) {
        this.taxYear = value;
    }

    /**
     * Obtiene el valor de la propiedad fileRevisionInd.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFileRevisionInd() {
        return fileRevisionInd;
    }

    /**
     * Define el valor de la propiedad fileRevisionInd.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFileRevisionInd(Boolean value) {
        this.fileRevisionInd = value;
    }

    /**
     * Obtiene el valor de la propiedad originalCTSTransmissionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalCTSTransmissionId() {
        return originalCTSTransmissionId;
    }

    /**
     * Define el valor de la propiedad originalCTSTransmissionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalCTSTransmissionId(String value) {
        this.originalCTSTransmissionId = value;
    }

    /**
     * Obtiene el valor de la propiedad senderContactEmailAddressTxt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderContactEmailAddressTxt() {
        return senderContactEmailAddressTxt;
    }

    /**
     * Define el valor de la propiedad senderContactEmailAddressTxt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderContactEmailAddressTxt(String value) {
        this.senderContactEmailAddressTxt = value;
    }

}
