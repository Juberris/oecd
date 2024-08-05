//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.24 a las 04:41:47 PM CLT 
//


package cl.sii.filepackaging.model.schema.wrapper.oecd.ties.cfw.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MessageType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="MessageType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DTCAEOI"/&gt;
 *     &lt;enumeration value="EOIRFreeDT"/&gt;
 *     &lt;enumeration value="EOIRFreeIT"/&gt;
 *     &lt;enumeration value="EOIRFreeTCR"/&gt;
 *     &lt;enumeration value="EOIRStructDT"/&gt;
 *     &lt;enumeration value="EOIRStructIT"/&gt;
 *     &lt;enumeration value="EOIRStructTCR"/&gt;
 *     &lt;enumeration value="SponFreeDT"/&gt;
 *     &lt;enumeration value="SponFreeIT"/&gt;
 *     &lt;enumeration value="SponFreeTCR"/&gt;
 *     &lt;enumeration value="SponStructDT"/&gt;
 *     &lt;enumeration value="SponStructIT"/&gt;
 *     &lt;enumeration value="SponStructTCR"/&gt;
 *     &lt;enumeration value="JointAudits"/&gt;
 *     &lt;enumeration value="JITSIC"/&gt;
 *     &lt;enumeration value="MAP"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageType_EnumType")
@XmlEnum
public enum MessageTypeEnumType {


    /**
     * DTC AEOI
     * 
     */
    DTCAEOI("DTCAEOI"),

    /**
     * EOIR free format - Direct Tax
     * 
     */
    @XmlEnumValue("EOIRFreeDT")
    EOIR_FREE_DT("EOIRFreeDT"),

    /**
     * EOIR free format - Indirect Tax
     * 
     */
    @XmlEnumValue("EOIRFreeIT")
    EOIR_FREE_IT("EOIRFreeIT"),

    /**
     * EOIR free format - Tax collection and recovery
     * 
     */
    @XmlEnumValue("EOIRFreeTCR")
    EOIR_FREE_TCR("EOIRFreeTCR"),

    /**
     * EOIR structured format (e-forms) - Direct Tax
     * 
     */
    @XmlEnumValue("EOIRStructDT")
    EOIR_STRUCT_DT("EOIRStructDT"),

    /**
     * EOIR structured format (e-forms) - Indirect Tax
     * 
     */
    @XmlEnumValue("EOIRStructIT")
    EOIR_STRUCT_IT("EOIRStructIT"),

    /**
     * EOIR structured format (e-forms) - Tax collection and recovery
     * 
     */
    @XmlEnumValue("EOIRStructTCR")
    EOIR_STRUCT_TCR("EOIRStructTCR"),

    /**
     * Spontaneous exchanges free format - Direct Tax
     * 
     */
    @XmlEnumValue("SponFreeDT")
    SPON_FREE_DT("SponFreeDT"),

    /**
     * Spontaneous exchanges free format - Indirect Tax
     * 
     */
    @XmlEnumValue("SponFreeIT")
    SPON_FREE_IT("SponFreeIT"),

    /**
     * Spontaneous exchanges free format - Tax collection and recovery
     * 
     */
    @XmlEnumValue("SponFreeTCR")
    SPON_FREE_TCR("SponFreeTCR"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Direct Tax
     * 
     */
    @XmlEnumValue("SponStructDT")
    SPON_STRUCT_DT("SponStructDT"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Indirect Tax
     * 
     */
    @XmlEnumValue("SponStructIT")
    SPON_STRUCT_IT("SponStructIT"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Tax collection and recovery
     * 
     */
    @XmlEnumValue("SponStructTCR")
    SPON_STRUCT_TCR("SponStructTCR"),

    /**
     * Joint Audits
     * 
     */
    @XmlEnumValue("JointAudits")
    JOINT_AUDITS("JointAudits"),

    /**
     * JITSIC
     * 
     */
    JITSIC("JITSIC"),

    /**
     * MAP
     * 
     */
    MAP("MAP"),

    /**
     * Other exchanges under international tax agreements
     * 
     */
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    MessageTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageTypeEnumType fromValue(String v) {
        for (MessageTypeEnumType c: MessageTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
