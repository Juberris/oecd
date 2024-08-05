package cl.sii.filepackaging.util.security;

import org.apache.xml.security.Init;
import org.apache.xml.security.utils.Base64;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CtsUtilShared {
    public static int maxAttemptsToCreateNewFile = 10;
    public static String defaultKeystoreType = "pkcs12";
    public static String certificateType = "X.509";
    public static int defaultBufSize = 16384;
    public static int defaultChunkStreamingSize = 32768;
    private static Random randomInt = new Random(System.currentTimeMillis());
    private static Random randomLong = new Random(System.currentTimeMillis());
    private static Random randomBoolean = new Random(System.currentTimeMillis());
    private static Random randomDouble = new Random(System.currentTimeMillis());
    private static Object renameToNextSequencedFileLock = new Object();

    public CtsUtilShared() {
    }

    public static boolean validateSchema(String xmlFile, String schemaFile) throws Exception {
        return validateSchema(xmlFile, schemaFile, (String)null);
    }

    public static boolean validateSchema(String xmlFile, String schemaFile, String startElem) throws Exception {
        boolean success = false;

        try {
            QName qname = null;
            if (startElem != null) {
                String elem = null;
                String ns = null;
                Pattern pattern = Pattern.compile("\\{([^}]*)\\}|.+");

                for(Matcher matcher = pattern.matcher(startElem); matcher.find(); ns = matcher.group(1)) {
                    if (!matcher.group().startsWith("{")) {
                        elem = matcher.group();
                        break;
                    }
                }

                if (ns != null) {
                    qname = new QName(ns, elem);
                }
            }

            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedReader(new FileReader(xmlFile)));

            while(reader.hasNext() && (reader.getEventType() != 1 || startElem != null && (qname != null || !startElem.equalsIgnoreCase(reader.getName().getLocalPart())) && (qname == null || !qname.equals(reader.getName())))) {
                reader.next();
            }

            if (reader.getEventType() == 8) {
                throw new Exception(startElem + " element not found");
            } else {
                if (schemaFile.startsWith("http")) {
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new URL(schemaFile)).newValidator().validate(new StAXSource(reader));
                } else {
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(schemaFile)).newValidator().validate(new StAXSource(reader));
                }

                success = true;
                reader.close();
                return success;
            }
        } catch (Exception var9) {
            throw var9;
        }
    }

    public static String stripXmlHeader(String tmp) {
        int pos = tmp.indexOf("<?xml ");
        if (pos != -1) {
            pos = tmp.indexOf(">", pos);
            if (pos != -1) {
                tmp = tmp.substring(pos + 1);
                boolean stripWS = false;

                int i;
                for(i = 0; i < tmp.length() && Character.isWhitespace(tmp.charAt(i)); ++i) {
                    stripWS = true;
                }

                if (stripWS) {
                    tmp = tmp.substring(i);
                }
            }
        }

        return tmp;
    }

    public static String genUniqueRandomId() {
        UUID uuid = UUID.randomUUID();
        return uuid + "@" + System.identityHashCode(uuid);
    }

    public static BigDecimal genRandomDecimal(double low, double high) {
        double d;
        synchronized(randomDouble) {
            d = randomDouble.nextDouble();
        }

        d = low + d * (high - low);
        return (new BigDecimal(d)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static long genRandomPositiveLong() {
        long l;
        synchronized(randomLong) {
            l = randomLong.nextLong();
        }

        if (l < 0L) {
            l *= -1L;
        }

        return l;
    }

    public static boolean genRandomBoolean() {
        synchronized(randomBoolean) {
            return randomBoolean.nextBoolean();
        }
    }

    public static int genRandomPositiveInt() {
        int i;
        synchronized(randomInt) {
            i = randomInt.nextInt();
        }

        if (i < 0) {
            i *= -1;
        }

        return i;
    }

    public static int genRandomInt(int low, int high) {
        if (low >= high) {
            return low;
        } else {
            synchronized(randomInt) {
                return randomInt.nextInt(high - low) + low;
            }
        }
    }

    public static void cleanFolder(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();

            for(int i = 0; i < files.length; ++i) {
                File file = new File(dir.getAbsolutePath() + File.separator + files[i]);
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }

    }

    public static void deleteDestAndRenameFile(File src, File dest) throws Exception {
        if (!src.getAbsolutePath().equals(dest.getAbsolutePath())) {
            int attempts = maxAttemptsToCreateNewFile;

            while(attempts-- > 0 && dest.exists() && !dest.delete()) {
                Thread.sleep(100L);
            }

            if (attempts <= 0) {
                throw new Exception("unable to rename " + src.getAbsolutePath() + " to " + dest.getAbsolutePath());
            }

            attempts = maxAttemptsToCreateNewFile;

            while(attempts-- > 0 && !src.renameTo(dest)) {
                Thread.sleep(100L);
            }

            if (attempts <= 0) {
                throw new Exception("unable to rename " + src.getAbsolutePath() + " to " + dest.getAbsolutePath());
            }

            if (src.exists() && !src.delete()) {
                src.deleteOnExit();
            }
        }

    }

    public static void printNode(Node node) {
        if (node.getFirstChild() != null) {
            printNode(node.getFirstChild());
        }

        if (node.getNextSibling() != null) {
            printNode(node.getNextSibling());
        }

    }

    public static Certificate getCert(String certfile) throws Exception {
        try {
            CertificateFactory cf = CertificateFactory.getInstance(certificateType);
            FileInputStream fs = new FileInputStream(new File(certfile));
            Certificate cert = cf.generateCertificate(fs);
            fs.close();
            return cert;
        } catch (Exception var4) {
            throw var4;
        }
    }

    public static X509Certificate getCert(String keystorefile, String keystorepwd) throws Exception {
        return getCert(defaultKeystoreType, keystorefile, keystorepwd, (String)null);
    }

    public static X509Certificate getCert(String keystorefile, String keystorepwd, String alias) throws Exception {
        return getCert(defaultKeystoreType, keystorefile, keystorepwd, alias);
    }

    public static X509Certificate getCert(String keystoretype, String keystorefile, String keystorepwd, String alias) throws Exception {
        try {
            KeyStore keystore = KeyStore.getInstance(keystoretype);
            FileInputStream fis = new FileInputStream(new File(keystorefile));
            keystore.load(fis, keystorepwd.toCharArray());
            fis.close();
            if (alias == null) {
                Enumeration<String> e = keystore.aliases();
                if (e.hasMoreElements()) {
                    alias = (String)e.nextElement();
                }
            }

            if (alias != null) {
                X509Certificate cert = (X509Certificate)keystore.getCertificate(alias);
                return cert;
            } else {
                return null;
            }
        } catch (Exception var7) {
            throw var7;
        }
    }

    public static PrivateKey getPrivateKey(String keystorefile, String keystorepwd, String keypwd, String alias) throws Exception {
        return getPrivateKey(defaultKeystoreType, keystorefile, keystorepwd, keypwd, alias);
    }

    public static PrivateKey getPrivateKey(String keystorefile, String keystorepwd, String keypwd) throws Exception {
        return getPrivateKey(defaultKeystoreType, keystorefile, keystorepwd, keypwd, (String)null);
    }

    public static PrivateKey getPrivateKey(String keystoretype, String keystorefile, String keystorepwd, String keypwd, String alias) throws Exception {
        try {
            KeyStore keystore = KeyStore.getInstance(keystoretype);
            FileInputStream fis = new FileInputStream(new File(keystorefile));
            keystore.load(fis, keystorepwd.toCharArray());
            fis.close();
            if (alias == null) {
                Enumeration<String> e = keystore.aliases();
                if (e.hasMoreElements()) {
                    alias = (String)e.nextElement();
                }
            }

            if (alias != null) {
                PrivateKey privkey = (PrivateKey)keystore.getKey(alias, keypwd.toCharArray());
                if (privkey == null) {
                    privkey = (PrivateKey)keystore.getKey(alias.toLowerCase(), keypwd.toCharArray());
                }

                return privkey;
            } else {
                return null;
            }
        } catch (Exception var8) {
            throw var8;
        }
    }

    public static String getNodeXml(Node node) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer trans = transformerFactory.newTransformer();
        trans.setOutputProperty("omit-xml-declaration", "yes");
        StringWriter sw = new StringWriter();
        trans.transform(new DOMSource(node), new StreamResult(sw));
        return sw.getBuffer().toString();
    }

    public static void createBinaryFileFromSignedBase64BinaryFile(String infile, String outfile) throws Exception {
        BufferedReader br = null;
        BufferedOutputStream bos = null;

        try {
            if (!Init.isInitialized()) {
                Init.init();
            }

            boolean isBase64StartFound = false;
            br = new BufferedReader(new FileReader(new File(infile)));
            bos = new BufferedOutputStream(new FileOutputStream(new File(outfile)));

            while(true) {
                String line;
                if ((line = br.readLine()) != null) {
                    line = line.trim();
                    int pos;
                    if (!isBase64StartFound && (pos = line.indexOf("Object")) != -1) {
                        line = line.substring(pos + "Object".length());
                        pos = line.indexOf(62);
                        if (pos == -1) {
                            throw new Exception("'>' closing bracket is missing for <Object");
                        }

                        line = line.substring(pos + 1);

                        while(true) {
                            if (line.length() == 0) {
                                line = br.readLine();
                                if (line == null) {
                                    throw new Exception("unexpected EOF encountered");
                                }

                                line = line.trim();
                            }

                            if (!line.startsWith("<")) {
                                isBase64StartFound = true;
                                break;
                            }

                            pos = line.indexOf(62);
                            if (pos == -1) {
                                throw new Exception("missing > in " + line);
                            }

                            line = line.substring(pos + 1);
                        }
                    }

                    if (!isBase64StartFound) {
                        continue;
                    }

                    pos = line.indexOf(60);
                    if (pos != -1) {
                        line = line.substring(0, pos);
                    }

                    byte[] buf = Base64.decode(line.getBytes());
                    bos.write(buf);
                    if (pos == -1) {
                        continue;
                    }
                }

                br.close();
                br = null;
                bos.close();
                bos = null;
                return;
            }
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception var16) {
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception var15) {
                }
            }

        }
    }

    public static String getTmpFileName() throws Exception {
        return File.createTempFile("cts_signer", (String)null).getAbsolutePath();
    }

    public static File renameToNextSequencedFile(String srcfile) throws Exception {
        return renameToNextSequencedFile(srcfile, (String)null, (String)null, (String)null);
    }

    public static File renameToNextSequencedFile(String srcfile, String destfolder, String prefix, String suffix) throws Exception {
        synchronized(renameToNextSequencedFileLock) {
            File src = new File(srcfile);
            File dest = null;
            int count = 0;
            if (destfolder == null) {
                if (src.getParent() != null) {
                    destfolder = src.getAbsoluteFile().getParent();
                } else {
                    destfolder = "";
                }
            }

            int pos;
            if (prefix == null && (pos = srcfile.lastIndexOf(46)) != -1) {
                prefix = srcfile.substring(0, pos);
                if (suffix == null) {
                    suffix = srcfile.substring(pos);
                }
            }

            do {
                dest = new File(destfolder + prefix + "_" + count++ + suffix);
            } while(dest.exists());

            if (!src.renameTo(dest)) {
                throw new Exception("unable to rename " + src + " to " + dest);
            } else {
                if (src.exists() && !src.delete()) {
                    src.deleteOnExit();
                }

                return dest;
            }
        }
    }

    public static HashMap<String, String> getXmlInfo(String xml) throws Exception {
        HashMap<String, String> hash = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(new File(xml)));
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(br);
        String key = null;

        for(String val = null; reader.hasNext(); reader.next()) {
            switch (reader.getEventType()) {
                case 1:
                    key = reader.getName().getLocalPart();
                    val = null;
                    break;
                case 2:
                    if (key != null && val != null && key.equals(reader.getName().getLocalPart())) {
                        hash.put(key, val);
                    }

                    val = null;
                    key = null;
                case 3:
                default:
                    break;
                case 4:
                    val = reader.getText();
            }
        }

        reader.close();
        br.close();
        return hash;
    }

    public static class XmlTag {
        public QName qname;
        public String nsuri = "";

        public XmlTag(QName qname) {
            this.qname = qname;
        }

        public String toString() {
            return this.getStartTag();
        }

        public String getStartTag() {
            StringBuilder sb = new StringBuilder();
            sb.append("<");
            String prefix = this.qname.getPrefix();
            if (!"".equals(prefix)) {
                sb.append(prefix);
                sb.append(":");
            }

            sb.append(this.qname.getLocalPart());
            if (!"".equals(this.nsuri)) {
                sb.append(" ");
                sb.append(this.nsuri);
            }

            sb.append(">");
            return sb.toString();
        }

        public String getEndTag() {
            StringBuilder sb = new StringBuilder();
            sb.append("</");
            String prefix = this.qname.getPrefix();
            if (!"".equals(prefix)) {
                sb.append(prefix);
                sb.append(":");
            }

            sb.append(this.qname.getLocalPart());
            sb.append(">");
            return sb.toString();
        }
    }
}
