//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="MessageSpec" type="{urn:oecd:ties:gsm:v1}MessageSpec_Type"/&gt;
 *         &lt;element name="GenericStatusMessage" type="{urn:oecd:ties:gsm:v1}GenericMessageStatus_Type"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" type="{urn:oecd:ties:gsm:v1}StringMin1Max10_Type" fixed="2.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "messageSpec",
    "genericStatusMessage"
})
@XmlRootElement(name = "GenericStatusMessage_OECD")
public class GenericStatusMessageOECD {

    @XmlElement(name = "MessageSpec", required = true)
    protected MessageSpecType messageSpec;
    @XmlElement(name = "GenericStatusMessage", required = true)
    protected GenericMessageStatusType genericStatusMessage;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Obtiene el valor de la propiedad messageSpec.
     * 
     * @return
     *     possible object is
     *     {@link MessageSpecType }
     *     
     */
    public MessageSpecType getMessageSpec() {
        return messageSpec;
    }

    /**
     * Define el valor de la propiedad messageSpec.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageSpecType }
     *     
     */
    public void setMessageSpec(MessageSpecType value) {
        this.messageSpec = value;
    }

    /**
     * Obtiene el valor de la propiedad genericStatusMessage.
     * 
     * @return
     *     possible object is
     *     {@link GenericMessageStatusType }
     *     
     */
    public GenericMessageStatusType getGenericStatusMessage() {
        return genericStatusMessage;
    }

    /**
     * Define el valor de la propiedad genericStatusMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericMessageStatusType }
     *     
     */
    public void setGenericStatusMessage(GenericMessageStatusType value) {
        this.genericStatusMessage = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "2.0";
        } else {
            return version;
        }
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
