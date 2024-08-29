package cl.sii.cts2.data.export.util;

import cl.sii.cts2.data.export.entities.cts.Cts2DP;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static cl.sii.cts2.data.export.util.Xml.read;

@Component
@Slf4j
public class PackageZipHandler {
    @Getter
    byte[] pyld =null;

    @Value("${dir.inbox}")
    String pathInbox;

    @Value("${dir.certs}")
    String pathCerts;

    @Value("${cts.ks.keystorePwd}")
    String keystorePwd;

    @Value("${cts.ks.keyPwd}")
    String keyPwd;

    public void getPayloadFile(Cts2DP dp) {
        byte[] md =null;
        byte[] key = null;
        byte[] bytes = null;
        File zip = new File(pathInbox+"//"+dp.getFilename());
        try (FileInputStream fis = new FileInputStream(zip); BufferedInputStream bis = new BufferedInputStream(fis); ZipInputStream zis = new ZipInputStream(bis)) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                if (ze.getName().endsWith("_Payload")) {
                    pyld = IOUtils.toByteArray(zis);
                } else if (ze.getName().endsWith("Metadata.xml")) {
                    md = IOUtils.toByteArray(zis);
                } else if (ze.getName().endsWith("_Key")) {
                    key = IOUtils.toByteArray(zis);
                }
            }

            byte[] keyClr = decryptKey(key);
            if(keyClr!=null){
                    byte[] signedPayload=decryptPayload(keyClr, pyld);
                    this.pyld=getUnsignedPayload(signedPayload);
            }else{
                this.pyld=null;
            }


        } catch (FileNotFoundException e) {
            log.warn("Missing File {}",dp.getFilename());
        } catch (Exception e) {
            this.pyld=null;
            log.warn("Error File {} - Error : {}",dp.getFilename(), e.getMessage());
        }

    }


    String pyldKey(Cts2DP dp) {
        return dp.toKey() + "_pyld";
    }

    String mdKey(Cts2DP dp) {
        return dp.toKey() + "_md";
    }

    String keyKey(Cts2DP dp) {
        return dp.toKey() + "_key";
    }

    byte[] decryptKey( byte[] key) throws Exception {
        byte[] skeyPlusIvBytes;
        File dir = new File(String.valueOf(this.pathCerts)).getCanonicalFile();
        FileFilter fileFilter = new WildcardFileFilter(new String[]{"*.p12"});
        for (final File certFile : Objects.requireNonNull(dir.listFiles(fileFilter))){
            log.info("Intentando certificado para decifrar llave: " + certFile);
            X509Certificate x509 = (X509Certificate) CtsUtilShared.getCert(certFile.getAbsolutePath(), keystorePwd);
            String ksPath = certFile.getAbsolutePath();
            String ksPwd =this.keystorePwd;
            String pkPwd = this.keyPwd;
            try {
                skeyPlusIvBytes = CtsSecurity.uncipher(key, ksPath, ksPwd, pkPwd);
                log.warn("Certificado utilizado para decifrar llave: " + certFile);
                return skeyPlusIvBytes;
            }catch (Exception e){
                log.warn("##############################LLAVE OBSOLETA  {} ##############################",certFile );
            }

        }
        return null;
    }

    public byte[] decryptPayload(byte[] keyDecrypted, byte[] pyld) throws Exception {
        return IOUtils.toByteArray(CtsSecurity.uncipherAndUnzip(keyDecrypted, new ByteArrayInputStream(pyld)));
    }

    public byte[] getUnsignedPayload(byte[] signedPyld){
        byte[] out=null;

        try (ByteArrayOutputStream data = new ByteArrayOutputStream()) {
            Cts2ExportScript.modify(new OutputStreamWriter(data), new Cts2ExportScript.XmlMod() {
                @Override
                public boolean include(String path) {
                    return path.contains("_OECD");
                }
            }, l -> {

                try {
                    read(new BOMInputStream(new ByteArrayInputStream(signedPyld)), l);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });

            out=data.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return  out;
    }
}
