//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crsstf.v5;

import jakarta.xml.bind.annotation.*;


/**
 * Document specification: Data identifying and describing the document, where
 * 'document' here means the part of a message that is to transmit the data about a single block of CRS information. 
 * 
 * <p>Clase Java para DocSpec_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocSpec_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocTypeIndic" type="{urn:oecd:ties:crsstf:v5}OECDDocTypeIndic_EnumType"/&gt;
 *         &lt;element name="DocRefId" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type"/&gt;
 *         &lt;element name="CorrMessageRefId" type="{urn:oecd:ties:crsstf:v5}StringMin1Max170_Type" minOccurs="0"/&gt;
 *         &lt;element name="CorrDocRefId" type="{urn:oecd:ties:crsstf:v5}StringMin1Max200_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocSpec_Type", propOrder = {
    "docTypeIndic",
    "docRefId",
    "corrMessageRefId",
    "corrDocRefId"
})
public class DocSpecType {

    @XmlElement(name = "DocTypeIndic", required = true)
    @XmlSchemaType(name = "string")
    protected OECDDocTypeIndicEnumType docTypeIndic;
    @XmlElement(name = "DocRefId", required = true)
    protected String docRefId;
    @XmlElement(name = "CorrMessageRefId")
    protected String corrMessageRefId;
    @XmlElement(name = "CorrDocRefId")
    protected String corrDocRefId;

    /**
     * Obtiene el valor de la propiedad docTypeIndic.
     * 
     * @return
     *     possible object is
     *     {@link OECDDocTypeIndicEnumType }
     *     
     */
    public OECDDocTypeIndicEnumType getDocTypeIndic() {
        return docTypeIndic;
    }

    /**
     * Define el valor de la propiedad docTypeIndic.
     * 
     * @param value
     *     allowed object is
     *     {@link OECDDocTypeIndicEnumType }
     *     
     */
    public void setDocTypeIndic(OECDDocTypeIndicEnumType value) {
        this.docTypeIndic = value;
    }

    /**
     * Obtiene el valor de la propiedad docRefId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocRefId() {
        return docRefId;
    }

    /**
     * Define el valor de la propiedad docRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocRefId(String value) {
        this.docRefId = value;
    }

    /**
     * Obtiene el valor de la propiedad corrMessageRefId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrMessageRefId() {
        return corrMessageRefId;
    }

    /**
     * Define el valor de la propiedad corrMessageRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrMessageRefId(String value) {
        this.corrMessageRefId = value;
    }

    /**
     * Obtiene el valor de la propiedad corrDocRefId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrDocRefId() {
        return corrDocRefId;
    }

    /**
     * Define el valor de la propiedad corrDocRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrDocRefId(String value) {
        this.corrDocRefId = value;
    }

}
