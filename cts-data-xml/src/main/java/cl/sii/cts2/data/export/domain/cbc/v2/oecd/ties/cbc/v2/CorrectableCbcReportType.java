//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.cbc.v2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.cbcstf.v5.DocSpecType;
import cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * <p>Clase Java para CorrectableCbcReport_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CorrectableCbcReport_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocSpec" type="{urn:oecd:ties:cbcstf:v5}DocSpec_Type"/&gt;
 *         &lt;element name="ResCountryCode" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type"/&gt;
 *         &lt;element name="Summary"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Revenues"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Unrelated" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                             &lt;element name="Related" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                             &lt;element name="Total" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ProfitOrLoss" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                   &lt;element name="TaxPaid" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                   &lt;element name="TaxAccrued" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                   &lt;element name="Capital" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                   &lt;element name="Earnings" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                   &lt;element name="NbEmployees" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="Assets" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ConstEntities" type="{urn:oecd:ties:cbc:v2}ConstituentEntity_Type" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectableCbcReport_Type", propOrder = {
    "docSpec",
    "resCountryCode",
    "summary",
    "constEntities"
})
public class CorrectableCbcReportType {

    @XmlElement(name = "DocSpec", required = true)
    protected DocSpecType docSpec;
    @XmlElement(name = "ResCountryCode", required = true)
    @XmlSchemaType(name = "string")
    protected CountryCodeType resCountryCode;
    @XmlElement(name = "Summary", required = true)
    protected CorrectableCbcReportType.Summary summary;
    @XmlElement(name = "ConstEntities", required = true)
    protected List<ConstituentEntityType> constEntities;

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
     * Obtiene el valor de la propiedad resCountryCode.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getResCountryCode() {
        return resCountryCode;
    }

    /**
     * Define el valor de la propiedad resCountryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setResCountryCode(CountryCodeType value) {
        this.resCountryCode = value;
    }

    /**
     * Obtiene el valor de la propiedad summary.
     * 
     * @return
     *     possible object is
     *     {@link Summary }
     *     
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * Define el valor de la propiedad summary.
     * 
     * @param value
     *     allowed object is
     *     {@link Summary }
     *     
     */
    public void setSummary(Summary value) {
        this.summary = value;
    }

    /**
     * Gets the value of the constEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstituentEntityType }
     * 
     * 
     */
    public List<ConstituentEntityType> getConstEntities() {
        if (constEntities == null) {
            constEntities = new ArrayList<ConstituentEntityType>();
        }
        return this.constEntities;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Revenues"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Unrelated" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *                   &lt;element name="Related" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *                   &lt;element name="Total" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ProfitOrLoss" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *         &lt;element name="TaxPaid" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *         &lt;element name="TaxAccrued" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *         &lt;element name="Capital" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *         &lt;element name="Earnings" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *         &lt;element name="NbEmployees" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="Assets" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "revenues",
        "profitOrLoss",
        "taxPaid",
        "taxAccrued",
        "capital",
        "earnings",
        "nbEmployees",
        "assets"
    })
    public static class Summary {

        @XmlElement(name = "Revenues", required = true)
        protected CorrectableCbcReportType.Summary.Revenues revenues;
        @XmlElement(name = "ProfitOrLoss", required = true)
        protected MonAmntType profitOrLoss;
        @XmlElement(name = "TaxPaid", required = true)
        protected MonAmntType taxPaid;
        @XmlElement(name = "TaxAccrued", required = true)
        protected MonAmntType taxAccrued;
        @XmlElement(name = "Capital", required = true)
        protected MonAmntType capital;
        @XmlElement(name = "Earnings", required = true)
        protected MonAmntType earnings;
        @XmlElement(name = "NbEmployees", required = true)
        protected BigInteger nbEmployees;
        @XmlElement(name = "Assets", required = true)
        protected MonAmntType assets;

        /**
         * Obtiene el valor de la propiedad revenues.
         * 
         * @return
         *     possible object is
         *     {@link Revenues }
         *     
         */
        public Revenues getRevenues() {
            return revenues;
        }

        /**
         * Define el valor de la propiedad revenues.
         * 
         * @param value
         *     allowed object is
         *     {@link Revenues }
         *     
         */
        public void setRevenues(Revenues value) {
            this.revenues = value;
        }

        /**
         * Obtiene el valor de la propiedad profitOrLoss.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getProfitOrLoss() {
            return profitOrLoss;
        }

        /**
         * Define el valor de la propiedad profitOrLoss.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setProfitOrLoss(MonAmntType value) {
            this.profitOrLoss = value;
        }

        /**
         * Obtiene el valor de la propiedad taxPaid.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getTaxPaid() {
            return taxPaid;
        }

        /**
         * Define el valor de la propiedad taxPaid.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setTaxPaid(MonAmntType value) {
            this.taxPaid = value;
        }

        /**
         * Obtiene el valor de la propiedad taxAccrued.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getTaxAccrued() {
            return taxAccrued;
        }

        /**
         * Define el valor de la propiedad taxAccrued.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setTaxAccrued(MonAmntType value) {
            this.taxAccrued = value;
        }

        /**
         * Obtiene el valor de la propiedad capital.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getCapital() {
            return capital;
        }

        /**
         * Define el valor de la propiedad capital.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setCapital(MonAmntType value) {
            this.capital = value;
        }

        /**
         * Obtiene el valor de la propiedad earnings.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getEarnings() {
            return earnings;
        }

        /**
         * Define el valor de la propiedad earnings.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setEarnings(MonAmntType value) {
            this.earnings = value;
        }

        /**
         * Obtiene el valor de la propiedad nbEmployees.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNbEmployees() {
            return nbEmployees;
        }

        /**
         * Define el valor de la propiedad nbEmployees.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNbEmployees(BigInteger value) {
            this.nbEmployees = value;
        }

        /**
         * Obtiene el valor de la propiedad assets.
         * 
         * @return
         *     possible object is
         *     {@link MonAmntType }
         *     
         */
        public MonAmntType getAssets() {
            return assets;
        }

        /**
         * Define el valor de la propiedad assets.
         * 
         * @param value
         *     allowed object is
         *     {@link MonAmntType }
         *     
         */
        public void setAssets(MonAmntType value) {
            this.assets = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="Unrelated" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
         *         &lt;element name="Related" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
         *         &lt;element name="Total" type="{urn:oecd:ties:cbc:v2}MonAmnt_Type"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "unrelated",
            "related",
            "total"
        })
        public static class Revenues {

            @XmlElement(name = "Unrelated", required = true)
            protected MonAmntType unrelated;
            @XmlElement(name = "Related", required = true)
            protected MonAmntType related;
            @XmlElement(name = "Total", required = true)
            protected MonAmntType total;

            /**
             * Obtiene el valor de la propiedad unrelated.
             * 
             * @return
             *     possible object is
             *     {@link MonAmntType }
             *     
             */
            public MonAmntType getUnrelated() {
                return unrelated;
            }

            /**
             * Define el valor de la propiedad unrelated.
             * 
             * @param value
             *     allowed object is
             *     {@link MonAmntType }
             *     
             */
            public void setUnrelated(MonAmntType value) {
                this.unrelated = value;
            }

            /**
             * Obtiene el valor de la propiedad related.
             * 
             * @return
             *     possible object is
             *     {@link MonAmntType }
             *     
             */
            public MonAmntType getRelated() {
                return related;
            }

            /**
             * Define el valor de la propiedad related.
             * 
             * @param value
             *     allowed object is
             *     {@link MonAmntType }
             *     
             */
            public void setRelated(MonAmntType value) {
                this.related = value;
            }

            /**
             * Obtiene el valor de la propiedad total.
             * 
             * @return
             *     possible object is
             *     {@link MonAmntType }
             *     
             */
            public MonAmntType getTotal() {
                return total;
            }

            /**
             * Define el valor de la propiedad total.
             * 
             * @param value
             *     allowed object is
             *     {@link MonAmntType }
             *     
             */
            public void setTotal(MonAmntType value) {
                this.total = value;
            }

        }

    }

}
