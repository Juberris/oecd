package com.example.maven_jaxb_plugin.util;
import com.example.maven_jaxb_plugin.model.CrsOecd;
import com.example.maven_jaxb_plugin.model.schema.wrapper.oecd.ties.cfw.v1.CTSFileWrapperOECD;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class FieldPathUtil {
    // Utiliza un conjunto para rastrear clases ya procesadas y evitar ciclos
    private static final Set<Class<?>> processedClasses = new HashSet<>();

    public static String getFieldPath(Class<?> clazz, String fieldName) {
        StringBuilder path = new StringBuilder();
        if (buildFieldPath(clazz, fieldName, path)) {
            // Si encontramos la propiedad, agregamos la raíz al principio
            if (clazz.isAnnotationPresent(XmlRootElement.class)) {
                XmlRootElement rootElement = clazz.getAnnotation(XmlRootElement.class);
                path.insert(0, "/" + rootElement.name());
            }
        }
        return path.toString();
    }

    private static boolean buildFieldPath(Class<?> clazz, String fieldName, StringBuilder path) {
        if (clazz == null || processedClasses.contains(clazz)) {
            return false;
        }

        processedClasses.add(clazz);

        try {
            boolean found = false;

            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().isEnum()) {
                    // Maneja enums, que no necesitan paths XML
                    continue;
                }

                String fieldNameXml = field.getName();
                if (field.isAnnotationPresent(XmlElement.class)) {
                    XmlElement element = field.getAnnotation(XmlElement.class);
                    fieldNameXml = element.name().equals("##default") ? field.getName() : element.name();
                } else if (field.isAnnotationPresent(XmlElementWrapper.class)) {
                    XmlElementWrapper wrapper = field.getAnnotation(XmlElementWrapper.class);
                    fieldNameXml = wrapper.name().equals("##default") ? field.getName() : wrapper.name();
                }

                int initialLength = path.length();
                path.append("/").append(fieldNameXml);

                if (field.getType().equals(List.class)) {
                    Class<?> genericType = (Class<?>) ((java.lang.reflect.ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    if (buildFieldPath(genericType, fieldName, path)) {
                        found = true;
                    }
                } else {
                    if (fieldName.equalsIgnoreCase(field.getName())) {
                        found = true;
                    } else {
                        if (buildFieldPath(field.getType(), fieldName, path)) {
                            found = true;
                        }
                    }
                }

                if (found) {
                    return true;
                }

                path.setLength(initialLength); // Reset path to the previous state
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            processedClasses.remove(clazz); // Asegúrate de remover la clase después del procesamiento
        }
        return false;
    }

    private static void buildAllFieldPaths(Class<?> clazz, StringBuilder path, List<String> paths) {
        if (clazz == null || processedClasses.contains(clazz)) {
            return;
        }

        processedClasses.add(clazz);

        try {
            if (clazz.isAnnotationPresent(XmlRootElement.class)) {
                XmlRootElement rootElement = clazz.getAnnotation(XmlRootElement.class);
                path.append("/").append(rootElement.name());
            }

            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().isEnum()) {
                    // Maneja enums, que no necesitan paths XML
                    continue;
                }

                String fieldNameXml = field.getName();
                if (field.isAnnotationPresent(XmlElement.class)) {
                    XmlElement element = field.getAnnotation(XmlElement.class);
                    fieldNameXml = element.name().equals("##default") ? field.getName() : element.name();
                } else if (field.isAnnotationPresent(XmlElementWrapper.class)) {
                    XmlElementWrapper wrapper = field.getAnnotation(XmlElementWrapper.class);
                    fieldNameXml = wrapper.name().equals("##default") ? field.getName() : wrapper.name();
                }

                int initialLength = path.length();
                path.append("/").append(fieldNameXml);

                if (field.getType().equals(List.class)) {
                    Class<?> genericType = (Class<?>) ((java.lang.reflect.ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    buildAllFieldPaths(genericType, path, paths);
                } else {
                    paths.add(path.toString());
                    buildAllFieldPaths(field.getType(), path, paths);
                }

                path.setLength(initialLength); // Reset path to the previous state
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            processedClasses.remove(clazz); // Asegúrate de remover la clase después del procesamiento
        }
    }

    public static List<String> getAllFieldPaths(Class<?> clazz) {
        List<String> paths = new ArrayList<>();
        processedClasses.clear(); // Limpia el conjunto antes de empezar
        StringBuilder path = new StringBuilder();
        buildAllFieldPaths(clazz, path, paths);
        return paths;
    }

    private static String convertGetterToProperty(String methodName) {
        if (methodName.isEmpty()) {
            return null;
        }

        // Convierte la primera letra en minúscula para obtener el nombre de la propiedad
        StringBuilder propertyName = new StringBuilder(methodName);
        propertyName.setCharAt(0, Character.toLowerCase(propertyName.charAt(0)));

        return propertyName.toString();
    }
    public static String getPropertyNameFromGetter(String methodName) {
        if (methodName == null || methodName.isEmpty()) {
            return null;
        }

        // Verifica si el método es un getter
        if (methodName.startsWith("get")) {
            return convertGetterToProperty(methodName.substring(3));
        } else if (methodName.startsWith("is")) {
            return convertGetterToProperty(methodName.substring(2));
        }

        return null;
    }
    public static void main(String[] args) {
        /*List<String> paths = getAllFieldPaths(CTSFileWrapperOECD.class);
        for (String path : paths) {
            System.out.println("FieldPath XML: " + path);
        }*/
        CTSFileWrapperOECD oecdWrap=new CTSFileWrapperOECD();
        String fieldPath = getFieldPath(CTSFileWrapperOECD.class, "fileName");
        System.out.println("FieldPath XML: " + fieldPath);
    }
}


