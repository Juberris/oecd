//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.crs.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.AcctNumberTypeEnumType;


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
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="AcctNumberType" type="{urn:oecd:ties:commontypesfatcacrs:v1}AcctNumberType_EnumType" /&gt;
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
     * Obtiene el valor de la propiedad value.
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
