package cl.sii.filepackaging.service;

import cl.sii.filepackaging.model.AppProp;
import cl.sii.filepackaging.util.DateTime;
import cl.sii.filepackaging.util.security.CtsSecurity;
import cl.sii.filepackaging.util.security.CtsUtilShared;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@Component
@Slf4j
@Data
public class Unpacker {
    CtsKeystore ctsKeystore;

    @Autowired
    AppProp ctsProps;
    @Autowired
    StatusMessageService statusMessageService;

    public <T> T openZip(File zip, ZipListener<T> l) throws Exception {
        byte[] pyld = null;
        byte[] md = null;
        byte[] key = null;
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
            if (log.isDebugEnabled()) {
                log.debug("PYLD [" + (pyld != null ? pyld.length : null) + "] MD [" + (md != null ? md.length : null) + "] KEY [" + (key != null ? key.length : null) + "]");
            }
        }
        return l.on(pyld, md, key);
    }
    public byte[] decryptKey(String country, byte[] key){
        ctsKeystore.setKeystorePrePath(ctsProps.getKeystorePrePath());
        ctsKeystore.setKeystorePwd(ctsProps.getKeystorePwd());
        ctsKeystore.setKeyPwd(ctsProps.getKeyPwd());
        ctsKeystore.refresh();
        return ctsKeystore.browse(byte[].class, country, new CtsKeystore.CtsCertListener<byte[]>() {
            private CtsSecurity.CtsSecurityException e;
            private byte[] skeyPlusIvBytes;

            @Override
            public boolean next(CtsKeystore.CtsCert cert) {
                try {
                    if (cert.format == CtsKeystore.CtsCertFormat.P12) {
                        log.info("Intentando certificado para decifrar llave: " + cert.file);
                        X509Certificate x509 = (X509Certificate) CtsUtilShared.getCert(cert.file.getAbsolutePath(), ctsKeystore.getKeystorePwd());
                        log.trace("Valido Desde [" + DateTime.utcNoMs(x509.getNotBefore()) + "] Hasta [" + DateTime.utcNoMs(x509.getNotAfter()) + "]");
                        String ksPath = cert.file.getAbsolutePath();
                        String ksPwd = ctsProps.getKeystorePwd();
                        String pkPwd = ctsProps.getKeyPwd();
                        skeyPlusIvBytes = CtsSecurity.uncipher(key, ksPath, ksPwd, pkPwd);
                        e = null;
                        log.info("Certificado utilizado para decifrar llave: " + cert.file);
                        return false;
                    }
                } catch (CtsSecurity.CtsSecurityException e) {
                    this.e = e;
                } catch (Exception e) {
                    this.e = new CtsSecurity.CtsSecurityException("Error inesperado", e);
                }
                return true;
            }

            @Override
            public byte[] get() {
                if (e != null) {
                    throw e;
                }
                if (skeyPlusIvBytes == null) {
                    statusMessageService.addRecordError("50002",null,null);
                    throw new CtsSecurity.CtsSecurityException("No se pudo decifrar key");
                }
                return skeyPlusIvBytes;
            }
        });
    }

    public byte[] decryptPayload(byte[] keyDecrypted, byte[] pyld) throws Exception {
        return IOUtils.toByteArray(CtsSecurity.uncipherAndUnzip(keyDecrypted, new ByteArrayInputStream(pyld)));
    }

    public static interface ZipListener<T> {
        T on(byte[] pyldBytes, byte[] mdBytes, byte[] keyBytes) throws Exception;
    }
}
