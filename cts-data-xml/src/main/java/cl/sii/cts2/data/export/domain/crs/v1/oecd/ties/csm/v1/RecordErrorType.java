//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.csm.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RecordError_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RecordError_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Code" type="{urn:oecd:ties:csm:v1}StringMax10Type"/&gt;
 *         &lt;element name="Details" type="{urn:oecd:ties:csm:v1}ErrorDetail_Type" minOccurs="0"/&gt;
 *         &lt;element name="DocRefIDInError" type="{urn:oecd:ties:csm:v1}StringMax200Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="FieldsInError" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="FieldPath" type="{urn:oecd:ties:csm:v1}StringMax400Type"/&gt;
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
@XmlType(name = "RecordError_Type", propOrder = {
    "code",
    "details",
    "docRefIDInError",
    "fieldsInError"
})
public class RecordErrorType {

    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "Details")
    protected ErrorDetailType details;
    @XmlElement(name = "DocRefIDInError")
    protected List<String> docRefIDInError;
    @XmlElement(name = "FieldsInError")
    protected List<FieldsInError> fieldsInError;

    /**
     * Obtiene el valor de la propiedad code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Define el valor de la propiedad code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Obtiene el valor de la propiedad details.
     * 
     * @return
     *     possible object is
     *     {@link ErrorDetailType }
     *     
     */
    public ErrorDetailType getDetails() {
        return details;
    }

    /**
     * Define el valor de la propiedad details.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorDetailType }
     *     
     */
    public void setDetails(ErrorDetailType value) {
        this.details = value;
    }

    /**
     * Gets the value of the docRefIDInError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docRefIDInError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocRefIDInError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDocRefIDInError() {
        if (docRefIDInError == null) {
            docRefIDInError = new ArrayList<String>();
        }
        return this.docRefIDInError;
    }

    /**
     * Gets the value of the fieldsInError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldsInError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldsInError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldsInError }
     * 
     * 
     */
    public List<FieldsInError> getFieldsInError() {
        if (fieldsInError == null) {
            fieldsInError = new ArrayList<FieldsInError>();
        }
        return this.fieldsInError;
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
     *         &lt;element name="FieldPath" type="{urn:oecd:ties:csm:v1}StringMax400Type"/&gt;
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
        "fieldPath"
    })
    public static class FieldsInError {

        @XmlElement(name = "FieldPath", required = true)
        protected String fieldPath;

        /**
         * Obtiene el valor de la propiedad fieldPath.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFieldPath() {
            return fieldPath;
        }

        /**
         * Define el valor de la propiedad fieldPath.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFieldPath(String value) {
            this.fieldPath = value;
        }

    }

}
