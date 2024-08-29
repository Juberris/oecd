//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v2.3.7 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.20 a las 04:35:00 PM CLT 
//


package cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.commontypesfatcacrs.v1;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import cl.sii.cts2.data.export.domain.crs.v1.oecd.ties.isocrstypes.v1.CountryCodeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oecd.ties.commontypesfatcacrs.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddressTypeCountryCode_QNAME = new QName("urn:oecd:ties:commontypesfatcacrs:v1", "CountryCode");
    private final static QName _AddressTypeAddressFree_QNAME = new QName("urn:oecd:ties:commontypesfatcacrs:v1", "AddressFree");
    private final static QName _AddressTypeAddressFix_QNAME = new QName("urn:oecd:ties:commontypesfatcacrs:v1", "AddressFix");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oecd.ties.commontypesfatcacrs.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddressFixType }
     * 
     */
    public AddressFixType createAddressFixType() {
        return new AddressFixType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link MonAmntType }
     * 
     */
    public MonAmntType createMonAmntType() {
        return new MonAmntType();
    }

    /**
     * Create an instance of {@link NameOrganisationType }
     * 
     */
    public NameOrganisationType createNameOrganisationType() {
        return new NameOrganisationType();
    }

    /**
     * Create an instance of {@link TINType }
     * 
     */
    public TINType createTINType() {
        return new TINType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryCodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CountryCodeType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:commontypesfatcacrs:v1", name = "CountryCode", scope = AddressType.class)
    public JAXBElement<CountryCodeType> createAddressTypeCountryCode(CountryCodeType value) {
        return new JAXBElement<CountryCodeType>(_AddressTypeCountryCode_QNAME, CountryCodeType.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:commontypesfatcacrs:v1", name = "AddressFree", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeAddressFree(String value) {
        return new JAXBElement<String>(_AddressTypeAddressFree_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressFixType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddressFixType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:commontypesfatcacrs:v1", name = "AddressFix", scope = AddressType.class)
    public JAXBElement<AddressFixType> createAddressTypeAddressFix(AddressFixType value) {
        return new JAXBElement<AddressFixType>(_AddressTypeAddressFix_QNAME, AddressFixType.class, AddressType.class, value);
    }

}
