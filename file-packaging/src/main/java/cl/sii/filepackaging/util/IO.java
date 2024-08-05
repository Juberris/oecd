package cl.sii.filepackaging.util;


import cl.sii.filepackaging.model.schema.cts2.CTSCommunicationTypeCdType;
import cl.sii.filepackaging.model.schema.cts2.CTSSenderFileMetadataType;

import java.io.*;
import java.util.Date;

public class IO {
    public IO() {
    }

    public static OutputStream out(File f) {
        try {
            return new FileOutputStream(f);
        } catch (Exception var2) {
            throw new RuntimeException("Error obteniendo archivo: " + f, var2);
        }
    }

    public static void save(String text, File f) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(f));
            w.write(text);
            w.close();
        } catch (Exception var3) {
            throw new RuntimeException("Error escribiendo texto: " + text + ", en archivo: " + f, var3);
        }
    }

    public static String buildSenderFileName(CTSSenderFileMetadataType metaData, Date date) {
        return buildSenderFileName(metaData.getCTSSenderCountryCd(), metaData.getCTSCommunicationTypeCd(), DateTime.formatUTC(date));
    }

    public static String buildSenderFileName(String sender, CTSCommunicationTypeCdType ctsType, String utc) {

        return concat("_", sender, ctsType.value(), utc);
    }
    public static String concat(String sep, Object... values) {
        StringBuilder sb = new StringBuilder();
        int iNotNull = 0;

        for(int i = 0; i < values.length; ++i) {
            if (values[i] != null) {
                if (iNotNull > 0 && sep != null) {
                    sb.append(sep);
                }

                sb.append(values[i].toString());
                ++iNotNull;
            }
        }

        return sb.toString();
    }
    public static String path(String path) {
        return path(new File(path));
    }

    public static String path(File f) {
        try {
            return f != null && f.getName().trim().length() > 0 ? f.getCanonicalPath() : null;
        } catch (Exception var2) {
            throw new RuntimeException("Error obtenindo ruta de archivo: " + f, var2);
        }
    }

    public static void copy(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[8192];

            for(int i = in.read(buffer); i != -1; i = in.read(buffer)) {
                out.write(buffer, 0, i);
            }

            out.flush();
        } catch (Exception var4) {
            throw new RuntimeException("Error copiando stream", var4);
        }
    }
}
