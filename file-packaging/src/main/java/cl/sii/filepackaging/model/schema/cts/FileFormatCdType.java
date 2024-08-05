//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:24 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FileFormatCdType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="FileFormatCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="XML"/>
 *     &lt;enumeration value="TXT"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="RTF"/>
 *     &lt;enumeration value="JPG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FileFormatCdType")
@XmlEnum
public enum FileFormatCdType {


    /**
     * XML
     * 
     */
    XML,

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
    JPG;

    public String value() {
        return name();
    }

    public static FileFormatCdType fromValue(String v) {
        return valueOf(v);
    }

}
