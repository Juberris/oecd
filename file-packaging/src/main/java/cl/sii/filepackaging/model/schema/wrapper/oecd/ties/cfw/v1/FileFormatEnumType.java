//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.24 a las 04:41:47 PM CLT 
//


package cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FileFormat_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="FileFormat_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TXT"/&gt;
 *     &lt;enumeration value="PDF"/&gt;
 *     &lt;enumeration value="RTF"/&gt;
 *     &lt;enumeration value="JPG"/&gt;
 *     &lt;enumeration value="DOC"/&gt;
 *     &lt;enumeration value="XLS"/&gt;
 *     &lt;enumeration value="DOCX"/&gt;
 *     &lt;enumeration value="XLSX"/&gt;
 *     &lt;enumeration value="STF"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FileFormat_EnumType")
@XmlEnum
public enum FileFormatEnumType {


    /**
     * Plain Text
     * 
     */
    TXT,

    /**
     * PDF
     * 
     */
    PDF,

    /**
     * Rich Text Format
     * 
     */
    RTF,

    /**
     * Picture in JPEG Format
     * 
     */
    JPG,

    /**
     * Word
     * 
     */
    DOC,

    /**
     * Excel
     * 
     */
    XLS,

    /**
     * Word 2016 plus
     * 
     */
    DOCX,

    /**
     * Excel 2016 plus
     * 
     */
    XLSX,

    /**
     * STF
     * 
     */
    STF;

    public String value() {
        return name();
    }

    public static FileFormatEnumType fromValue(String v) {
        return valueOf(v);
    }

}
