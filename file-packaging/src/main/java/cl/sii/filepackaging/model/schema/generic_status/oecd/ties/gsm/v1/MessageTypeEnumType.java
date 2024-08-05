//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

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
 *     &lt;enumeration value="CRSStatus"/&gt;
 *     &lt;enumeration value="CBCStatus"/&gt;
 *     &lt;enumeration value="ETRStatus"/&gt;
 *     &lt;enumeration value="DTCAEOIStatus"/&gt;
 *     &lt;enumeration value="MDRStatus"/&gt;
 *     &lt;enumeration value="NTJStatus"/&gt;
 *     &lt;enumeration value="CDQStatus"/&gt;
 *     &lt;enumeration value="DPIStatus"/&gt;
 *     &lt;enumeration value="EOIRFreeDTStatus"/&gt;
 *     &lt;enumeration value="EOIRFreeITStatus"/&gt;
 *     &lt;enumeration value="EOIRFreeTCRStatus"/&gt;
 *     &lt;enumeration value="EOIRStructDTStatus"/&gt;
 *     &lt;enumeration value="EOIRStructITStatus"/&gt;
 *     &lt;enumeration value="EOIRStructTCRStatus"/&gt;
 *     &lt;enumeration value="SponFreeDTStatus"/&gt;
 *     &lt;enumeration value="SponFreeITStatus"/&gt;
 *     &lt;enumeration value="SponFreeTCRStatus"/&gt;
 *     &lt;enumeration value="SponStructDTStatus"/&gt;
 *     &lt;enumeration value="SponStructITStatus"/&gt;
 *     &lt;enumeration value="SponStructTCRStatus"/&gt;
 *     &lt;enumeration value="JointAuditsStatus"/&gt;
 *     &lt;enumeration value="JITSICStatus"/&gt;
 *     &lt;enumeration value="MAPStatus"/&gt;
 *     &lt;enumeration value="TRACEStatus"/&gt;
 *     &lt;enumeration value="OtherStatus"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessageType_EnumType")
@XmlEnum
public enum MessageTypeEnumType {


    /**
     * CRS Status Message
     * 
     */
    @XmlEnumValue("CRSStatus")
    CRS_STATUS("CRSStatus"),

    /**
     * CbC Status Message
     * 
     */
    @XmlEnumValue("CBCStatus")
    CBC_STATUS("CBCStatus"),

    /**
     * ETR Status Message
     * 
     */
    @XmlEnumValue("ETRStatus")
    ETR_STATUS("ETRStatus"),

    /**
     * DTC AEOI Status message
     * 
     */
    @XmlEnumValue("DTCAEOIStatus")
    DTCAEOI_STATUS("DTCAEOIStatus"),

    /**
     * MDR Status message
     * 
     */
    @XmlEnumValue("MDRStatus")
    MDR_STATUS("MDRStatus"),

    /**
     * FHTP NoNom exchanges Status message
     * 
     */
    @XmlEnumValue("NTJStatus")
    NTJ_STATUS("NTJStatus"),

    /**
     * CRS Data Quality Status message
     * 
     */
    @XmlEnumValue("CDQStatus")
    CDQ_STATUS("CDQStatus"),

    /**
     * Model Reporting Rules for Digital Platforms Status message
     * 
     */
    @XmlEnumValue("DPIStatus")
    DPI_STATUS("DPIStatus"),

    /**
     * EOIR free format - Direct Tax Status message
     * 
     */
    @XmlEnumValue("EOIRFreeDTStatus")
    EOIR_FREE_DT_STATUS("EOIRFreeDTStatus"),

    /**
     * EOIR free format - Indirect Tax Status message
     * 
     */
    @XmlEnumValue("EOIRFreeITStatus")
    EOIR_FREE_IT_STATUS("EOIRFreeITStatus"),

    /**
     * EOIR free format - Tax collection and recovery Status message
     * 
     */
    @XmlEnumValue("EOIRFreeTCRStatus")
    EOIR_FREE_TCR_STATUS("EOIRFreeTCRStatus"),

    /**
     * EOIR structured format (e-forms) - Direct Tax Status message
     * 
     */
    @XmlEnumValue("EOIRStructDTStatus")
    EOIR_STRUCT_DT_STATUS("EOIRStructDTStatus"),

    /**
     * EOIR structured format (e-forms) - Indirect Tax Status message
     * 
     */
    @XmlEnumValue("EOIRStructITStatus")
    EOIR_STRUCT_IT_STATUS("EOIRStructITStatus"),

    /**
     * EOIR structured format (e-forms) - Tax collection and recovery Status message
     * 
     */
    @XmlEnumValue("EOIRStructTCRStatus")
    EOIR_STRUCT_TCR_STATUS("EOIRStructTCRStatus"),

    /**
     * Spontaneous exchanges free format - Direct Tax Status message
     * 
     */
    @XmlEnumValue("SponFreeDTStatus")
    SPON_FREE_DT_STATUS("SponFreeDTStatus"),

    /**
     * Spontaneous exchanges free format - Indirect Tax Status message
     * 
     */
    @XmlEnumValue("SponFreeITStatus")
    SPON_FREE_IT_STATUS("SponFreeITStatus"),

    /**
     * Spontaneous exchanges free format - Tax collection and recovery Status message
     * 
     */
    @XmlEnumValue("SponFreeTCRStatus")
    SPON_FREE_TCR_STATUS("SponFreeTCRStatus"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Direct Tax Status message
     * 
     */
    @XmlEnumValue("SponStructDTStatus")
    SPON_STRUCT_DT_STATUS("SponStructDTStatus"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Indirect Tax Status message
     * 
     */
    @XmlEnumValue("SponStructITStatus")
    SPON_STRUCT_IT_STATUS("SponStructITStatus"),

    /**
     * Spontaneous exchanges structured format (e-forms) - Tax collection and recovery Status message
     * 
     */
    @XmlEnumValue("SponStructTCRStatus")
    SPON_STRUCT_TCR_STATUS("SponStructTCRStatus"),

    /**
     * Joint Audits Status message
     * 
     */
    @XmlEnumValue("JointAuditsStatus")
    JOINT_AUDITS_STATUS("JointAuditsStatus"),

    /**
     * JITSIC Status message
     * 
     */
    @XmlEnumValue("JITSICStatus")
    JITSIC_STATUS("JITSICStatus"),

    /**
     * MAP Status message
     * 
     */
    @XmlEnumValue("MAPStatus")
    MAP_STATUS("MAPStatus"),

    /**
     * TRACE Status message
     * 
     */
    @XmlEnumValue("TRACEStatus")
    TRACE_STATUS("TRACEStatus"),

    /**
     * Other exchanges under international tax agreements Status message
     * 
     */
    @XmlEnumValue("OtherStatus")
    OTHER_STATUS("OtherStatus");
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
