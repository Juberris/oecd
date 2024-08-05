package cl.sii.filepackaging.util.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Slf4j
public class CtsSecurity {


    public static void signXml(InputStream in, OutputStream out, String keystorefile, String keystorepwd, String keypwd, String docType) {
        try {
            CtsSigner signer = new CtsSigner();
            signer.signFile(in, out, CtsUtilShared.getPrivateKey(keystorefile, keystorepwd, keypwd), CtsUtilShared.getCert(keystorefile, keystorepwd), docType.equalsIgnoreCase("XML") ? CtsSigner.SigDocType.XML:CtsSigner.SigDocType.BINARY, signer.sigXmlTransform, signer.getSigRefUriVal());
        } catch (Exception var6) {
            log.error("Error firmando archivo {}, Error: {}", docType, var6.getMessage());
            log.error("Error :", var6);
        }
    }

    public static void zip(String[] dataFilePaths, InputStream[] payloads, OutputStream out) {
        ZipOutputStream zipOut = null;

        try {
            zipOut = new ZipOutputStream(out);
            zipOut.setMethod(8);
            zipOut.setLevel(9);

            for(int i = 0; i < dataFilePaths.length; ++i) {
                ZipEntry entry = new ZipEntry(dataFilePaths[i]);
                zipOut.putNextEntry(entry);
                IOUtils.copy(payloads[i], zipOut);
                zipOut.closeEntry();
            }
        } catch (Exception var13) {
            throw new CtsSecurityException("Error creando zip de archivos " + Arrays.toString(dataFilePaths), var13);
        } finally {
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (Exception var12) {
                }
            }

        }

    }

    public static byte[] zipAndCipher(String filename, InputStream in, OutputStream out) {
        try {
            SecretKey secretKey = createKey();
            Cipher cipher = createCipher(secretKey);
            OutputStream cipherOut = new CipherOutputStream(out, cipher);
            ZipOutputStream zipOut = new ZipOutputStream(cipherOut) {
                public void close() throws IOException {
                    this.closeEntry();
                    super.close();
                }
            };
            zipOut.setMethod(8);
            zipOut.setLevel(9);
            zipOut.putNextEntry(new ZipEntry(filename));
            IOUtils.copy(in, zipOut);
            zipOut.close();
            return (new SKeyPlusIv(secretKey, cipher.getIV())).bytes;
        } catch (Exception var7) {
            throw new CtsSecurityException("Error zipeando y cifrando archivo[" + filename + "]", var7);
        }
    }
    public static byte[] cipher(byte[] sKeyPlusIvBytes, String keystorefile, String keystorepwd) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            cipher(sKeyPlusIvBytes, keystorefile, keystorepwd, out);
            out.close();
            return out.toByteArray();
        } catch (Exception var4) {
            throw new CtsSecurityException("Error cifrando llave", var4);
        }
    }

    public static void cipher(byte[] sKeyPlusIvBytes, String keystorefile, String keystorepwd, OutputStream out) {
        try {
            SKeyPlusIv sKeyPlusIv = new SKeyPlusIv(sKeyPlusIvBytes);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(3, keystorepwd != null ? CtsUtilShared.getCert(keystorefile, keystorepwd).getPublicKey() : CtsUtilShared.getCert(keystorefile).getPublicKey());
            byte[] ivBuf = sKeyPlusIv.ivBytes;
            byte[] skeyBuf = sKeyPlusIv.sKeyBytes;
            byte[] skeyPlusIvBuf = new byte[skeyBuf.length + ivBuf.length];
            System.arraycopy(skeyBuf, 0, skeyPlusIvBuf, 0, skeyBuf.length);
            System.arraycopy(ivBuf, 0, skeyPlusIvBuf, skeyBuf.length, ivBuf.length);
            out.write(cipher.wrap(new SecretKeySpec(skeyPlusIvBuf, "AES")));
        } catch (Exception var9) {
            throw new CtsSecurityException("Error cifrando llave", var9);
        }
    }

    public static SecretKey createKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256);
            return generator.generateKey();
        } catch (Exception var1) {
            throw new CtsSecurityException("Error creando cipher", var1);
        }
    }

    public static byte[] uncipher(byte[] key, String keystorefile, String keystorepwd, String keypwd) {
        return uncipher((InputStream)(new ByteArrayInputStream(key)), keystorefile, keystorepwd, keypwd);
    }

    public static byte[] uncipher(InputStream in, String keystorefile, String keystorepwd, String keypwd) {
        byte[] buf = new byte[16384];
        byte[] skeyBuf = null;

        try {
            BufferedInputStream bis = new BufferedInputStream(in);

            int len;
            while((len = bis.read(buf)) != -1) {
                if (skeyBuf == null) {
                    skeyBuf = new byte[len];
                    System.arraycopy(buf, 0, skeyBuf, 0, len);
                } else {
                    int count = skeyBuf.length;
                    skeyBuf = Arrays.copyOf(skeyBuf, skeyBuf.length + len);
                    System.arraycopy(buf, 0, skeyBuf, count, len);
                }
            }

            bis.close();
            bis = null;
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(4, CtsUtilShared.getPrivateKey(keystorefile, keystorepwd, keypwd));
            SecretKey skey = (SecretKey)cipher.unwrap(skeyBuf, "AES", 3);
            return (new SKeyPlusIv(skey.getEncoded())).bytes;
        } catch (Exception var11) {
            log.error("Error uncipher ==> Error descifrando llave con [{}] , key length [{}], {}",keystorefile,skeyBuf.length, var11);
            throw new CtsSecurityException("Error descifrando llave con [" + keystorefile + "], key length [" + skeyBuf.length + "]", var11);
        }
    }

    public static Cipher createCipher(SecretKey sKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, sKey);
            return cipher;
        } catch (Exception var2) {
            throw new CtsSecurityException("Error creando cipher", var2);
        }
    }
    public static Cipher createCipher(SecretKey sKey, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, sKey, iv);
            return cipher;
        } catch (Exception var3) {
            throw new CtsSecurityException("Error creando cipher", var3);
        }
    }
    public static InputStream uncipherAndUnzip(byte[] sKeyPlusIvBytes, InputStream in) {
        try {
            SKeyPlusIv sKeyPlusIv = new SKeyPlusIv(sKeyPlusIvBytes);
            Cipher cipher = createCipher(sKeyPlusIv.sKey, sKeyPlusIv.iv);
            InputStream cipherIn = new CipherInputStream(in, cipher);
            ZipInputStream zipIn = new ZipInputStream(cipherIn) {
                public void close() throws IOException {
                    this.closeEntry();
                    super.close();
                }
            };
            if (zipIn.getNextEntry() == null) {
                throw new CtsSecurityException("zip no contiene archivos");
            } else {
                return zipIn;
            }
        } catch (Exception var6) {
            throw new CtsSecurityException("Error decifrando y unzipeando", var6);
        }
    }

    public static class SKeyPlusIv {
        public final byte[] bytes;
        public final byte[] sKeyBytes;
        public final byte[] ivBytes;
        public final SecretKey sKey;
        public final IvParameterSpec iv;

        public SKeyPlusIv(SecretKey sKey, byte[] ivBytes) {
            this.sKey = sKey;
            this.iv = new IvParameterSpec(ivBytes);
            this.sKeyBytes = sKey.getEncoded();
            this.ivBytes = ivBytes;
            this.bytes = new byte[this.sKeyBytes.length + ivBytes.length];
            System.arraycopy(this.sKeyBytes, 0, this.bytes, 0, this.sKeyBytes.length);
            System.arraycopy(ivBytes, 0, this.bytes, this.sKeyBytes.length, ivBytes.length);
        }

        public SKeyPlusIv(byte[] bytes) {
            this.bytes = bytes;
            this.sKeyBytes = new byte[32];
            this.ivBytes = new byte[bytes.length - this.sKeyBytes.length];
            System.arraycopy(bytes, 0, this.sKeyBytes, 0, this.sKeyBytes.length);
            System.arraycopy(bytes, this.sKeyBytes.length, this.ivBytes, 0, this.ivBytes.length);
            this.sKey = new SecretKeySpec(this.sKeyBytes, "AES");
            this.iv = new IvParameterSpec(this.ivBytes);
        }
    }

    public static class CtsSecurityException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public CtsSecurityException() {
        }

        public CtsSecurityException(String message, Throwable cause) {
            super(message, cause);
        }

        public CtsSecurityException(String message) {
            super(message);
        }

        public CtsSecurityException(Throwable cause) {
            super(cause);
        }
    }
}
