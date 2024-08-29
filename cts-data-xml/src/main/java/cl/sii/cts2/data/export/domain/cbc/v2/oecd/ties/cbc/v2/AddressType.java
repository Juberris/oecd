//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.08.22 a las 03:58:07 PM CLT 
//


package cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.cbc.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;
import cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.cbcstf.v5.OECDLegalAddressTypeEnumType;
import cl.sii.cts2.data.export.domain.cbc.v2.oecd.ties.isocbctypes.v1.CountryCodeType;


/**
 * 
 * 			The user has the option to enter the data about the address of a party either as one long field or to spread the data over up to eight  elements or even to use both formats. If the user chooses the option to enter the data required in separate elements, the container element for this will be 'AddressFix'. If the user chooses the option to enter the data required in a less structured way in 'AddressFree' all available address details shall be presented as one string of bytes, blank or "/" (slash) or carriage return- line feed used as a delimiter between parts of the address. PLEASE NOTE that the address country code is outside  both of these elements. The use of the fixed form is recommended as a rule to allow easy matching. However, the use of the free form is recommended if the sending state cannot reliably identify and distinguish the different parts of the address. The user may want to use both formats e.g. if besides separating the logical parts of the address he also wants to indicate a suitable breakdown into print-lines by delimiters in the free text form. In this case 'AddressFix' has to precede 'AddressFree'.
 * 			
 * 
 * <p>Clase Java para Address_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Address_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CountryCode" type="{urn:oecd:ties:isocbctypes:v1}CountryCode_Type"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="AddressFree" type="{urn:oecd:ties:cbcstf:v5}StringMin1Max4000_Type"/&gt;
 *           &lt;sequence&gt;
 *             &lt;element name="AddressFix" type="{urn:oecd:ties:cbc:v2}AddressFix_Type"/&gt;
 *             &lt;element name="AddressFree" type="{urn:oecd:ties:cbcstf:v5}StringMin1Max4000_Type" minOccurs="0"/&gt;
 *           &lt;/sequence&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="legalAddressType" type="{urn:oecd:ties:cbcstf:v5}OECDLegalAddressType_EnumType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address_Type", propOrder = {
    "content"
})
public class AddressType {

    @XmlElementRefs({
        @XmlElementRef(name = "CountryCode", namespace = "urn:oecd:ties:cbc:v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AddressFree", namespace = "urn:oecd:ties:cbc:v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AddressFix", namespace = "urn:oecd:ties:cbc:v2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;
    @XmlAttribute(name = "legalAddressType")
    protected OECDLegalAddressTypeEnumType legalAddressType;

    /**
     * Obtiene el resto del modelo de contenido. 
     * 
     * <p>
     * Ha obtenido esta propiedad que permite capturar todo por el siguiente motivo: 
     * El nombre de campo "AddressFree" se está utilizando en dos partes diferentes de un esquema. Consulte: 
     * línea 264 de file:/D:/juan.berrios/Desarrollo/Proyectos/OCDE/stf/source-code/DTCAEOI/xsd-maven-jaxb/src/main/resources/xsd/cbc/v2/CbcXML_v2.0.xsd
     * línea 261 de file:/D:/juan.berrios/Desarrollo/Proyectos/OCDE/stf/source-code/DTCAEOI/xsd-maven-jaxb/src/main/resources/xsd/cbc/v2/CbcXML_v2.0.xsd
     * <p>
     * Para deshacerse de esta propiedad, aplique una personalización de propiedad a una
     * de las dos declaraciones siguientes para cambiarles de nombre: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link AddressFixType }{@code >}
     * {@link JAXBElement }{@code <}{@link CountryCodeType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

    /**
     * Obtiene el valor de la propiedad legalAddressType.
     * 
     * @return
     *     possible object is
     *     {@link OECDLegalAddressTypeEnumType }
     *     
     */
    public OECDLegalAddressTypeEnumType getLegalAddressType() {
        return legalAddressType;
    }

    /**
     * Define el valor de la propiedad legalAddressType.
     * 
     * @param value
     *     allowed object is
     *     {@link OECDLegalAddressTypeEnumType }
     *     
     */
    public void setLegalAddressType(OECDLegalAddressTypeEnumType value) {
        this.legalAddressType = value;
    }

}
