//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.stf.v4;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OECDNameType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="OECDNameType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OECD201"/&gt;
 *     &lt;enumeration value="OECD202"/&gt;
 *     &lt;enumeration value="OECD203"/&gt;
 *     &lt;enumeration value="OECD204"/&gt;
 *     &lt;enumeration value="OECD205"/&gt;
 *     &lt;enumeration value="OECD206"/&gt;
 *     &lt;enumeration value="OECD207"/&gt;
 *     &lt;enumeration value="OECD208"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OECDNameType_EnumType")
@XmlEnum
public enum OECDNameTypeEnumType {


    /**
     * SMFAliasOrOther
     * 
     */
    @XmlEnumValue("OECD201")
    OECD_201("OECD201"),

    /**
     * indiv (individual)
     * 
     */
    @XmlEnumValue("OECD202")
    OECD_202("OECD202"),

    /**
     * alias (alias)
     * 
     */
    @XmlEnumValue("OECD203")
    OECD_203("OECD203"),

    /**
     * nick (nickname)
     * 
     */
    @XmlEnumValue("OECD204")
    OECD_204("OECD204"),

    /**
     * aka (also known as)
     * 
     */
    @XmlEnumValue("OECD205")
    OECD_205("OECD205"),

    /**
     * dba (doing business as)
     * 
     */
    @XmlEnumValue("OECD206")
    OECD_206("OECD206"),

    /**
     * legal (legal name)
     * 
     */
    @XmlEnumValue("OECD207")
    OECD_207("OECD207"),

    /**
     * atbirth (name at birth)
     * 
     */
    @XmlEnumValue("OECD208")
    OECD_208("OECD208");
    private final String value;

    OECDNameTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OECDNameTypeEnumType fromValue(String v) {
        for (OECDNameTypeEnumType c: OECDNameTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
