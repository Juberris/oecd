package cl.sii.crs2.sara.export.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.DefaultPrettyPrinter;



public class SaraSerDe {

    public static final ObjectMapper JSON_SERDE = new ObjectMapper(new JsonFactory() {
        @Override
        protected JsonGenerator _createJsonGenerator(Writer out, IOContext ctxt) throws IOException {
            return super._createJsonGenerator(out, ctxt).setPrettyPrinter(new DefaultPrettyPrinter() {
                {
                    _arrayIndenter = new Lf2SpacesIndenter();
                }
            });
        }

    });
    public static final ObjectMapper JSON_SERDE_NULLS = new ObjectMapper(new JsonFactory() {
        @Override
        protected JsonGenerator _createJsonGenerator(Writer out, IOContext ctxt) throws IOException {
            return super._createJsonGenerator(out, ctxt).setPrettyPrinter(new DefaultPrettyPrinter() {
                {
                    _arrayIndenter = new Lf2SpacesIndenter();
                }
            });
        }

    });

    static {
        JSON_SERDE.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        JSON_SERDE.setSerializationInclusion(Inclusion.NON_NULL);
        JSON_SERDE_NULLS.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String toJson(Object object) {
        try {
            return toJson(object, false);
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static String toJson(Object object, boolean nulls) {
        try {
            return nulls ? JSON_SERDE_NULLS.writerWithDefaultPrettyPrinter().writeValueAsString(object) : JSON_SERDE.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static void toJson(File file, Object object) {
        try {
            JSON_SERDE.writerWithDefaultPrettyPrinter().writeValue(file, object);
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static Map<String, Object> fromJson(String json) {
        try {
            if (json != null) {
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
                };
                return JSON_SERDE.readValue(json, typeRef);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            if (json != null) {
                return JSON_SERDE.readValue(json, clazz);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) {
        try {
            if (json != null) {
                return JSON_SERDE.readValue(json, clazz);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static <T> T fromJson(File json, Class<T> clazz) {
        try {
            if (json != null) {
                return JSON_SERDE.readValue(json, clazz);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static <T> T fromJson(File json, TypeReference<T> clazz) {
        try {
            if (json != null) {
                return JSON_SERDE.readValue(json, clazz);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static Map<String, Object> fromJson(File json) {
        try {
            if (json != null && json.exists()) {
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
                };
                return JSON_SERDE.readValue(json, typeRef);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static <T> Map<String, T> fromJsonMap(File json, Class<T> clazz) {
        try {
            if (json != null && json.exists()) {
                TypeReference<HashMap<String, T>> typeRef = new TypeReference<HashMap<String, T>>() {
                };
                return JSON_SERDE.readValue(json, typeRef);
            }
            return null;
        } catch (Exception e) {
            throw new SaraException(e);
        }
    }

    public static String toString(byte[] bytes) {
        return bytes != null ? encodeB64(bytes) : null;
    }

    public static String toString(Object object) {
        if (object != null) {
            if (isPrimitive(object.getClass())) {
                return object.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("<" + object.getClass().getSimpleName() + ">\n");
                toString(sb, object, 1);
                sb.append("</" + object.getClass().getSimpleName() + ">\n");
                return sb.toString();
            }
        }
        return null;
    }

    public static void toString(StringBuilder sb, Object object, int level) {
        if (object != null) {
            Class<?> clazz = object.getClass();
            if (isPrimitive(clazz)) {
                sb.append(indent(level) + object + "\n");
                return;
            }
            Field[] fields = clazz.getDeclaredFields();
            while (fields != null) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (!Modifier.isStatic(field.getModifiers())) {
                        try {
                            String fieldName = field.getName();
                            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Class<?> fieldType = field.getType();
                            Object fieldValue = field.get(object);
                            if (List.class.isAssignableFrom(fieldType)) {
                                if (fieldValue != null) {
                                    List<?> list = (List<?>) fieldValue;
                                    for (Object listObject : list) {
                                        sb.append(indent(level + 1) + "<" + fieldName + ">\n");
                                        toString(sb, listObject, level + 2);
                                        sb.append(indent(level + 1) + "</" + fieldName + ">\n");
                                    }
                                }
                            } else if (fieldType.isArray()) {
                                if (fieldValue != null) {
                                    int length = Array.getLength(fieldValue);
                                    for (int i = 0; i < length; i++) {
                                        sb.append(indent(level + 1) + "<" + fieldName + ">\n");
                                        Object arrayValue = Array.get(fieldValue, i);
                                        toString(sb, arrayValue, level + 2);
                                        sb.append(indent(level + 1) + "</" + fieldName + ">\n");
                                    }
                                }
                            } else {
                                try {
                                    if (fieldValue == null || isPrimitive(fieldType) || fieldType.isEnum() || !fieldType.getPackage().getName().startsWith("cl.sii")) {
                                        sb.append(indent(level) + "<" + fieldName + ">" + fieldValue + "</" + fieldName + ">\n");
                                    } else {
                                        sb.append(indent(level) + "<" + fieldName + ">\n");
                                        toString(sb, fieldValue, level + 1);
                                        sb.append(indent(level) + "</" + fieldName + ">\n");
                                    }
                                } catch (Exception e) {
                                    System.out.println("fieldValue: " + fieldValue + ", fieldType: " + fieldType);
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                Class<?> clazzParent = clazz.getSuperclass();
                if (clazzParent != null) {
                    fields = clazzParent.getDeclaredFields();
                    clazz = clazzParent;
                } else {
                    fields = null;
                }
            }
        }
    }

    public static boolean isPrimitive(Class<?> clazz) {
        return clazz == boolean.class || clazz == Boolean.class || //
                clazz == byte.class || clazz == Byte.class || //
                clazz == char.class || clazz == Character.class || //
                clazz == short.class || clazz == Short.class || //
                clazz == int.class || clazz == Integer.class || //
                clazz == long.class || clazz == Long.class || //
                clazz == float.class || clazz == Float.class || //
                clazz == double.class || clazz == Double.class || //
                clazz == String.class;
    }

    public static String indent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }

    public static String encodeB64(byte[] data) {
        char[] tbl = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

        StringBuilder buffer = new StringBuilder();
        int pad = 0;
        for (int i = 0; i < data.length; i += 3) {

            int b = ((data[i] & 0xFF) << 16) & 0xFFFFFF;
            if (i + 1 < data.length) {
                b |= (data[i + 1] & 0xFF) << 8;
            } else {
                pad++;
            }
            if (i + 2 < data.length) {
                b |= (data[i + 2] & 0xFF);
            } else {
                pad++;
            }

            for (int j = 0; j < 4 - pad; j++) {
                int c = (b & 0xFC0000) >> 18;
                buffer.append(tbl[c]);
                b <<= 6;
            }
        }
        for (int j = 0; j < pad; j++) {
            buffer.append("=");
        }

        return buffer.toString();
    }

    public static byte[] decodeB64(String data) {
        int[] tbl = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1 };
        byte[] bytes = data.getBytes();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length;) {
            int b = 0;
            if (tbl[bytes[i]] != -1) {
                b = (tbl[bytes[i]] & 0xFF) << 18;
            }
            // skip unknown characters
            else {
                i++;
                continue;
            }

            int num = 0;
            if (i + 1 < bytes.length && tbl[bytes[i + 1]] != -1) {
                b = b | ((tbl[bytes[i + 1]] & 0xFF) << 12);
                num++;
            }
            if (i + 2 < bytes.length && tbl[bytes[i + 2]] != -1) {
                b = b | ((tbl[bytes[i + 2]] & 0xFF) << 6);
                num++;
            }
            if (i + 3 < bytes.length && tbl[bytes[i + 3]] != -1) {
                b = b | (tbl[bytes[i + 3]] & 0xFF);
                num++;
            }

            while (num > 0) {
                int c = (b & 0xFF0000) >> 16;
                buffer.write((char) c);
                b <<= 8;
                num--;
            }
            i += 4;
        }
        return buffer.toByteArray();
    }
}

