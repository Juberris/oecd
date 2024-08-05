//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para ValidationResult_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidationResult_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" type="{urn:oecd:ties:gsm:v1}FileAcceptanceStatus_EnumType"/&gt;
 *         &lt;element name="ValidatedBy" type="{urn:oecd:ties:gsm:v1}StringMin1Max400_Type" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationResult_Type", propOrder = {
    "status",
    "validatedBy"
})
public class ValidationResultType {

    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected FileAcceptanceStatusEnumType status;
    @XmlElement(name = "ValidatedBy", required = true)
    protected List<String> validatedBy;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link FileAcceptanceStatusEnumType }
     *     
     */
    public FileAcceptanceStatusEnumType getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link FileAcceptanceStatusEnumType }
     *     
     */
    public void setStatus(FileAcceptanceStatusEnumType value) {
        this.status = value;
    }

    /**
     * Gets the value of the validatedBy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validatedBy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidatedBy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidatedBy() {
        if (validatedBy == null) {
            validatedBy = new ArrayList<String>();
        }
        return this.validatedBy;
    }

}
