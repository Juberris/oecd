package cl.sii.crs2.sara.export.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Service
@Slf4j
public class Cts2ExportScript {
    private static final Set<String> INVALID_CHARS = new HashSet<>();
    static {
        INVALID_CHARS.add("&#");
        INVALID_CHARS.add("--");
        INVALID_CHARS.add("/**");
    }

    public static void modify(Writer w, XmlMod mod, Consumer<Xml.XmlReaderListener> c) {
        Xml.XmlWriter xmlw = new Xml.XmlWriter(w);
        Xml.XmlReaderListener l = new Xml.XmlReaderListener() {
            @Override
            public void elementBgn(String prefix, String fullpath, Map<String, String> attrs) {
                if (mod.include(fullpath)) {
                    mod.bgn(fullpath);
                    String[] path = fullpath.split("/");
                    boolean noprefix = Strings.isEmpty(prefix);
                    if (namespaces.size() > 0) {
                        xmlw.bgn((noprefix ? "" : prefix + ":") + path[path.length - 1], namespaces, mod.attrs(fullpath, attrs));
                        namespaces.clear();
                    } else {
                        xmlw.bgn((noprefix ? "" : prefix + ":") + path[path.length - 1], mod.attrs(fullpath, attrs));
                    }
                }
            }

            @Override
            public void elementValue(String fullpath, String value) {
                if (mod.include(fullpath)) {
                    value = value.trim();
                    value = mod.value(fullpath, value);
                    value = clean(value);
                    xmlw.value(value);
                }
            }

            @Override
            public void elementEnd(String prefix, String fullpath) {
                if (mod.include(fullpath)) {
                    String[] path = fullpath.split("/");
                    xmlw.end(prefix + ":" + path[path.length - 1]);
                    mod.end(fullpath);
                }
            }

            @Override
            public void error(String msg, Throwable t) {
                throw new RuntimeException(msg, t);
            }

            @Override
            public void end() {
                xmlw.end();
                super.end();
            }
        };
        c.accept(l);
    }

    public static String clean(String value) {
        Set<String> chars = invalidCharacters(value);
        if (chars != null) {
            for (String ch : chars) {
                value = value.replace(ch, "");
            }
        }
        return value;
    }
    public static Set<String> invalidCharacters(String value) {
        Set<String> set = null;
        for (String chars : INVALID_CHARS) {
            if (value.contains(chars)) {
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(chars);
            }
        }
        return set;
    }

    public static class XmlMod {

        public void bgn(String path) {
        }

        public Map<String, String> attrs(String path, Map<String, String> attrs) {
            if (attrs != null) {
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                for (String key : attrs.keySet()) {
                    map.put(key, value(path + "/" + key, attrs.get(key)));
                }
                return map;
            }
            return attrs;
        }

        public String value(String path, String value) {
            return value;
        }

        public void end(String path) {

        }

        public boolean include(String path) {
            return true;
        }


    }
}
