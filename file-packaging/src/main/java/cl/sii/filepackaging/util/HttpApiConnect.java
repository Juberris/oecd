package cl.sii.filepackaging.util;

import cl.sii.filepackaging.client.CtsClient;
import cl.sii.filepackaging.model.AuthRequest;
import cl.sii.filepackaging.model.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.cert.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicBoolean;


@Component
@Slf4j
public class HttpApiConnect {

    private static final Log LOG = LogFactory.getLog(HttpApiConnect.class);
    private static final Http HTTP = new Http(LOG);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    String certificateType = "X.509";
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.pwd}")
    String pwd;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.cert}")
    String cert;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.uri}")
    private String uri;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.ks.keystorePrePath}")
    private String keystorePrePath;
    @Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.usr}")
    private String usr;
    //@Value("${cl.sii.sdi.lob.ss.sara.domain.cts.api.pk}")
    String pk;

    @Autowired
    private CtsClient ctsClient;

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

    public String getToken(){

        String url = uri + "/auth/CL_" + usr;
        System.out.println("URL ==> " + url);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AuthRequest req = objectMapper.readValue(getJsonEncryptPassword(), AuthRequest.class);

            AuthResponse authResponse= ctsClient.getToken(req);
            if (authResponse.getAccess_token() == null) {
                throw new Exception("Error obteniendo access token de uri [" + uri +" y usr [" + usr + "] con certificado [" + cert + "]\n" + authResponse.toString());
            }
            return authResponse.getAccess_token();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean getApiCert(String authToken, Country.CountryCode countryCode){
        AtomicBoolean updated = new AtomicBoolean();
        String url = uri + "/certificates/" + countryCode.name();
        DefaultHttpClient client = Http.create(url);
        try {
            HttpGet request = new HttpGet(url);
            request.addHeader("Authorization", "Bearer " + authToken);
            HttpResponse response = client.execute(HTTP.wrap(request));
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            InputStream data = entity.getContent();
            StringWriter w = new StringWriter();
            IOUtils.copy(data, w, Charset.defaultCharset());
            w.close();
            String crt = w.toString();
            PemReader pemReader = new PemReader(new StringReader(crt));
            PemObject pemObject = pemReader.readPemObject();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(pemObject.getContent()));
            try {
                cert.checkValidity();
            } catch (Exception e) {
                if (CertificateExpiredException.class.isAssignableFrom(e.getClass()) || CertificateNotYetValidException.class.isAssignableFrom(e.getClass())) {

                }
            }
            pemReader.close();
            if (cert == null) {
                throw new Exception("Error validando certificado de uri [" + uri + "] y countryCode [" + countryCode + "]");
            }
            File file = new File(keystorePrePath, "CTS" + countryCode.name() + ".crt");
            if (file.exists()) {
                FileInputStream is = new FileInputStream(file);
                X509Certificate certExisting = get(is);
                is.close();
                String date = DateTime.utcNoMs(cert.getNotBefore()) + "_" + DateTime.utcNoMs(cert.getNotAfter());
                String dateExisting = DateTime.utcNoMs(certExisting.getNotBefore()) + "_" + DateTime.utcNoMs(certExisting.getNotAfter());
                if (!date.equals(dateExisting)) {
                    File fileBack = new File(keystorePrePath, "CTS" + countryCode.name() + "_" + dateExisting + ".crt");
                    if (!fileBack.exists()) {
                        fileBack.setWritable(true);
                        System.out.println(file + "[" + file.exists() + "]->" + fileBack + "[" + fileBack.exists() + "]");
                        Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        updated.set(true);
                    }
                } else {
                    return true;
                }
            }
            FileWriter fw = new FileWriter(file);
            PemWriter pemWriter = new PemWriter(fw);
            pemWriter.writeObject(new PemObject("CERTIFICATE", cert.getEncoded()));
            pemWriter.close();
            System.out.println("response ==>" + response.toString());
        }catch (Exception e){
            log.error("Error al generar certificado ===> {}", countryCode);
            return false;
        }
        System.out.println("cert ==> " + cert);
        return true;
    }
    public String getJsonEncryptPassword() throws Exception {


        JsonWebEncryption jwe = new JsonWebEncryption();
        System.out.println("password ==> " + pwd);
        System.out.println("certificado ==> " + cert);
        jwe.setPlaintext(pwd);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);
        jwe.setKey(getKey());
        String pwdEncrypted = jwe.getCompactSerialization();
        System.out.println("pwdEncrypted ==> " + pwdEncrypted);
        System.out.println("jwe.getPlaintextString() ==> " + jwe.getPlaintextString());
        StringWriter jsonRequest = new StringWriter();
        JsonFactory jfactory = new JsonFactory();
        final JsonGenerator jGenerator = jfactory.createJsonGenerator(jsonRequest);
        jGenerator.writeStartObject();
        {
            jGenerator.writeStringField("userPassword", pwdEncrypted);
        }
        jGenerator.writeEndObject();
        jGenerator.close();
        System.out.println("jsonRequest ==> " + jsonRequest.toString());
        return jsonRequest.toString();
    }


    protected synchronized RSAPublicKey getKey() throws Exception {

        if (publicKey == null) {
            publicKey = (RSAPublicKey) getCert(cert).getPublicKey();
        }
        return publicKey;
    }

    protected synchronized RSAPrivateKey getPKey() throws Exception {

        if (privateKey == null) {
            privateKey = (RSAPrivateKey) getCert(pk);
        }
        return privateKey;
    }
    private Certificate getCert(String certfile) throws Exception {
        try {
            CertificateFactory cf = CertificateFactory.getInstance(certificateType);
            FileInputStream fs = new FileInputStream(new File(certfile));
            Certificate cert = cf.generateCertificate(fs);
            fs.close();
            return cert;
        } catch (Exception e) {
            throw e;
        }
    }

    public static X509Certificate get(InputStream s) throws Exception {
        IOResources io = new IOResources();
        PemReader pemReader = (PemReader) io.add(new PemReader(new InputStreamReader(s)));
        try {
            PemObject pemObject = pemReader.readPemObject();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(pemObject.getContent()));
        } catch (Exception e) {
            throw new Exception("Error construyendo certificado");
        } finally {
            io.close();
        }
    }
}

