//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package com.example.domain.crs.v1.oecd.ties.crs.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.MonAmntType;


/**
 * <p>Clase Java para Payment_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Payment_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Type" type="{urn:oecd:ties:crs:v1}CrsPaymentType_EnumType"/&gt;
 *         &lt;element name="PaymentAmnt" type="{urn:oecd:ties:commontypesfatcacrs:v1}MonAmnt_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment_Type", propOrder = {
    "type",
    "paymentAmnt"
})
public class PaymentType {

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected CrsPaymentTypeEnumType type;
    @XmlElement(name = "PaymentAmnt", required = true)
    protected MonAmntType paymentAmnt;

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link CrsPaymentTypeEnumType }
     *     
     */
    public CrsPaymentTypeEnumType getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link CrsPaymentTypeEnumType }
     *     
     */
    public void setType(CrsPaymentTypeEnumType value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad paymentAmnt.
     * 
     * @return
     *     possible object is
     *     {@link MonAmntType }
     *     
     */
    public MonAmntType getPaymentAmnt() {
        return paymentAmnt;
    }

    /**
     * Define el valor de la propiedad paymentAmnt.
     * 
     * @param value
     *     allowed object is
     *     {@link MonAmntType }
     *     
     */
    public void setPaymentAmnt(MonAmntType value) {
        this.paymentAmnt = value;
    }

}
