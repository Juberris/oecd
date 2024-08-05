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
 * <p>Clase Java para CrsCtrlgPersonType_EnumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="CrsCtrlgPersonType_EnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CRS801"/&gt;
 *     &lt;enumeration value="CRS802"/&gt;
 *     &lt;enumeration value="CRS803"/&gt;
 *     &lt;enumeration value="CRS804"/&gt;
 *     &lt;enumeration value="CRS805"/&gt;
 *     &lt;enumeration value="CRS806"/&gt;
 *     &lt;enumeration value="CRS807"/&gt;
 *     &lt;enumeration value="CRS808"/&gt;
 *     &lt;enumeration value="CRS809"/&gt;
 *     &lt;enumeration value="CRS810"/&gt;
 *     &lt;enumeration value="CRS811"/&gt;
 *     &lt;enumeration value="CRS812"/&gt;
 *     &lt;enumeration value="CRS813"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CrsCtrlgPersonType_EnumType")
@XmlEnum
public enum CrsCtrlgPersonTypeEnumType {


    /**
     * CP of legal person - ownership
     * 
     */
    @XmlEnumValue("CRS801")
    CRS_801("CRS801"),

    /**
     * CP of legal person - other means
     * 
     */
    @XmlEnumValue("CRS802")
    CRS_802("CRS802"),

    /**
     * CP of legal person - senior managing official
     * 
     */
    @XmlEnumValue("CRS803")
    CRS_803("CRS803"),

    /**
     * CP of legal arrangement - trust - settlor
     * 
     */
    @XmlEnumValue("CRS804")
    CRS_804("CRS804"),

    /**
     * CP of legal arrangement - trust - trustee
     * 
     */
    @XmlEnumValue("CRS805")
    CRS_805("CRS805"),

    /**
     * CP of legal arrangement - trust - protector
     * 
     */
    @XmlEnumValue("CRS806")
    CRS_806("CRS806"),

    /**
     * CP of legal arrangement - trust - beneficiary
     * 
     */
    @XmlEnumValue("CRS807")
    CRS_807("CRS807"),

    /**
     * CP of legal arrangement - trust - other
     * 
     */
    @XmlEnumValue("CRS808")
    CRS_808("CRS808"),

    /**
     * CP of legal arrangement - other - settlor-equivalent
     * 
     */
    @XmlEnumValue("CRS809")
    CRS_809("CRS809"),

    /**
     * CP of legal arrangement - other - trustee-equivalent
     * 
     */
    @XmlEnumValue("CRS810")
    CRS_810("CRS810"),

    /**
     * CP of legal arrangement - other - protector-equivalent
     * 
     */
    @XmlEnumValue("CRS811")
    CRS_811("CRS811"),

    /**
     * CP of legal arrangement - other - beneficiary-equivalent
     * 
     */
    @XmlEnumValue("CRS812")
    CRS_812("CRS812"),

    /**
     * CP of legal arrangement - other - other-equivalent
     * 
     */
    @XmlEnumValue("CRS813")
    CRS_813("CRS813");
    private final String value;

    CrsCtrlgPersonTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrsCtrlgPersonTypeEnumType fromValue(String v) {
        for (CrsCtrlgPersonTypeEnumType c: CrsCtrlgPersonTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
