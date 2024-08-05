package cl.sii.filepackaging.service;



import cl.sii.filepackaging.model.AppProp;
import cl.sii.filepackaging.util.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.*;



@Data
@Slf4j
public class CtsKeystore {


    private CtsHttpApi ctsHttpApi;

    private String keystorePrePath;
    private String keystorePwd;
    private String keyPwd;
    private int keyLength;

    private boolean offline;
    private boolean forceUpdate;


    private Map<String, Set<CtsCert>> map = new TreeMap();

    public String getSenderPath(String country) {
        return this.getSenderPath(new Country(country));
    }

    public String getSenderPath(Country country) {
        return this.keystorePrePath + "/CTS" + country.getId() + ".p12";
    }

    public String getReceiverPath(String country) throws IOException {
        return this.getReceiverPath(new Country(country));
    }
    public String getReceiverPath(Country country) throws IOException {
        String path = this.getReceiverPathOnly(country);
        Class var3 = CtsKeystore.class;
        synchronized(CtsKeystore.class) {
            if (this.forceUpdate) {
                this.ctsHttpApi.updateCert(country.getCode());
            } else {
                CertStatus cerStatus = this.validate(path);
                if (cerStatus.status == CertStatus.Status.MISSING || cerStatus.status == CertStatus.Status.INVALID_DATE || cerStatus.status == CertStatus.Status.INVALID_KEY_LENGTH) {
                    if (this.offline) {
                        log.warn("Fecha de certificado [" + IO.path(path) + "]");
                        throw new RuntimeException("Validación de certificado [" + IO.path(path) + "] offline falló, status [" + cerStatus + "]");

                    }

                    this.ctsHttpApi.updateCert(country.getCode());
                    cerStatus = this.validate(path);
                    if (cerStatus.status == CertStatus.Status.INVALID_DATE) {
                        log.warn("Fecha de certificado [" + IO.path(path) + "] no es valida");
                    }
                }
            }

            return path;
        }
    }

    public CertStatus validate(String path) throws IOException {
        if (log.isTraceEnabled()) {
            log.trace("Validando certificado [" + path + "]");
        }

        if (!(new File(path)).exists()) {
            return new CertStatus(CertStatus.Status.MISSING, path);
        } else {
            IOResources resources = new IOResources();

            CertStatus var4;
            try {
                CertStatus status = this.validate(new FileReader(path), IO.path(path));
                if (log.isTraceEnabled()) {
                    log.trace("Status certificado [" + path + "] es [" + status + "]");
                }

                var4 = status;
            } catch (IOException var8) {
                throw new RuntimeException("Error validando certificado  [" + IO.path(path) + "]");
            } finally {
                resources.close();
            }

            return var4;
        }
    }
    public CertStatus validate(Reader reader, String id) {
        try {
            PemReader pemReader = new PemReader(reader);
            PemObject pemObject = pemReader.readPemObject();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(pemObject.getContent()));
            pemReader.close();

            try {
                cert.checkValidity();
            } catch (Exception var9) {
                String date = IO.concat(":", new Object[]{DateTime.utc(cert.getNotBefore()), DateTime.utc(cert.getNotAfter())});
                return new CertStatus(CertStatus.Status.INVALID_DATE, "Certificado [" + id + "], Validity [" + date + "]");
            }

            RSAPublicKey rsaPk = (RSAPublicKey)cert.getPublicKey();
            int bits = rsaPk.getModulus().bitLength();
            return bits < this.keyLength ? new CertStatus(CertStatus.Status.INVALID_KEY_LENGTH, "Certificado [" + id + "], Key Length [" + bits + "] es menor que el esperado [" + this.keyLength + "]") : new CertStatus(CertStatus.Status.VALID, "OK");
        } catch (Exception var10) {

            throw new RuntimeException("Error validando certificado [" + id + "]");
        }
    }

    public String getReceiverPathOnly(Country country) {
        return this.keystorePrePath + "/CTS" + country.getCode() + ".crt";
    }
    public static class CertStatus {
        public final Status status;
        public final String info;

        public CertStatus(Status status, String info) {
            this.status = status;
            this.info = info;
        }

        public boolean valid() {
            return this.status == Status.VALID;
        }

        public String toString() {
            return this.status.name() + ". " + this.info;
        }

        public static enum Status {
            VALID,
            INVALID_DATE,
            INVALID_KEY_LENGTH,
            MISSING;

            private Status() {
            }
        }
    }
    public interface CtsCertListener<T> {
        boolean next(CtsCert var1);

        T get();
    }
    public static enum CtsCertFormat {
        P12,
        PEM;

        private CtsCertFormat() {
        }
    }
    public void refresh() {
        this.map.clear();
        File dir = new File(this.keystorePrePath);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".crt") || name.endsWith(".p12");
            }
        });
        Set<String> keys = new HashSet();
        if (files != null && files.length != 0) {
            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                CtsCertFormat format = file.getName().endsWith(".p12") ? CtsCertFormat.P12 : CtsCertFormat.PEM;
                String country = file.getName().startsWith("CTS") ? file.getName().substring(3, 5) : file.getName();
                Set<CtsCert> set = (Set)this.map.get(country);
                if (set == null) {
                    set = new TreeSet();
                    this.map.put(country, set);
                }

                StringBuilder key = new StringBuilder();
                if (format == CtsCertFormat.PEM) {
                    try {
                        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                        Collection<X509Certificate> certs = (Collection<X509Certificate>) certFactory.generateCertificates(new FileInputStream(file));
                        Iterator var14 = certs.iterator();

                        while(var14.hasNext()) {
                            X509Certificate cert = (X509Certificate)var14.next();
                            key.append(IO.concat(":", new Object[]{country, DateTime.utc(cert.getNotBefore()), DateTime.utc(cert.getNotAfter())}));
                        }
                    } catch (Exception var16) {
                        log.error(String.valueOf(file), var16);
                    }
                }

                if (format == CtsCertFormat.P12 || !keys.contains(key.toString())) {
                    CtsCert cert = new CtsCert();
                    cert.file = file;
                    cert.country = country;
                    cert.format = format;
                    cert.date = file.lastModified();
                    ((Set)set).add(cert);
                    if (format == CtsCertFormat.PEM) {
                        keys.add(key.toString());
                    }
                }
            }

            if (log.isDebugEnabled()) {
                Iterator var17 = this.map.keySet().iterator();

                while(var17.hasNext()) {
                    String country = (String)var17.next();
                    log.debug(country);
                    Iterator var19 = ((Set)this.map.get(country)).iterator();

                    while(var19.hasNext()) {
                        CtsCert cert = (CtsCert)var19.next();
                        log.debug((new SimpleDateFormat("yyyyMMdd_HHmmssSSS")).format(new Date(cert.date)));
                    }
                }
            }

        } else {
            log.warn("No existe directorio [" + dir + "] o esta vacio");
        }
    }

    public <T> T browse(Class<T> type, String country, CtsCertListener<T> l) {
        Set<CtsCert> certs = (Set)this.map.get(country.substring(0, 2));
        if (certs != null) {
            Iterator var5 = certs.iterator();

            while(var5.hasNext()) {
                CtsCert cert = (CtsCert)var5.next();
                if (!l.next(cert)) {
                    break;
                }
            }
        }

        return l.get();
    }

    public static class CtsCert implements Comparable<CtsCert> {
        public File file;
        public CtsCertFormat format;
        public String country;
        public long date;
        public String from;
        public String to;

        public CtsCert() {
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof CtsCert)) {
                return false;
            } else {
                CtsCert other = (CtsCert)o;
                return this.key().equals(other.key());
            }
        }

        public int hashCode() {
            return this.key().hashCode();
        }

        public int compareTo(CtsCert o) {
            return o.key().compareTo(this.key());
        }

        public String key() {
            return IO.concat("_", new Object[]{this.date, this.country, this.format, this.file.getName()});
        }
    }

}
