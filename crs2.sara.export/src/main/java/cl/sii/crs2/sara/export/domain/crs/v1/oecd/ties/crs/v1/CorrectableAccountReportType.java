//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.MonAmntType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.stf.v4.DocSpecType;


/**
 * <p>Clase Java para CorrectableAccountReport_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CorrectableAccountReport_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocSpec" type="{urn:oecd:ties:stf:v4}DocSpec_Type"/&gt;
 *         &lt;element name="AccountNumber" type="{urn:oecd:ties:crs:v1}FIAccountNumber_Type"/&gt;
 *         &lt;element name="AccountHolder" type="{urn:oecd:ties:crs:v1}AccountHolder_Type"/&gt;
 *         &lt;element name="ControllingPerson" type="{urn:oecd:ties:crs:v1}ControllingPerson_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AccountBalance" type="{urn:oecd:ties:commontypesfatcacrs:v1}MonAmnt_Type"/&gt;
 *         &lt;element name="Payment" type="{urn:oecd:ties:crs:v1}Payment_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectableAccountReport_Type", propOrder = {
    "docSpec",
    "accountNumber",
    "accountHolder",
    "controllingPerson",
    "accountBalance",
    "payment"
})
public class CorrectableAccountReportType {

    @XmlElement(name = "DocSpec", required = true)
    protected DocSpecType docSpec;
    @XmlElement(name = "AccountNumber", required = true)
    protected FIAccountNumberType accountNumber;
    @XmlElement(name = "AccountHolder", required = true)
    protected AccountHolderType accountHolder;
    @XmlElement(name = "ControllingPerson")
    protected List<ControllingPersonType> controllingPerson;
    @XmlElement(name = "AccountBalance", required = true)
    protected MonAmntType accountBalance;
    @XmlElement(name = "Payment")
    protected List<PaymentType> payment;

    /**
     * Obtiene el valor de la propiedad docSpec.
     * 
     * @return
     *     possible object is
     *     {@link DocSpecType }
     *     
     */
    public DocSpecType getDocSpec() {
        return docSpec;
    }

    /**
     * Define el valor de la propiedad docSpec.
     * 
     * @param value
     *     allowed object is
     *     {@link DocSpecType }
     *     
     */
    public void setDocSpec(DocSpecType value) {
        this.docSpec = value;
    }

    /**
     * Obtiene el valor de la propiedad accountNumber.
     * 
     * @return
     *     possible object is
     *     {@link FIAccountNumberType }
     *     
     */
    public FIAccountNumberType getAccountNumber() {
        return accountNumber;
    }

    /**
     * Define el valor de la propiedad accountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link FIAccountNumberType }
     *     
     */
    public void setAccountNumber(FIAccountNumberType value) {
        this.accountNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad accountHolder.
     * 
     * @return
     *     possible object is
     *     {@link AccountHolderType }
     *     
     */
    public AccountHolderType getAccountHolder() {
        return accountHolder;
    }

    /**
     * Define el valor de la propiedad accountHolder.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountHolderType }
     *     
     */
    public void setAccountHolder(AccountHolderType value) {
        this.accountHolder = value;
    }

    /**
     * Gets the value of the controllingPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controllingPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getControllingPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ControllingPersonType }
     * 
     * 
     */
    public List<ControllingPersonType> getControllingPerson() {
        if (controllingPerson == null) {
            controllingPerson = new ArrayList<ControllingPersonType>();
        }
        return this.controllingPerson;
    }

    /**
     * Obtiene el valor de la propiedad accountBalance.
     * 
     * @return
     *     possible object is
     *     {@link MonAmntType }
     *     
     */
    public MonAmntType getAccountBalance() {
        return accountBalance;
    }

    /**
     * Define el valor de la propiedad accountBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link MonAmntType }
     *     
     */
    public void setAccountBalance(MonAmntType value) {
        this.accountBalance = value;
    }

    /**
     * Gets the value of the payment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentType }
     * 
     * 
     */
    public List<PaymentType> getPayment() {
        if (payment == null) {
            payment = new ArrayList<PaymentType>();
        }
        return this.payment;
    }

}
