package cl.sii.filepackaging.model.schema;


import cl.sii.filepackaging.util.Country;
import cl.sii.filepackaging.util.DateTime;
import cl.sii.filepackaging.util.Http;
import cl.sii.filepackaging.util.IOResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;


@Slf4j
public class CtsApi {
    @Value("${cts.ks.keystorePrePath}")
    private String keystorePrePath;
    @Value("${cts.api.uri}")
    private String uri;


    private static Http HTTP;

    public boolean updateCert(final Country.CountryCode countryCode) {
        final AtomicBoolean updated = new AtomicBoolean();
        String url = this.uri + "/certificates/" + countryCode.name();
        DefaultHttpClient client = Http.create(url);

        try {
            String accessToken = "this.ctsApiAuthenticator.authenticate();";
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "Bearer " + accessToken);
            HttpResponse response = client.execute(HTTP.wrap(request));
            HTTP.process(response, new Http.DownloadCallback() {
                public void call(int code, String msg, InputStream data) throws IOException {
                    try {
                        if (code != 200) {
                            log.warn("Error updateCert [" + countryCode + "]\n" + IOUtils.toString(data));
                        } else {
                            StringWriter w = new StringWriter();
                            IOUtils.copy(data, w);
                            w.close();
                            String crt = w.toString();
                            PemReader pemReader = new PemReader(new StringReader(crt));
                            PemObject pemObject = pemReader.readPemObject();
                            CertificateFactory cf = CertificateFactory.getInstance("X.509");
                            X509Certificate cert = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(pemObject.getContent()));

                            try {
                                cert.checkValidity();
                            } catch (Exception var16) {
                                if (!CertificateExpiredException.class.isAssignableFrom(var16.getClass()) && CertificateNotYetValidException.class.isAssignableFrom(var16.getClass())) {
                                }
                            }

                            pemReader.close();
                            if (cert == null) {
                                log.error("Error validando certificado de uri [" + CtsApi.this.uri + "] y countryCode [" + countryCode + "]");
                                throw new Exception();
                            } else {
                                File file = new File(CtsApi.this.keystorePrePath, "CTS" + countryCode.name() + ".crt");
                                if (file.exists()) {
                                    FileInputStream is = new FileInputStream(file);
                                    X509Certificate certExisting = CtsApi.get(is);
                                    is.close();
                                    String date = DateTime.utcNoMs(cert.getNotBefore()) + "_" + DateTime.utcNoMs(cert.getNotAfter());
                                    String dateExisting = DateTime.utcNoMs(certExisting.getNotBefore()) + "_" + DateTime.utcNoMs(certExisting.getNotAfter());
                                    if (date.equals(dateExisting)) {
                                        return;
                                    }

                                    File fileBack = new File(CtsApi.this.keystorePrePath, "CTS" + countryCode.name() + "_" + dateExisting + ".crt");
                                    if (!fileBack.exists()) {
                                        fileBack.setWritable(true);
                                        System.out.println(file + "[" + file.exists() + "]->" + fileBack + "[" + fileBack.exists() + "]");
                                        Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                        updated.set(true);
                                    }
                                }

                                FileWriter fw = new FileWriter(file);
                                PemWriter pemWriter = new PemWriter(fw);
                                pemWriter.writeObject(new PemObject("CERTIFICATE", cert.getEncoded()));
                                pemWriter.close();
                            }
                        }

                    } catch (Exception var18) {
                        log.error("Error validando certificado de uri [" + CtsApi.this.uri + "] y countryCode [" + countryCode + "]");

                    }
                }
            });
        } catch (Exception var13) {
            log.error("Error obteniendo certificado de uri [" + this.uri + "] y countryCode [" + countryCode + "]");
        } finally {
            HttpClientUtils.closeQuietly(client);
        }

        return updated.get();
    }

    public static X509Certificate get(InputStream s) throws Exception {
        IOResources io = new IOResources();
        PemReader pemReader = (PemReader)io.add(new PemReader(new InputStreamReader(s)));

        X509Certificate var5;
        try {
            PemObject pemObject = pemReader.readPemObject();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            var5 = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(pemObject.getContent()));
        } catch (Exception var9) {
            log.error("Error construyendo certificado");
            throw new Exception();
        } finally {
            io.close();
        }

        return var5;
    }

}
