//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.crs.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.stf.v4.DocSpecType;


/**
 * <p>Clase Java para CorrectableOrganisationParty_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CorrectableOrganisationParty_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oecd:ties:crs:v1}OrganisationParty_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DocSpec" type="{urn:oecd:ties:stf:v4}DocSpec_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectableOrganisationParty_Type", propOrder = {
    "docSpec"
})
public class CorrectableOrganisationPartyType
    extends OrganisationPartyType
{

    @XmlElement(name = "DocSpec", required = true)
    protected DocSpecType docSpec;

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

}
