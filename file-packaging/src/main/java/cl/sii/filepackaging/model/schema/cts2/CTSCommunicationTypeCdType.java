//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:25 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts2;

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
 *     &lt;enumeration value="CBC"/>
 *     &lt;enumeration value="ETR"/>
 *     &lt;enumeration value="DTCAEOI"/>
 *     &lt;enumeration value="MDR"/>
 *     &lt;enumeration value="NTJ"/>
 *     &lt;enumeration value="CDQ"/>
 *     &lt;enumeration value="GIG"/>
 *     &lt;enumeration value="EOIRFreeDT"/>
 *     &lt;enumeration value="EOIRFreeIT"/>
 *     &lt;enumeration value="EOIRFreeTCR"/>
 *     &lt;enumeration value="EOIRStructDT"/>
 *     &lt;enumeration value="EOIRStructIT"/>
 *     &lt;enumeration value="EOIRStructTCR"/>
 *     &lt;enumeration value="SponFreeDT"/>
 *     &lt;enumeration value="SponFreeIT"/>
 *     &lt;enumeration value="SponFreeTCR"/>
 *     &lt;enumeration value="SponStructDT"/>
 *     &lt;enumeration value="SponStructIT"/>
 *     &lt;enumeration value="SponStructTCR"/>
 *     &lt;enumeration value="JointAudits"/>
 *     &lt;enumeration value="JITSIC"/>
 *     &lt;enumeration value="MAP"/>
 *     &lt;enumeration value="TRACE"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="CRSStatus"/>
 *     &lt;enumeration value="CBCStatus"/>
 *     &lt;enumeration value="ETRStatus"/>
 *     &lt;enumeration value="DTCAEOIStatus"/>
 *     &lt;enumeration value="MDRStatus"/>
 *     &lt;enumeration value="NTJStatus"/>
 *     &lt;enumeration value="CDQStatus"/>
 *     &lt;enumeration value="GIGStatus"/>
 *     &lt;enumeration value="EOIRFreeDTStatus"/>
 *     &lt;enumeration value="EOIRFreeITStatus"/>
 *     &lt;enumeration value="EOIRFreeTCRStatus"/>
 *     &lt;enumeration value="EOIRStructDTStatus"/>
 *     &lt;enumeration value="EOIRStructITStatus"/>
 *     &lt;enumeration value="EOIRStructTCRStatus"/>
 *     &lt;enumeration value="SponFreeDTStatus"/>
 *     &lt;enumeration value="SponFreeITStatus"/>
 *     &lt;enumeration value="SponFreeTCRStatus"/>
 *     &lt;enumeration value="SponStructDTStatus"/>
 *     &lt;enumeration value="SponStructITStatus"/>
 *     &lt;enumeration value="SponStructTCRStatus"/>
 *     &lt;enumeration value="JointAuditsStatus"/>
 *     &lt;enumeration value="JITSICStatus"/>
 *     &lt;enumeration value="MAPStatus"/>
 *     &lt;enumeration value="TRACEStatus"/>
 *     &lt;enumeration value="OtherStatus"/>
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
     * CbC Report
     * 
     */
    CBC("CBC"),

    /**
     * ETR Report
     * 
     */
    ETR("ETR"),

    /**
     * DTC AEOI
     * 
     */
    DTCAEOI("DTCAEOI"),

    /**
     * MDR
     * 
     */
    MDR("MDR"),

    /**
     * FHTP NoNom exchanges
     * 
     */
    NTJ("NTJ"),

    /**
     * CRS Data Quality
     * 
     */
    CDQ("CDQ"),

    /**
     * Gig economy
     * 
     */
    GIG("GIG"),

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
     * TRACE
     * 
     */
    TRACE("TRACE"),

    /**
     * Other exchanges under international tax agreements
     * 
     */
    @XmlEnumValue("Other")
    OTHER("Other"),

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
     * Gig economy Status message
     * 
     */
    @XmlEnumValue("GIGStatus")
    GIG_STATUS("GIGStatus"),

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
    
    @Override
    public String toString() {
        return value();
    }

}
