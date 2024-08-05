//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrsAcctHolderType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CrsAcctHolderType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CRS101"/&gt;
 *     &lt;enumeration value="CRS102"/&gt;
 *     &lt;enumeration value="CRS103"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CrsAcctHolderType_EnumType")
@XmlEnum
public enum CrsAcctHolderTypeEnumType {


    /**
     * Passive Non-Financial Entity with one or more controlling person that is a Reportable Person
     * 
     */
    @XmlEnumValue("CRS101")
    CRS_101("CRS101"),

    /**
     * CRS Reportable Person
     * 
     */
    @XmlEnumValue("CRS102")
    CRS_102("CRS102"),

    /**
     * Passive NFE that is a CRS Reportable Person
     * 
     */
    @XmlEnumValue("CRS103")
    CRS_103("CRS103");
    private final String value;

    CrsAcctHolderTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrsAcctHolderTypeEnumType fromValue(String v) {
        for (CrsAcctHolderTypeEnumType c: CrsAcctHolderTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
