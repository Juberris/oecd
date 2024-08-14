//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 03:45:25 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.commontypesfatcacrs.v2.AcctNumberTypeEnumType;

import jakarta.xml.bind.annotation.*;


/**
 * Account number definition  
 * 
 * <p>Clase Java para FIAccountNumber_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FIAccountNumber_Type"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:oecd:ties:crsstf:v5&gt;StringMin1Max200_Type"&gt;
 *       &lt;attribute name="AcctNumberType" type="{urn:oecd:ties:commontypesfatcacrs:v2}AcctNumberType_EnumType" /&gt;
 *       &lt;attribute name="UndocumentedAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="ClosedAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="DormantAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIAccountNumber_Type", propOrder = {
    "value"
})
public class FIAccountNumberType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "AcctNumberType")
    protected AcctNumberTypeEnumType acctNumberType;
    @XmlAttribute(name = "UndocumentedAccount")
    protected Boolean undocumentedAccount;
    @XmlAttribute(name = "ClosedAccount")
    protected Boolean closedAccount;
    @XmlAttribute(name = "DormantAccount")
    protected Boolean dormantAccount;

    /**
     * Defines a string with minimum length 1 and maximum length of 200
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad acctNumberType.
     * 
     * @return
     *     possible object is
     *     {@link AcctNumberTypeEnumType }
     *     
     */
    public AcctNumberTypeEnumType getAcctNumberType() {
        return acctNumberType;
    }

    /**
     * Define el valor de la propiedad acctNumberType.
     * 
     * @param value
     *     allowed object is
     *     {@link AcctNumberTypeEnumType }
     *     
     */
    public void setAcctNumberType(AcctNumberTypeEnumType value) {
        this.acctNumberType = value;
    }

    /**
     * Obtiene el valor de la propiedad undocumentedAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUndocumentedAccount() {
        return undocumentedAccount;
    }

    /**
     * Define el valor de la propiedad undocumentedAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUndocumentedAccount(Boolean value) {
        this.undocumentedAccount = value;
    }

    /**
     * Obtiene el valor de la propiedad closedAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClosedAccount() {
        return closedAccount;
    }

    /**
     * Define el valor de la propiedad closedAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClosedAccount(Boolean value) {
        this.closedAccount = value;
    }

    /**
     * Obtiene el valor de la propiedad dormantAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDormantAccount() {
        return dormantAccount;
    }

    /**
     * Define el valor de la propiedad dormantAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDormantAccount(Boolean value) {
        this.dormantAccount = value;
    }

}
