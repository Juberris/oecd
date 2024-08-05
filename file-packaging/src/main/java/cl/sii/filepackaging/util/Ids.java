package cl.sii.filepackaging.util;


import org.apache.commons.lang.StringUtils;
import org.apache.xerces.impl.dv.util.Base64;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.UUID;

public class Ids {
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public Ids() {
    }

    public static String id(String key) {
        String hashcode = String.valueOf(key.hashCode());
        hashcode = hashcode.replace("-", "1");
        hashcode = String.format("%011d", Long.parseLong(hashcode));
        return "" + hashcode + System.currentTimeMillis();
    }

    public static String id(String prefix, String key, String suffix) {
        String hashcode = String.valueOf(key.hashCode());
        hashcode = hashcode.replace("-", "1");
        hashcode = String.format("%011d", Long.parseLong(hashcode));
        return prefix + hashcode + suffix;
    }

    public static String id(Object obj) {
        String hashcode = String.valueOf(System.identityHashCode(obj));
        hashcode = hashcode.replace("-", "1");
        hashcode = String.format("%011d", Long.parseLong(hashcode));
        return hashcode;
    }

    public static String ascii(String key) {
        StringBuilder sb = new StringBuilder();
        boolean upper = true;

        for (int i = 0; i < key.length(); ++i) {
            char ch = key.charAt(i);
            if (upper) {
                ch = Character.toUpperCase(ch);
                upper = false;
            } else {
                ch = Character.toLowerCase(ch);
            }

            if (ch != ' ' && Character.isLetter(ch)) {
                sb.append(ch);
            } else {
                upper = true;
            }
        }

        String string = Normalizer.normalize(sb.toString(), Form.NFD);
        return string.replaceAll("\\p{M}", "");
    }

    public static String md5(String key) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(key.getBytes(), 0, key.length());
            return (new BigInteger(1, m.digest())).toString(16);
        } catch (Exception var2) {
            throw new RuntimeException("Error generando id para [" + key + "]");
        }
    }

    public static String asHex(byte[] buf) {
        char[] chars = new char[2 * buf.length];

        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 240) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 15];
        }

        return new String(chars);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateKey() {
        UUID uuid = UUID.randomUUID();
        byte[] uuidArray = toByteArray(uuid);
        String returnValue = Base64.encode(uuidArray);
        returnValue = StringUtils.removeEnd(returnValue, "\r\n");
        return returnValue;
    }

    public static UUID convertKey(String key) {
        UUID returnValue = null;
        if (StringUtils.isNotBlank(key)) {
            try {
                byte[] decodedArray = Base64.decode(key);
                returnValue = fromByteArray(decodedArray);
            } catch (Exception var3) {
                throw new RuntimeException("Error convirtiendo key base64 [" + key + "] a UUID");
            }
        }

        return returnValue;
    }

    private static byte[] toByteArray(UUID uuid) {
        byte[] byteArray = new byte[16];
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.put(new long[]{uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()});
        return byteArray;
    }

    private static UUID fromByteArray(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        LongBuffer longBuffer = buffer.asLongBuffer();
        return new UUID(longBuffer.get(0), longBuffer.get(1));
    }

    public static String buildMessageRefId(String typeExchange, String sender, String receiver, String year) {
        return typeExchange+sender+receiver+year+"_"+ uuid();
    }
}
