//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.24 a las 04:41:47 PM CLT 
//


package cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FileAttach_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FileAttach_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FileName" type="{urn:oecd:ties:cfw:v1}StringMin1Max255_Type"/&gt;
 *         &lt;element name="FileFormatCd" type="{urn:oecd:ties:cfw:v1}FileFormat_EnumType"/&gt;
 *         &lt;element name="BinaryEncodingSchemeCd" type="{urn:oecd:ties:cfw:v1}BinaryEncodingScheme_EnumType"/&gt;
 *         &lt;element name="FileWrapper" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileAttach_Type", propOrder = {
    "fileName",
    "fileFormatCd",
    "binaryEncodingSchemeCd",
    "fileWrapper"
})
public class FileAttachType {

    @XmlElement(name = "FileName", required = true)
    protected String fileName;
    @XmlElement(name = "FileFormatCd", required = true)
    @XmlSchemaType(name = "string")
    protected FileFormatEnumType fileFormatCd;
    @XmlElement(name = "BinaryEncodingSchemeCd", required = true)
    @XmlSchemaType(name = "string")
    protected BinaryEncodingSchemeEnumType binaryEncodingSchemeCd;
    @XmlElement(name = "FileWrapper", required = true)
    protected String fileWrapper;

    /**
     * Obtiene el valor de la propiedad fileName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Define el valor de la propiedad fileName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Obtiene el valor de la propiedad fileFormatCd.
     * 
     * @return
     *     possible object is
     *     {@link FileFormatEnumType }
     *     
     */
    public FileFormatEnumType getFileFormatCd() {
        return fileFormatCd;
    }

    /**
     * Define el valor de la propiedad fileFormatCd.
     * 
     * @param value
     *     allowed object is
     *     {@link FileFormatEnumType }
     *     
     */
    public void setFileFormatCd(FileFormatEnumType value) {
        this.fileFormatCd = value;
    }

    /**
     * Obtiene el valor de la propiedad binaryEncodingSchemeCd.
     * 
     * @return
     *     possible object is
     *     {@link BinaryEncodingSchemeEnumType }
     *     
     */
    public BinaryEncodingSchemeEnumType getBinaryEncodingSchemeCd() {
        return binaryEncodingSchemeCd;
    }

    /**
     * Define el valor de la propiedad binaryEncodingSchemeCd.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryEncodingSchemeEnumType }
     *     
     */
    public void setBinaryEncodingSchemeCd(BinaryEncodingSchemeEnumType value) {
        this.binaryEncodingSchemeCd = value;
    }

    /**
     * Obtiene el valor de la propiedad fileWrapper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileWrapper() {
        return fileWrapper;
    }

    /**
     * Define el valor de la propiedad fileWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileWrapper(String value) {
        this.fileWrapper = value;
    }

}
