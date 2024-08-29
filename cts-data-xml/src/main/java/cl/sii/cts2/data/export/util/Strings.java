package cl.sii.cts2.data.export.util;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;


import jakarta.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Base64;

public class Strings {


    public static Integer getInt(String text, int bgn, int end) {
        if (text != null) {
            if (end > bgn && text.length() > bgn && text.length() > end) {
                try {
                    return Integer.parseInt(text.substring(bgn, end));
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static String format(Number number) {
        return new DecimalFormat("#,##0.00").format(number);
    }

    public static String[] csv2Array(String csv) {
        if (csv == null) {
            return new String[] {};
        }
        return csv.split("\\,");
    }

    public static String[] tsv2Array(String tsv) {
        if (tsv == null) {
            return new String[] {};
        }
        return tsv.split("\\t");
    }

    public static String array2Csv(String... items) {
        if (items != null && items.length > 0) {
            int index = 0;
            StringBuilder sb = new StringBuilder();
            for (String item : items) {
                if (index > 0) {
                    sb.append(",");
                }
                if (item != null && item.trim().length() > 0) {
                    sb.append(item);
                    index++;
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static String truncate(String str, int max) {
        if (str != null && str.length() > max) {
            return str.substring(0, max);
        }
        return str;
    }

    public static String concatWithSpacesForNull(String sep, Object... values) {
        return concatWithCharForNull(sep, "", values);
    }

    public static String concatWithCharForNull(String sep, String ch, Object... values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0 && sep != null) {
                sb.append(sep);
            }
            if (values[i] != null) {
                sb.append(values[i].toString());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String concat(String sep, Object... values) {
        StringBuilder sb = new StringBuilder();
        int iNotNull = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                if (iNotNull > 0 && sep != null) {
                    sb.append(sep);
                }
                sb.append(values[i].toString());
                iNotNull++;
            }
        }
        return sb.toString();
    }

    public static String trim(String txt, int length) {
        String newTxt = txt;
        if (isEmpty(txt)) {
            return newTxt;
        }
        int index = txt.indexOf("\n");
        if (index > 0) {
            newTxt = txt.substring(0, index);
        }
        if (newTxt.length() < length) {
            return index > 0 ? newTxt + " [" + txt.length() + "]..." : newTxt;
        }
        return newTxt.substring(0, length) + " [" + txt.length() + "]...";
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || isEmpty(obj.toString());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String nvl(String str, String def) {
        return isEmpty(str) ? def : str;
    }

    public static String nvl(Object obj, Object def) {
        return isEmpty(obj) ? def.toString() : obj.toString();
    }

    public static boolean isNumber(String str) {
        try {
            if (!isEmpty(str)) {
                Integer.parseInt(str);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isBoolean(String str) {
        try {
            if (!isEmpty(str)) {
                return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static Integer asInt(String str) {
        return !isNumber(str) ? null : Integer.parseInt(str.trim());
    }

    public static Boolean asBoolean(String str) {
        return !isBoolean(str) ? null : str.trim().equalsIgnoreCase("true");
    }

    public static String clean(String str, String... values) {
        String newStr = str;
        if (!isEmpty(newStr)) {
            newStr = newStr.trim();
            if (values != null && values.length > 0) {
                for (String value : values) {
                    if (newStr.equals(value)) {
                        return null;
                    }
                }
            }
            return newStr;
        }
        return null;
    }

    public static boolean anyNotNull(Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean anyNull(Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value == null) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    public static String normalize(String txt) {
        if (txt == null) {
            return null;
        }
        return removeAccents(txt).toUpperCase();
    }

    public static String removeAccents(String txt) {
        if (txt == null) {
            return null;
        }
        String string = Normalizer.normalize(txt, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{M}", "");
        return string;
    }

    public static boolean equals(String l, String... rs) {
        for (String r : rs) {
            if (l != null ? l.equals(r) : l == r) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(String l, String... rs) {
        for (String r : rs) {
            if (l.contains(r)) {
                return true;
            }
        }
        return false;
    }

    public static Integer getInt(List<String> list, int index) {
        if (list != null) {
            if (list.size() > index) {
                return Integer.valueOf(list.get(index));
            }
        }
        return null;
    }

    public static Boolean getBool(List<String> list, int index) {
        if (list != null) {
            if (list.size() > index) {
                return "true".equals(list.get(index));
            }
        }
        return null;
    }

    public static String get(List<String> list, int index) {
        if (list != null) {
            if (list.size() > index) {
                return list.get(index);
            }
        }
        return null;
    }

    public static boolean isRutStructValid(String rut) {
        if (!isEmpty(rut)) {
            return rut.matches("^0*(\\d{1,3}(\\d{3})*)([\\dkK])$");
        }
        return false;
    }

    public static boolean isRutValid(String rut) {
        if (!isEmpty(rut)) {
            boolean validacion = false;
            try {
                rut = rut.toUpperCase();
                char dv = rut.charAt(rut.length() - 1);
                validacion = dv == calcDv(rut.substring(0, rut.length() - 1));
            } catch (Exception e) {
                validacion = false;
            }
            return validacion;
        }
        return false;
    }

    public static Character calcDv(String rut) {
        try {
            int m = 0, s = 1;
            int rutAux = Integer.parseInt(rut);
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            return (char) (s != 0 ? s + 47 : 75);
        } catch (Exception e) {
            return null;
        }
    }

    public static String fill(int num, String txt) {
        StringBuilder fill = new StringBuilder();
        for (int i = 0; i < num; i++) {
            fill.append(txt);
        }
        return fill.toString();
    }

    public static class PW {
        public final StringWriter w;
        public final PrintWriter out;

        public PW() {
            w = new StringWriter();
            out = new PrintWriter(w);
        }

        public PW println() {
            out.println();
            return this;
        }

        public PW println(String s) {
            out.println(s);
            return this;
        }

        public PW print(String s) {
            out.print(s);
            return this;
        }

        public void close() {
            out.close();
        }

        @Override
        public String toString() {
            out.flush();
            return w.toString();
        }

    }

    public static String simplify(String s) {
        if (s != null) {
            StringBuilder sb = new StringBuilder();
            s = s.toUpperCase(Locale.ENGLISH);
            int whites = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isWhitespace(ch)) {
                    whites++;
                    if (whites == 1) {
                        sb.append(ch);
                    }
                } else if (Character.isLetter(ch)) {
                    whites = 0;
                    sb.append(ch);
                } else if (Character.isDigit(ch)) {
                    whites = 0;
                    sb.append(ch);
                } else if (!Character.isWhitespace(ch)) {
                    whites++;
                    if (whites == 1) {
                        sb.append(" ");
                    }
                }
            }
            return stripAccents(sb.toString());
        }
        return null;
    }

    private static final String UNICODE = "ÀàÈèÌìÒòÙùÁáÉéÍíÓóÚúÝýÂâÊêÎîÔôÛûŶŷÃãÕõÑñÄäËëÏïÖöÜüŸÿÅåÇçŐőŰű";
    private static final String PLAIN_ASCII = "AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu";

    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);
            int pos = UNICODE.indexOf(c);
            if (pos > -1)
                sb.append(PLAIN_ASCII.charAt(pos));
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String md5(String str) {
        return md5(str.getBytes());
    }

    public static String md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    public static String b64(byte[] bytes) {
        try {
            return new String(Base64.encodeBase64(bytes));
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }
}
