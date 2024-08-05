//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:24 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CTSCommunicationTypeCdType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="CTSCommunicationTypeCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CRS"/>
 *     &lt;enumeration value="CRSStatus"/>
 *     &lt;enumeration value="ETR"/>
 *     &lt;enumeration value="ETRStatus"/>
 *     &lt;enumeration value="CBC"/>
 *     &lt;enumeration value="CBCStatus"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CTSCommunicationTypeCdType")
@XmlEnum
public enum CTSCommunicationTypeCdType {


    /**
     * CRS Report
     * 
     */
    CRS("CRS"),

    /**
     * CRS Status Message
     * 
     */
    @XmlEnumValue("CRSStatus")
    CRS_STATUS("CRSStatus"),

    /**
     * ETR Report
     * 
     */
    ETR("ETR"),

    /**
     * ETR Status Message
     * 
     */
    @XmlEnumValue("ETRStatus")
    ETR_STATUS("ETRStatus"),

    /**
     * CbC Report
     * 
     */
    CBC("CBC"),

    /**
     * CbC Status Message
     * 
     */
    @XmlEnumValue("CBCStatus")
    CBC_STATUS("CBCStatus");
    private final String value;

    CTSCommunicationTypeCdType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CTSCommunicationTypeCdType fromValue(String v) {
        for (CTSCommunicationTypeCdType c: CTSCommunicationTypeCdType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
