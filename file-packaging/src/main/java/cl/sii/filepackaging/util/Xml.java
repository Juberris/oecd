package cl.sii.filepackaging.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.jaxp.SAXParserFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

import javax.xml.bind.*;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.*;

public class Xml {
    private static final Log LOG = LogFactory.getLog(Xml.class);
    private static Map<Class<?>, JAXBContext> JAXBContexts = new HashMap();

    public Xml() {
    }

    public static void validate(String lang, List<String> schemas, ErrorHandler errorHandler, InputStream xml) throws IOException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = new SAXParserFactoryImpl();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        if (schemas != null) {
            if (schemas != null && schemas.size() > 0) {
                Source[] schemaSources = null;
                if (schemas != null) {
                    schemaSources = new Source[schemas.size()];

                    for(int i = 0; i < schemaSources.length; ++i) {
                        String schema = (String)schemas.get(i);
                        if (schema == null || !(new File(schema)).exists()) {
                            throw new ParserConfigurationException("SchemaFile [" + schema + "] no existe");
                        }

                        schemaSources[i] = new StreamSource((String)schemas.get(i));
                    }
                }

                if (schemaSources != null) {
                    factory.setSchema(schemaFactory.newSchema(schemaSources));
                }
            }

            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);

            try {
                Locale locale = new Locale(lang == null ? "es" : lang);
                reader.setProperty("http://apache.org/xml/properties/locale", locale);
            } catch (SAXNotRecognizedException var10) {
                LOG.warn("Version de Xerces no soporta multilenguaje (http://apache.org/xml/properties/locale)", var10);
            }

            reader.setErrorHandler(errorHandler);

            try {
                reader.parse(new InputSource(xml));
            } catch (SAXParseException var9) {
            }
        }

    }

    public static void read(Reader xml, XmlReaderListener listener) {
        try {
            read(XMLInputFactory.newInstance().createXMLStreamReader(xml), listener);
        } catch (XmlException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new XmlException("Error leyendo xml", var4);
        }
    }

    public static void read(InputStream xml, XmlReaderListener listener) {
        try {
            read(XMLInputFactory.newInstance().createXMLStreamReader(xml), listener);
        } catch (XmlException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new XmlException("Error leyendo xml", var4);
        }
    }

    public static String signDom(String xml, String jksPath, String jksPwd, String keyPwd) throws IOException {
        IOResources resources = new IOResources();

        String var22;
        try {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            Reference ref = fac.newReference("", fac.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1", (DigestMethodParameterSpec)null), Collections.singletonList(fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec)null)), (String)null, (String)null);
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (C14NMethodParameterSpec)null), fac.newSignatureMethod("http://www.w3.org/2000/09/xmldsig#rsa-sha1", (SignatureMethodParameterSpec)null), Collections.singletonList(ref));
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(jksPath), jksPwd.toCharArray());
            KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry)ks.getEntry((String)ks.aliases().nextElement(), new KeyStore.PasswordProtection(keyPwd.toCharArray()));
            X509Certificate cert = (X509Certificate)keyEntry.getCertificate();
            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List<Object> x509Content = new ArrayList();
            x509Content.add(cert.getSubjectX500Principal().getName());
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(resources.add(IOResources.toInputStream(xml)));
            DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());
            XMLSignature signature = fac.newXMLSignature(si, ki);
            signature.sign(dsc);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
            os.close();
            var22 = os.toString();
        } catch (Exception var26) {
            throw new XmlException("Error firmando xml", var26);
        } finally {
            resources.close();
        }

        return var22;
    }

    public static void read(XMLStreamReader xmlreader, XmlReaderListener listener) {
        try {
            listener.begin();
            Stack<String> fullPathStack = new Stack();
            StringBuilder characters = null;

            while(xmlreader.hasNext()) {
                String fullpath = "";

                String elementPrefix;
                for(Iterator var5 = fullPathStack.iterator(); var5.hasNext(); fullpath = fullpath + "/" + elementPrefix) {
                    elementPrefix = (String)var5.next();
                }

                xmlreader.next();
                String elementName;
                if (xmlreader.getEventType() != 1) {
                    if (xmlreader.getEventType() == 4) {
                        if (characters == null) {
                            characters = new StringBuilder();
                        }

                        characters.append(xmlreader.getText());
                    } else if (xmlreader.getEventType() == 2) {
                        if (characters != null) {
                            listener.elementValue(fullpath, characters.toString());
                            characters = null;
                        }

                        elementName = xmlreader.getName().getPrefix();
                        listener.elementEnd(fullpath);
                        listener.elementEnd(elementName, fullpath);
                        fullPathStack.pop();
                    }
                } else {
                    characters = null;
                    if (xmlreader.getNamespaceCount() > 0) {
                        for(int nsIndex = 0; nsIndex < xmlreader.getNamespaceCount(); ++nsIndex) {
                            elementPrefix = xmlreader.getNamespacePrefix(nsIndex);
                            String nsId = xmlreader.getNamespaceURI(nsIndex);
                            listener.ns(elementPrefix, nsId);
                        }
                    }

                    elementName = xmlreader.getLocalName();
                    elementPrefix = xmlreader.getName().getPrefix();
                    fullPathStack.push(elementName);
                    int attrsCount = xmlreader.getAttributeCount();
                    int lnum = xmlreader.getLocation().getLineNumber();
                    int cnum = xmlreader.getLocation().getColumnNumber();
                    if (attrsCount <= 0) {
                        listener.elementBgn(fullpath + "/" + elementName, (Map)null);
                        listener.elementBgn(fullpath + "/" + elementName, (Map)null, lnum, cnum);
                        listener.elementBgn(elementPrefix, fullpath + "/" + elementName, (Map)null);
                    } else {
                        Map<String, String> attrs = new HashMap();

                        for(int i = 0; i < attrsCount; ++i) {
                            attrs.put(xmlreader.getAttributeLocalName(i), xmlreader.getAttributeValue(i));
                        }

                        listener.elementBgn(fullpath + "/" + elementName, attrs);
                        listener.elementBgn(fullpath + "/" + elementName, attrs, lnum, cnum);
                        listener.elementBgn(elementPrefix, fullpath + "/" + elementName, attrs);
                    }
                }
            }
        } catch (XmlException var16) {
            if (!var16.isStopped()) {
                throw var16;
            }
        } catch (Exception var17) {
            throw new XmlException("Error leyendo xml", var17);
        } finally {
            listener.end();
        }

    }

    public static JAXBContext get(Class<?> clazz) {
        JAXBContext jaxbContext = null;
        Class var2 = Xml.class;
        synchronized(Xml.class) {
            jaxbContext = (JAXBContext)JAXBContexts.get(clazz);
            if (jaxbContext == null) {
                try {
                    jaxbContext = JAXBContext.newInstance(new Class[]{clazz});
                    JAXBContexts.put(clazz, jaxbContext);
                } catch (Exception var5) {
                    throw new RuntimeException("Error", var5);
                }
            }

            return jaxbContext;
        }
    }

    public static String toXml(Object obj) {
        return toXml(obj, (String)null, false);
    }

    public static String toXml(Object obj, boolean prettyPrint) {
        return toXml(obj, (String)null, prettyPrint);
    }

    public static String toXml(Object obj, String schemaFile, boolean prettyPrint) {
        StringWriter writer = new StringWriter();
        toXml(obj, schemaFile, writer, prettyPrint);
        return writer.toString();
    }

    public static void toXmlPretty(Object obj, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            toXml(obj, (String)null, writer, true);
            writer.close();
        } catch (XmlException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new XmlException("Error creando xml con " + obj.getClass().getSimpleName(), var4);
        }
    }

    public static void toXml(Object obj, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            toXml(obj, (String)null, writer, false);
            writer.close();
        } catch (XmlException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new XmlException("Error creando xml con " + obj.getClass().getSimpleName(), var4);
        }
    }

    public static void toXml(Object obj, String schemasCsv, Writer writer, boolean prettyPrint) {
        if (obj != null) {
            try {
                JAXBContext jaxbContext = get(obj.getClass());
                Marshaller marshaller = jaxbContext.createMarshaller();
                final XmlException xmlException = new XmlException("Error validando xml");
                if (schemasCsv != null) {
                    SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                    List<Source> schemaSources = new ArrayList();
                    String[] var9 = schemasCsv.split("\\,");
                    int var10 = var9.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String schema = var9[var11];
                        schemaSources.add(new StreamSource(new File(schema)));
                    }

                    Schema schema = sf.newSchema((Source[])schemaSources.toArray(new Source[0]));
                    marshaller.setSchema(schema);
                    marshaller.setEventHandler(new ValidationEventHandler() {
                        public boolean handleEvent(ValidationEvent event) {
                            xmlException.add(event);
                            return true;
                        }
                    });
                }

                if (prettyPrint) {
                    marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
                }

                marshaller.marshal(obj, writer);
                writer.close();
                if (!xmlException.isEmpty()) {
                    throw xmlException;
                }
            } catch (XmlException var13) {
                throw var13;
            } catch (Exception var14) {
                throw new XmlException("Error creando xml con " + obj.getClass().getSimpleName(), var14);
            }
        }

    }

    public static <E> E fromXml(File file, Class<E> clazz) {
        return fromXml((File)file, clazz, (String)null);
    }

    public static <E> E fromXml(File file, Class<E> clazz, String schemaFile) {
        try {
            FileInputStream in = new FileInputStream(file);
            E e = fromXml((InputStream)in, clazz, schemaFile);
            in.close();
            return e;
        } catch (XmlException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new XmlException("Error obteniendo xml  " + clazz.getSimpleName(), var6);
        }
    }

    public static <E> E fromXml(InputStream in, Class<E> clazz) {
        return fromXml((InputStream)in, clazz, (String)null);
    }

    public static <E> E fromXml(Document doc, Class<E> clazz, String schemaFile) {
        try {
            JAXBContext jaxbContext = get(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final XmlException xmlException = new XmlException("Error validando xml");
            if (schemaFile != null) {
                List<Source> sources = new ArrayList();
                String[] var7 = schemaFile.split("\\,");
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String path = var7[var9];
                    sources.add(new StreamSource(new FileInputStream(path)));
                }

                SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                Schema schema = sf.newSchema((Source[])sources.toArray(new Source[0]));
                unmarshaller.setSchema(schema);
                unmarshaller.setEventHandler(new ValidationEventHandler() {
                    public boolean handleEvent(ValidationEvent event) {
                        xmlException.add(event);
                        return true;
                    }
                });
            }

            E e = (E) unmarshaller.unmarshal(doc);
            if (!xmlException.isEmpty()) {
                throw xmlException;
            } else {
                return e;
            }
        } catch (Exception var11) {
            throw new XmlException("Error creando " + clazz.getSimpleName() + " desde xml", var11);
        }
    }

    public static <E> E fromXml(InputStream in, Class<E> clazz, String schemaFile) {
        try {
            JAXBContext jaxbContext = get(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final XmlException xmlException = new XmlException("Error validando xml");
            if (schemaFile != null) {
                List<Source> sources = new ArrayList();
                String[] var7 = schemaFile.split("\\,");
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String path = var7[var9];
                    sources.add(new StreamSource(new FileInputStream(path)));
                }

                SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                Schema schema = sf.newSchema((Source[])sources.toArray(new Source[0]));
                unmarshaller.setSchema(schema);
                unmarshaller.setEventHandler(new ValidationEventHandler() {
                    public boolean handleEvent(ValidationEvent event) {
                        xmlException.add(event);
                        return true;
                    }
                });
            }

            E e = (E) unmarshaller.unmarshal(in);
            if (!xmlException.isEmpty()) {
                throw xmlException;
            } else {
                return e;
            }
        } catch (Exception var11) {
            throw new XmlException("Error creando " + clazz.getSimpleName() + " desde xml", var11);
        }
    }

    public static String prettyPrint(InputStream stream) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            StreamResult result = new StreamResult(new StringWriter());
            Source source = new StreamSource(stream);
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception var4) {
            throw new XmlException("Error formateando xml", var4);
        }
    }

    public static String prettyPrint(String xml) {
        return prettyPrint((InputStream)(new ByteArrayInputStream(xml.getBytes())));
    }
    public static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NodeList getElementsByTagNameWildcard(Document document, String tagNamePattern) {
        //Retorna el primer elemento que encuentra
        NodeList allElements = document.getElementsByTagName("*");
        String elementSearch="";

        // Iterate through the elements and filter based on the pattern
        for (int i = 0; i < allElements.getLength(); i++) {
            if (allElements.item(i) instanceof Element) {
                Element element = (Element) allElements.item(i);
                if (element.getTagName().contains(tagNamePattern)) {
                    elementSearch=element.getTagName();
                    break;
                }
            }
        }
        NodeList filteredList = document.getElementsByTagName(elementSearch);

        return filteredList;
    }

    public static String nodeListToString(NodeList nodes) throws TransformerException {
        DOMSource source = new DOMSource();
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        for (int i = 0; i < nodes.getLength(); ++i) {
            source.setNode(nodes.item(i));
            transformer.transform(source, result);
        }

        return writer.toString();
    }
    public static void removeEmpty(File in, boolean pretty) {
        try {
            File out = File.createTempFile("xml", (String)null);
            TransformerFactory factory = TransformerFactory.newInstance();
            Templates template = factory.newTemplates(new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("remove-empty.xsl")));
            Transformer xformer = template.newTransformer();
            if (pretty) {
                xformer.setOutputProperty("indent", "yes");
                xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            }

            FileInputStream inStream = new FileInputStream(in);
            Throwable var7 = null;

            try {
                FileOutputStream outStream = new FileOutputStream(out);
                Throwable var9 = null;

                try {
                    xformer.transform(new StreamSource(inStream), new StreamResult(outStream));
                } catch (Throwable var34) {
                    var9 = var34;
                    throw var34;
                } finally {
                    if (outStream != null) {
                        if (var9 != null) {
                            try {
                                outStream.close();
                            } catch (Throwable var33) {
                                var9.addSuppressed(var33);
                            }
                        } else {
                            outStream.close();
                        }
                    }

                }
            } catch (Throwable var36) {
                var7 = var36;
                throw var36;
            } finally {
                if (inStream != null) {
                    if (var7 != null) {
                        try {
                            inStream.close();
                        } catch (Throwable var32) {
                            var7.addSuppressed(var32);
                        }
                    } else {
                        inStream.close();
                    }
                }

            }

            in.delete();
            out.renameTo(in);
        } catch (Exception var38) {
            throw new RuntimeException("Error eliminando elementos vacios de XML", var38);
        }
    }





    public static class XmlException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        private List<ValidationEvent> events;
        private boolean stopped;

        public XmlException(boolean stopped) {
            this.stopped = stopped;
        }

        public XmlException() {
        }

        public XmlException(String message, Throwable cause) {
            super(message, cause);
        }

        public XmlException(String message) {
            super(message);
        }

        public XmlException(Throwable cause) {
            super(cause);
        }

        public XmlException add(ValidationEvent event) {
            if (this.events == null) {
                this.events = new ArrayList();
            }

            this.events.add(event);
            return this;
        }

        public boolean isStopped() {
            return this.stopped;
        }

        public boolean isEmpty() {
            return this.events == null || this.events.size() == 0;
        }

        public String getMessage() {
            return this.toString();
        }

        public List<ValidationEvent> getEvents() {
            return this.events;
        }

        public String toString() {
            if (this.isEmpty()) {
                return super.getMessage();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(super.getMessage() + ":\n");
                Iterator var2 = this.events.iterator();

                while(var2.hasNext()) {
                    ValidationEvent e = (ValidationEvent)var2.next();
                    sb.append(e.getMessage() + "\n");
                }

                return sb.toString();
            }
        }
    }

    public static class XmlReaderListener {
        private Map<String, String> env;
        protected Map<String, String> namespaces = new LinkedHashMap();

        public XmlReaderListener() {
        }

        public XmlReaderListener setEnv(String envKey, String envValue) {
            if (this.env == null) {
                this.env = new HashMap();
            }

            this.env.put(envKey, envValue);
            return this;
        }

        public void begin(String id) {
        }

        public void begin() {
        }

        public void ns(String prefix, String value) {
            this.namespaces.put(prefix, value);
        }

        public void elementBgn(String fullpath, Map<String, String> attrs) {
        }

        public void elementBgn(String prefix, String fullpath, Map<String, String> attrs) {
        }

        public void elementBgn(String fullpath, Map<String, String> attrs, int lnum, int cnum) {
        }

        public void elementValue(String fullpath, String value) {
        }

        public void elementEnd(String fullpath) {
        }

        public void elementEnd(String prefix, String fullpath) {
        }

        public void end() {
            this.env = null;
        }

        public String getEnv(String key) {
            return this.env != null && this.env.containsKey(key) ? (String)this.env.get(key) : null;
        }

        public void error(String msg, Throwable t) {
        }

        public void error(String msg) {
            this.error(msg, (Throwable)null);
        }
    }

    public static class XmlWriter {
        private Writer writer;
        private XMLStreamWriter xmlWriter;

        public XmlWriter() {
            this(true);
        }

        public XmlWriter(boolean declaration) {
            try {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                this.xmlWriter = output.createXMLStreamWriter(this.writer = new StringWriter());
                if (declaration) {
                    this.xmlWriter.writeStartDocument();
                }

            } catch (Exception var3) {
                throw new RuntimeException("Error xml bgn", var3);
            }
        }

        public XmlWriter(Writer writer) {
            try {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                this.xmlWriter = output.createXMLStreamWriter(this.writer = writer);
                this.xmlWriter.writeStartDocument();
            } catch (Exception var3) {
                throw new RuntimeException("Error xml bgn", var3);
            }
        }

        public void bgn(String name, Map<String, String> attrs) {
            try {
                this.xmlWriter.writeStartElement(name);
                if (attrs != null) {
                    Iterator var3 = attrs.keySet().iterator();

                    while(var3.hasNext()) {
                        String key = (String)var3.next();
                        this.xmlWriter.writeAttribute(key, (String)attrs.get(key));
                    }
                }

            } catch (Exception var5) {
                throw new RuntimeException("Error xml bgn element", var5);
            }
        }

        public void bgn(String name, Map<String, String> namespaces, Map<String, String> attrs) {
            try {
                this.xmlWriter.writeStartElement(name);
                Iterator var4;
                String key;
                if (namespaces != null) {
                    var4 = namespaces.keySet().iterator();

                    while(var4.hasNext()) {
                        key = (String)var4.next();
                        this.xmlWriter.writeNamespace(key, (String)namespaces.get(key));
                    }
                }

                if (attrs != null) {
                    var4 = attrs.keySet().iterator();

                    while(var4.hasNext()) {
                        key = (String)var4.next();
                        this.xmlWriter.writeAttribute(key, (String)attrs.get(key));
                    }
                }

            } catch (Exception var6) {
                throw new RuntimeException("Error xml bgn element", var6);
            }
        }

        public void value(String value) {
            try {
                this.xmlWriter.writeCharacters(value.trim().replaceAll("[\\t\\n\\r]+", ", "));
            } catch (Exception var3) {
                throw new RuntimeException("Error xml value", var3);
            }
        }

        public void valueOriginal(String value) {
            try {
                this.xmlWriter.writeCharacters(value);
            } catch (Exception var3) {
                throw new RuntimeException("Error xml value", var3);
            }
        }

        public void end(String name) {
            try {
                this.xmlWriter.writeEndElement();
            } catch (Exception var3) {
                throw new RuntimeException("Error xml end element", var3);
            }
        }

        public String endToString() {
            try {
                this.xmlWriter.writeEndDocument();
                this.xmlWriter.flush();
                this.xmlWriter.close();
                return this.writer.toString();
            } catch (Exception var2) {
                throw new RuntimeException("Error xml end", var2);
            }
        }

        public void end() {
            try {
                this.xmlWriter.writeEndDocument();
                this.xmlWriter.flush();
                this.xmlWriter.close();
            } catch (Exception var2) {
                throw new RuntimeException("Error xml end", var2);
            }
        }
    }
}

