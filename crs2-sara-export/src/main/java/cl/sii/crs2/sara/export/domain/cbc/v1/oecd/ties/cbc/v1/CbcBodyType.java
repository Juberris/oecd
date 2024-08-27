//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:46:42 PM CLT 
//


package cl.sii.crs2.sara.export.domain.cbc.v1.oecd.ties.cbc.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CbcBody_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CbcBody_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReportingEntity" type="{urn:oecd:ties:cbc:v1}CorrectableReportingEntity_Type" minOccurs="0"/&gt;
 *         &lt;element name="CbcReports" type="{urn:oecd:ties:cbc:v1}CorrectableCbcReport_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AdditionalInfo" type="{urn:oecd:ties:cbc:v1}CorrectableAdditionalInfo_Type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CbcBody_Type", propOrder = {
    "reportingEntity",
    "cbcReports",
    "additionalInfo"
})
public class CbcBodyType {

    @XmlElement(name = "ReportingEntity")
    protected CorrectableReportingEntityType reportingEntity;
    @XmlElement(name = "CbcReports")
    protected List<CorrectableCbcReportType> cbcReports;
    @XmlElement(name = "AdditionalInfo")
    protected List<CorrectableAdditionalInfoType> additionalInfo;

    /**
     * Obtiene el valor de la propiedad reportingEntity.
     * 
     * @return
     *     possible object is
     *     {@link CorrectableReportingEntityType }
     *     
     */
    public CorrectableReportingEntityType getReportingEntity() {
        return reportingEntity;
    }

    /**
     * Define el valor de la propiedad reportingEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrectableReportingEntityType }
     *     
     */
    public void setReportingEntity(CorrectableReportingEntityType value) {
        this.reportingEntity = value;
    }

    /**
     * Gets the value of the cbcReports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cbcReports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCbcReports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorrectableCbcReportType }
     * 
     * 
     */
    public List<CorrectableCbcReportType> getCbcReports() {
        if (cbcReports == null) {
            cbcReports = new ArrayList<CorrectableCbcReportType>();
        }
        return this.cbcReports;
    }

    /**
     * Gets the value of the additionalInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorrectableAdditionalInfoType }
     * 
     * 
     */
    public List<CorrectableAdditionalInfoType> getAdditionalInfo() {
        if (additionalInfo == null) {
            additionalInfo = new ArrayList<CorrectableAdditionalInfoType>();
        }
        return this.additionalInfo;
    }

}
