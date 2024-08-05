package cl.sii.filepackaging.util.security;


import org.apache.xml.security.Init;
import org.apache.xml.security.utils.IgnoreAllErrorHandler;
import org.apache.xerces.impl.dv.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.*;

public class CtsSigner {
    private static final Set<String> INVALID_CHARS = new HashSet();
    public SigXmlTransform sigXmlTransform;
    protected Provider defaultSignatureFactoryProvider = null;
    protected boolean isExcludeKeyInfoFromSignature = false;
    protected SigRefIdPos sigRefIdPos;
    protected String signaturePrefix = "";
    public CtsSigner() {
        this.sigRefIdPos = SigRefIdPos.Object;
        this.sigXmlTransform = SigXmlTransform.Inclusive;
        //this.signatureVerifier = null;
        if (!Init.isInitialized()) {
            System.setProperty("com.sun.org.apache.xml.internal.security.ignoreLineBreaks", "true");
            Init.init();
        }

    }

    public boolean signFile(InputStream inStream, OutputStream outStream, PrivateKey sigkey, X509Certificate sigPubCert, SigDocType sigDocType, SigXmlTransform sigXmlTransform, String refUriVal) throws Exception {
        BufferedOutputStream bos = null;
        boolean ret = false;

        try {
            Document doc = this.createSignedDOMDoc(inStream, sigkey, sigPubCert, sigDocType, sigXmlTransform, refUriVal);
            NodeList nodeList = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
            if (nodeList.getLength() > 0) {
                bos = new BufferedOutputStream(outStream);
            }

            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(bos));
            ret = true;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception var18) {
                }
            }

        }

        return ret;
    }

    protected Document createSignedDOMDoc(InputStream inStream, PrivateKey sigkey, X509Certificate sigPubCert, SigDocType sigDocType, SigXmlTransform sigXmlTransform, String refUriVal) throws Exception {
        BufferedInputStream bis = null;
        Document doc = null;
        XMLObject xmlobj = null;
        List<Transform> transforms = null;
        Node node = null;
        String tmp = null;

        try {
            DocumentBuilderFactory dbfNSTrue = DocumentBuilderFactory.newInstance();
            dbfNSTrue.setNamespaceAware(true);
            DocumentBuilder docBuilderNSTrue = dbfNSTrue.newDocumentBuilder();
            docBuilderNSTrue.setErrorHandler(new IgnoreAllErrorHandler());
            if (inStream == null) {
                tmp = Base64.encode(CtsUtilShared.genUniqueRandomId().getBytes());
                doc = docBuilderNSTrue.newDocument();
                node = doc.createTextNode(tmp);
            } else {
                switch (sigDocType) {
                    case XML:
                    case WRAPPEDXML:
                        doc = docBuilderNSTrue.parse(inStream);
                        node = doc.getDocumentElement();
                        break;
                    case BINARY:
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        this.writeBase64BinaryAndOptionallyCalcMsgDigest(inStream, "\n", baos, false, (MessageDigest)null);
                        baos.close();
                        doc = docBuilderNSTrue.newDocument();
                        node = doc.createTextNode(baos.toString().replaceAll("[^A-Za-z0-9+/=]", ""));
                        break;
                    case TEXT:
                        StringBuffer sb = new StringBuffer();
                        bis = new BufferedInputStream(inStream);
                        byte[] buf = new byte[CtsUtilShared.defaultBufSize];

                        int len;
                        while((len = bis.read(buf)) != -1) {
                            sb.append(new String(buf, 0, len));
                        }

                        bis.close();
                        bis = null;
                        doc = docBuilderNSTrue.newDocument();
                        node = doc.createTextNode(sb.toString());
                }
            }

            XMLSignatureFactory xmlSigFactory;
            if (this.defaultSignatureFactoryProvider != null) {
                xmlSigFactory = XMLSignatureFactory.getInstance("DOM", this.defaultSignatureFactoryProvider);
            } else {
                xmlSigFactory = XMLSignatureFactory.getInstance();
            }

            if (sigDocType == SigDocType.XML) {
                if (sigXmlTransform != SigXmlTransform.None) {
                    transforms = Collections.singletonList(xmlSigFactory.newTransform(this.getCanonicalizationMethod(sigXmlTransform), (TransformParameterSpec)null));
                }
            } else if (sigDocType == SigDocType.BINARY) {
                transforms = Collections.singletonList(xmlSigFactory.newTransform("http://www.w3.org/2000/09/xmldsig#base64", (TransformParameterSpec)null));
            }

            XMLStructure content = new DOMStructure(this.filter((Node)node));
            SignatureProperty sigProp;
            SignatureProperties sigProps;
            switch (this.sigRefIdPos) {
                case SignatureProperties:
                    sigProp = xmlSigFactory.newSignatureProperty(Collections.singletonList(content), "#SignatureId", (String)null);
                    sigProps = xmlSigFactory.newSignatureProperties(Collections.singletonList(sigProp), refUriVal);
                    xmlobj = xmlSigFactory.newXMLObject(Collections.singletonList(sigProps), (String)null, (String)null, (String)null);
                    break;
                case SignatureProperty:
                    sigProp = xmlSigFactory.newSignatureProperty(Collections.singletonList(content), "#SignatureId", refUriVal);
                    sigProps = xmlSigFactory.newSignatureProperties(Collections.singletonList(sigProp), (String)null);
                    xmlobj = xmlSigFactory.newXMLObject(Collections.singletonList(sigProps), (String)null, (String)null, (String)null);
                    break;
                case Object:
                    xmlobj = xmlSigFactory.newXMLObject(Collections.singletonList(content), refUriVal, (String)null, (String)null);
            }

            List<XMLObject> xmlObjs = Collections.singletonList(xmlobj);
            Reference sigref = xmlSigFactory.newReference("#" + refUriVal, xmlSigFactory.newDigestMethod("http://www.w3.org/2001/04/xmlenc#sha256", (DigestMethodParameterSpec)null), transforms, (String)null, (String)null);
            SignedInfo signedInfo = xmlSigFactory.newSignedInfo(xmlSigFactory.newCanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (C14NMethodParameterSpec)null), xmlSigFactory.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", (SignatureMethodParameterSpec)null), Collections.singletonList(sigref));
            KeyInfo keyInfo = null;
            if (sigPubCert != null && !this.isExcludeKeyInfoFromSignature) {
                List<X509Certificate> list = new ArrayList();
                list.add(sigPubCert);
                KeyInfoFactory keyInfoFactory = xmlSigFactory.getKeyInfoFactory();
                X509Data kv = keyInfoFactory.newX509Data(list);
                keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(kv));
            }

            XMLSignature signature = xmlSigFactory.newXMLSignature(signedInfo, keyInfo, xmlObjs, "SignatureId", (String)null);
            doc = docBuilderNSTrue.newDocument();
            DOMSignContext dsc = new DOMSignContext(sigkey, doc);
            if (this.signaturePrefix != null && !"".equals(this.signaturePrefix)) {
                dsc.setDefaultNamespacePrefix(this.signaturePrefix);
            }

            signature.sign(dsc);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception var32) {
                }
            }

        }

        return doc;
    }

    protected void writeBase64BinaryAndOptionallyCalcMsgDigest(InputStream inStream, String newline, OutputStream outStream, boolean isCalcDigest, MessageDigest messageDigest) throws Exception {
        if (isCalcDigest && messageDigest == null) {
            throw new Exception("messageDigest must not be null if isCalcDigest=true");
        } else {
            BufferedInputStream bis = new BufferedInputStream(inStream);
            int offset = 0;
            int lastlinelen = 0;

            int len;
            byte[] buf;
            byte[] tmpBuf;
            for(buf = new byte[CtsUtilShared.defaultBufSize]; (len = bis.read(buf, offset, buf.length - offset)) != -1; lastlinelen = this.writeEncodedBinary(lastlinelen, tmpBuf, newline, outStream)) {
                if (isCalcDigest) {
                    messageDigest.update(buf, offset, len);
                }

                int nextoffset = (offset + len) % 3;
                tmpBuf = new byte[offset + len - nextoffset];
                System.arraycopy(buf, 0, tmpBuf, 0, offset + len - nextoffset);

                for(int i = 0; i < nextoffset; ++i) {
                    buf[i] = buf[offset + len - nextoffset + i];
                }

                offset = nextoffset;
            }

            if (offset > 0) {
                tmpBuf = new byte[offset];
                System.arraycopy(buf, 0, tmpBuf, 0, offset);
                this.writeEncodedBinary(lastlinelen, tmpBuf, newline, outStream);
            }

            bis.close();
        }
    }

    protected String getCanonicalizationMethod(SigXmlTransform sigXmlTransform) {
        switch (sigXmlTransform) {
            case InclusiveWithComments:
                return "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";
            case Exclusive:
                return "http://www.w3.org/2001/10/xml-exc-c14n#";
            case ExclusiveWithComments:
                return "http://www.w3.org/2001/10/xml-exc-c14n#WithComments";
            case Inclusive:
            case None:
            default:
                return "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
        }
    }

    protected int writeEncodedBinary(int lastlinelen, byte[] buf, String newline, OutputStream outStream) throws Exception {
        String encoded = Base64.encode(buf).replaceAll("\r", "").replaceAll("\n", "");
        int strlen = encoded.length();
        if (lastlinelen + strlen < 76) {
            outStream.write(encoded.getBytes());
            lastlinelen += strlen;
        } else {
            String tmpS = encoded.substring(0, 76 - lastlinelen);
            outStream.write(tmpS.getBytes());
            outStream.write(newline.getBytes());
            encoded = encoded.substring(tmpS.length());

            for(lastlinelen = 0; encoded.length() >= 76; encoded = encoded.substring(tmpS.length())) {
                tmpS = encoded.substring(0, 76 - lastlinelen);
                outStream.write(tmpS.getBytes());
                outStream.write(newline.getBytes());
            }

            if (encoded.length() > 0) {
                outStream.write(encoded.getBytes());
                lastlinelen = encoded.length();
            }
        }

        return lastlinelen;
    }

    public Node filter(Node n) {
        if (n != null) {
            NodeList nl = n.getChildNodes();
            if (nl != null) {
                for(int i = 0; i < nl.getLength(); ++i) {
                    Node child = nl.item(i);
                    if (child.getNodeType() == 3) {
                        String value = child.getNodeValue();
                        if (value != null && value.trim().length() > 0) {
                            value = value.trim();
                            String valueCleaned = clean(value);
                            if (!value.equals(valueCleaned)) {
                                child.setNodeValue(valueCleaned);
                            }
                        }
                    }

                    this.filter(child);
                }
            }
        }

        return n;
    }

    public static enum SigDocType {
        XML,
        WRAPPEDXML,
        TEXT,
        BINARY;

        private SigDocType() {
        }
    }
    public String getSigRefUriVal() {
        return "RefId-" + UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static enum SigXmlTransform {
        Inclusive,
        InclusiveWithComments,
        Exclusive,
        ExclusiveWithComments,
        None;

        private SigXmlTransform() {
        }
    }
    public static enum SigRefIdPos {
        Object,
        SignatureProperty,
        SignatureProperties;

        private SigRefIdPos() {
        }
    }
    public static String clean(String value) {
        Set<String> chars = invalidCharacters(value);
        String ch;
        if (chars != null) {
            for(Iterator var2 = chars.iterator(); var2.hasNext(); value = value.replace(ch, "")) {
                ch = (String)var2.next();
            }
        }

        return value;
    }
    public static Set<String> invalidCharacters(String value) {
        Set<String> set = null;
        Iterator var2 = INVALID_CHARS.iterator();

        while(var2.hasNext()) {
            String chars = (String)var2.next();
            if (value.contains(chars)) {
                if (set == null) {
                    set = new HashSet();
                }

                set.add(chars);
            }
        }

        return set;
    }
    static {
        INVALID_CHARS.add("&#");
        INVALID_CHARS.add("--");
        INVALID_CHARS.add("/**");
    }
}
