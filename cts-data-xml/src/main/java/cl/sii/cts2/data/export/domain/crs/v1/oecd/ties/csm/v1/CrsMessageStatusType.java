//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.csm.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrsMessageStatus_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrsMessageStatus_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OriginalMessage" type="{urn:oecd:ties:csm:v1}OriginalMessage_Type"/&gt;
 *         &lt;element name="ValidationErrors" type="{urn:oecd:ties:csm:v1}ValidationErrors_Type"/&gt;
 *         &lt;element name="ValidationResult" type="{urn:oecd:ties:csm:v1}ValidationResult_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrsMessageStatus_Type", propOrder = {
    "originalMessage",
    "validationErrors",
    "validationResult"
})
public class CrsMessageStatusType {

    @XmlElement(name = "OriginalMessage", required = true)
    protected OriginalMessageType originalMessage;
    @XmlElement(name = "ValidationErrors", required = true)
    protected ValidationErrorsType validationErrors;
    @XmlElement(name = "ValidationResult", required = true)
    protected ValidationResultType validationResult;

    /**
     * Obtiene el valor de la propiedad originalMessage.
     * 
     * @return
     *     possible object is
     *     {@link OriginalMessageType }
     *     
     */
    public OriginalMessageType getOriginalMessage() {
        return originalMessage;
    }

    /**
     * Define el valor de la propiedad originalMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalMessageType }
     *     
     */
    public void setOriginalMessage(OriginalMessageType value) {
        this.originalMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad validationErrors.
     * 
     * @return
     *     possible object is
     *     {@link ValidationErrorsType }
     *     
     */
    public ValidationErrorsType getValidationErrors() {
        return validationErrors;
    }

    /**
     * Define el valor de la propiedad validationErrors.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationErrorsType }
     *     
     */
    public void setValidationErrors(ValidationErrorsType value) {
        this.validationErrors = value;
    }

    /**
     * Obtiene el valor de la propiedad validationResult.
     * 
     * @return
     *     possible object is
     *     {@link ValidationResultType }
     *     
     */
    public ValidationResultType getValidationResult() {
        return validationResult;
    }

    /**
     * Define el valor de la propiedad validationResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationResultType }
     *     
     */
    public void setValidationResult(ValidationResultType value) {
        this.validationResult = value;
    }

}
