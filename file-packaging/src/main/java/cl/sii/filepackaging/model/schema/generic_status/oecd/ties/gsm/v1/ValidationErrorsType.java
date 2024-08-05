//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para ValidationErrors_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidationErrors_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FileError" type="{urn:oecd:ties:gsm:v1}FileError_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RecordError" type="{urn:oecd:ties:gsm:v1}RecordError_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationErrors_Type", propOrder = {
    "fileError",
    "recordError"
})
public class ValidationErrorsType {

    @XmlElement(name = "FileError")
    protected List<FileErrorType> fileError;
    @XmlElement(name = "RecordError")
    protected List<RecordErrorType> recordError;

    /**
     * Gets the value of the fileError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileErrorType }
     * 
     * 
     */
    public List<FileErrorType> getFileError() {
        if (fileError == null) {
            fileError = new ArrayList<FileErrorType>();
        }
        return this.fileError;
    }

    /**
     * Gets the value of the recordError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecordErrorType }
     * 
     * 
     */
    public List<RecordErrorType> getRecordError() {
        if (recordError == null) {
            recordError = new ArrayList<RecordErrorType>();
        }
        return this.recordError;
    }

}
