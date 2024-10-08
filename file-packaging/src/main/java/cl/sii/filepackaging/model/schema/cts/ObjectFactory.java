//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.29 a las 10:58:24 AM CLST 
//


package cl.sii.filepackaging.model.schema.cts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cts2.batch.app.schema.cts package. 
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

    private final static QName _CTSSenderFileMetadata_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "CTSSenderFileMetadata");
    private final static QName _FileCreateTs_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "FileCreateTs");
    private final static QName _BinaryEncodingSchemeCd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "BinaryEncodingSchemeCd");
    private final static QName _CTSReceiverCountryCd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "CTSReceiverCountryCd");
    private final static QName _FileRevisionInd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "FileRevisionInd");
    private final static QName _CTSCommunicationTypeCd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "CTSCommunicationTypeCd");
    private final static QName _OriginalCTSTransmissionId_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "OriginalCTSTransmissionId");
    private final static QName _SenderFileId_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "SenderFileId");
    private final static QName _SenderContactEmailAddressTxt_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "SenderContactEmailAddressTxt");
    private final static QName _TaxYear_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "TaxYear");
    private final static QName _CTSSenderCountryCd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "CTSSenderCountryCd");
    private final static QName _FileFormatCd_QNAME = new QName("urn:oecd:ctssenderfilemetadata", "FileFormatCd");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cts2.batch.app.schema.cts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CTSSenderFileMetadataType }
     * 
     */
    public CTSSenderFileMetadataType createCTSSenderFileMetadataType() {
        return new CTSSenderFileMetadataType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CTSSenderFileMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "CTSSenderFileMetadata")
    public JAXBElement<CTSSenderFileMetadataType> createCTSSenderFileMetadata(CTSSenderFileMetadataType value) {
        return new JAXBElement<CTSSenderFileMetadataType>(_CTSSenderFileMetadata_QNAME, CTSSenderFileMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "FileCreateTs")
    public JAXBElement<XMLGregorianCalendar> createFileCreateTs(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_FileCreateTs_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryEncodingSchemeCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "BinaryEncodingSchemeCd")
    public JAXBElement<BinaryEncodingSchemeCdType> createBinaryEncodingSchemeCd(BinaryEncodingSchemeCdType value) {
        return new JAXBElement<BinaryEncodingSchemeCdType>(_BinaryEncodingSchemeCd_QNAME, BinaryEncodingSchemeCdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "CTSReceiverCountryCd")
    public JAXBElement<String> createCTSReceiverCountryCd(String value) {
        return new JAXBElement<String>(_CTSReceiverCountryCd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "FileRevisionInd")
    public JAXBElement<Boolean> createFileRevisionInd(Boolean value) {
        return new JAXBElement<Boolean>(_FileRevisionInd_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CTSCommunicationTypeCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "CTSCommunicationTypeCd")
    public JAXBElement<CTSCommunicationTypeCdType> createCTSCommunicationTypeCd(CTSCommunicationTypeCdType value) {
        return new JAXBElement<CTSCommunicationTypeCdType>(_CTSCommunicationTypeCd_QNAME, CTSCommunicationTypeCdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "OriginalCTSTransmissionId")
    public JAXBElement<String> createOriginalCTSTransmissionId(String value) {
        return new JAXBElement<String>(_OriginalCTSTransmissionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "SenderFileId")
    public JAXBElement<String> createSenderFileId(String value) {
        return new JAXBElement<String>(_SenderFileId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "SenderContactEmailAddressTxt")
    public JAXBElement<String> createSenderContactEmailAddressTxt(String value) {
        return new JAXBElement<String>(_SenderContactEmailAddressTxt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "TaxYear")
    public JAXBElement<XMLGregorianCalendar> createTaxYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TaxYear_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "CTSSenderCountryCd")
    public JAXBElement<String> createCTSSenderCountryCd(String value) {
        return new JAXBElement<String>(_CTSSenderCountryCd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileFormatCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ctssenderfilemetadata", name = "FileFormatCd")
    public JAXBElement<FileFormatCdType> createFileFormatCd(FileFormatCdType value) {
        return new JAXBElement<FileFormatCdType>(_FileFormatCd_QNAME, FileFormatCdType.class, null, value);
    }

}
