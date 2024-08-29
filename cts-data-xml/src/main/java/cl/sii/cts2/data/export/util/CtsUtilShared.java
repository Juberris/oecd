package cl.sii.cts2.data.export.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CtsUtilShared {
    public static String defaultKeystoreType = "pkcs12";
    public static String certificateType = "X.509";

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
        return getCert(defaultKeystoreType, keystorefile, keystorepwd, null);
    }

    public static X509Certificate getCert(String keystorefile, String keystorepwd, String alias) throws Exception {
        return getCert(defaultKeystoreType, keystorefile, keystorepwd, alias);
    }

    public static X509Certificate getCert(String keystoretype, String keystorefile, String keystorepwd, String alias) throws Exception {
        try {
            //KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            KeyStore keystore = KeyStore.getInstance(keystoretype);
            FileInputStream fis = new FileInputStream(new File(keystorefile));
            keystore.load(fis, keystorepwd.toCharArray());
            fis.close();
            if (alias == null) {
                Enumeration<String> e = keystore.aliases();
                if (e.hasMoreElements())
                    alias = e.nextElement();
            }
            if (alias != null) {
                X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
                return cert;
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static PrivateKey getPrivateKey(String keystorefile, String keystorepwd, String keypwd, String alias) throws Exception {
        return getPrivateKey(defaultKeystoreType, keystorefile, keystorepwd, keypwd, alias);
    }

    public static PrivateKey getPrivateKey(String keystorefile, String keystorepwd, String keypwd) throws Exception {
        return getPrivateKey(defaultKeystoreType, keystorefile, keystorepwd, keypwd, null);
    }

    public static PrivateKey getPrivateKey(String keystoretype, String keystorefile, String keystorepwd, String keypwd, String alias) throws Exception {
        try {
            //KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            KeyStore keystore = KeyStore.getInstance(keystoretype);
            FileInputStream fis = new FileInputStream(new File(keystorefile));
            keystore.load(fis, keystorepwd.toCharArray());
            fis.close();
            if (alias == null) {
                Enumeration<String> e = keystore.aliases();
                if (e.hasMoreElements())
                    alias = e.nextElement();
            }
            if (alias != null) {
                PrivateKey privkey = (PrivateKey) keystore.getKey(alias, keypwd.toCharArray());
                if (privkey == null)
                    privkey = (PrivateKey) keystore.getKey(alias.toLowerCase(), keypwd.toCharArray());
                return privkey;
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

}
