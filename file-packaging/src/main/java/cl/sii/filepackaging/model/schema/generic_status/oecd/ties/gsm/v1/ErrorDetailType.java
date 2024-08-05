//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.29 a las 11:52:55 AM CLT 
//


package cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.v1;

import cl.sii.filepackaging.model.schema.generic_status.oecd.ties.gsm.isogsmtypes.v1.LanguageCodeType;

import javax.xml.bind.annotation.*;


/**
 * Error message provide more details about the error
 * 
 * <p>Clase Java para ErrorDetail_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ErrorDetail_Type"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:oecd:ties:gsm:v1&gt;StringMin1Max4000_Type"&gt;
 *       &lt;attribute name="Language" type="{urn:oecd:ties:gsm:isogsmtypes:v1}LanguageCode_Type" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorDetail_Type", propOrder = {
    "value"
})
public class ErrorDetailType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Language")
    protected LanguageCodeType language;

    /**
     * Defines a string with minimum length 1 and maximum length of 4000
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
     * Obtiene el valor de la propiedad language.
     * 
     * @return
     *     possible object is
     *     {@link LanguageCodeType }
     *     
     */
    public LanguageCodeType getLanguage() {
        return language;
    }

    /**
     * Define el valor de la propiedad language.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageCodeType }
     *     
     */
    public void setLanguage(LanguageCodeType value) {
        this.language = value;
    }

}
