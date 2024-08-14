//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.fatca.v1;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1.MonAmntType;
import cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.stf.v4.DocSpecType;


/**
 * <p>Clase Java para CorrectablePoolReport_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CorrectablePoolReport_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocSpec" type="{urn:oecd:ties:stf:v4}DocSpec_Type"/&gt;
 *         &lt;element name="AccountCount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="AccountPoolReportType" type="{urn:oecd:ties:fatca:v1}FatcaAcctPoolReportType_EnumType"/&gt;
 *         &lt;element name="PoolBalance" type="{urn:oecd:ties:commontypesfatcacrs:v1}MonAmnt_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectablePoolReport_Type", propOrder = {
    "docSpec",
    "accountCount",
    "accountPoolReportType",
    "poolBalance"
})
public class CorrectablePoolReportType {

    @XmlElement(name = "DocSpec", required = true)
    protected DocSpecType docSpec;
    @XmlElement(name = "AccountCount", required = true)
    protected BigInteger accountCount;
    @XmlElement(name = "AccountPoolReportType", required = true)
    @XmlSchemaType(name = "string")
    protected FatcaAcctPoolReportTypeEnumType accountPoolReportType;
    @XmlElement(name = "PoolBalance", required = true)
    protected MonAmntType poolBalance;

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
     * Obtiene el valor de la propiedad accountCount.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAccountCount() {
        return accountCount;
    }

    /**
     * Define el valor de la propiedad accountCount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAccountCount(BigInteger value) {
        this.accountCount = value;
    }

    /**
     * Obtiene el valor de la propiedad accountPoolReportType.
     * 
     * @return
     *     possible object is
     *     {@link FatcaAcctPoolReportTypeEnumType }
     *     
     */
    public FatcaAcctPoolReportTypeEnumType getAccountPoolReportType() {
        return accountPoolReportType;
    }

    /**
     * Define el valor de la propiedad accountPoolReportType.
     * 
     * @param value
     *     allowed object is
     *     {@link FatcaAcctPoolReportTypeEnumType }
     *     
     */
    public void setAccountPoolReportType(FatcaAcctPoolReportTypeEnumType value) {
        this.accountPoolReportType = value;
    }

    /**
     * Obtiene el valor de la propiedad poolBalance.
     * 
     * @return
     *     possible object is
     *     {@link MonAmntType }
     *     
     */
    public MonAmntType getPoolBalance() {
        return poolBalance;
    }

    /**
     * Define el valor de la propiedad poolBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link MonAmntType }
     *     
     */
    public void setPoolBalance(MonAmntType value) {
        this.poolBalance = value;
    }

}
