package cl.sii.filepackaging.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
public class OS {
    private static final String[] FORMATS = new String[]{"yyyyMMdd'T'HHmmssSSS'Z'", "yyyyMMdd'T'HHmmss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd", "yyyy"};
    public static int execute(File dir, File cmd, String... args) throws Exception {
        return execute(dir, path(cmd), args);

    }

    public static int execute(File dir, String cmd, String... args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("cmd.exe");
        list.add("/c");
        list.add(cmd);
        if (args != null) {
            list.add(String.join(" ", args));
        }
        ProcessBuilder builder = new ProcessBuilder(list.toArray(new String[] {}));
        builder.directory(dir.getAbsoluteFile());
        builder.redirectErrorStream(true);
        Process process = builder.start();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new StreamGobbler(process.getInputStream(), System.out::println));
        executorService.shutdown();
        return process.waitFor();
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }

    public static String path(File path) {
        try {
            return path.getCanonicalPath();
        } catch (Exception e) {
            throw new RuntimeException(path.toString(), e);
        }
    }

    public static void delete(File dir) {
        delete(dir, false);
    }

    public static void delete(File dir, boolean leaveEmpty) {
        if (dir.exists() && dir.isDirectory()) {

            try {
                Files.walk(dir.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            } catch (IOException e) {
               log.error("Error al borrar elemento :: {}",dir);
            }
            if (leaveEmpty) {
                    dir.mkdirs();
                }

        }

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

    public static XMLGregorianCalendar date2UtcXmlDate(String date) {
        return date2UtcXmlDate(utc2Date(date));
    }

    public static XMLGregorianCalendar date2UtcXmlDate(Date date) {
        if (date != null) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                XMLGregorianCalendar xmlcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFormat.format(date));
                xmlcal.setTimezone(0);
                return xmlcal;
            } catch (Exception var4) {
                throw new RuntimeException("Error convirtiendo date [" + date + "] a xml date", var4);
            }
        } else {
            return null;
        }
    }
    public static XMLGregorianCalendar yearXmlDate(Integer year) {
        if (year == null) {
            return null;
        } else {
            new GregorianCalendar();

            try {
                XMLGregorianCalendar xmlcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(String.valueOf(year));
                return xmlcal;
            } catch (Exception var3) {
                throw new RuntimeException("Error convirtiendo date [" + year + "] a xml date", var3);
            }
        }
    }
    public static Date utc2Date(String utc) {
        if (utc == null) {
            return null;
        } else {
            Exception ex = null;
            String[] var2 = FORMATS;
            int var3 = var2.length;
            int var4 = 0;

            while(var4 < var3) {
                String format = var2[var4];
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                try {
                    Date date = sdf.parse(utc);
                    return date;
                } catch (Exception var8) {
                    ex = var8;
                    ++var4;
                }
            }

        }
        return null;
    }

    public static File tmp(String prefix) {
        File directory = new File(System.getProperty("java.io.tmpdir"), prefix);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            return new File(directory, prefix + "_" + Ids.uuid());
        } catch (Exception var3) {
            log.error("Error creando archivo temporal {}", var3);
        }
        return null;
    }

    public static void tempFileCleaner(String prefix){
        String tempDirPath = System.getProperty("java.io.tmpdir");
        File tempDir = new File(tempDirPath);
        if (tempDir.exists() && tempDir.isDirectory()) {
            File[] tempFiles = tempDir.listFiles();
            if (tempFiles != null) {
                for (File tempFile : tempFiles) {
                    // Check if the file starts with the specified prefix
                    if (tempFile.isFile() && tempFile.getName().startsWith(prefix)) {
                        // Delete the file
                        boolean isDeleted = tempFile.delete();
                        if (isDeleted) {
                            System.out.println("Deleted: " + tempFile.getName());
                        } else {
                            System.out.println("Failed to delete: " + tempFile.getName());
                        }
                    }
                }
            } else {
                log.error("Failed to list files in the temporary directory.");
            }
        }else {
            log.error("Temporary directory does not exist or is not a directory.");
        }
    }
}
