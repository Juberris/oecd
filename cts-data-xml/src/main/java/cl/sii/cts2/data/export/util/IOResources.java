package cl.sii.cts2.data.export.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
public class IOResources implements Closeable {

    private Set<InputStream> inputStreams = new LinkedHashSet();
    private Set<Reader> readers = new LinkedHashSet();
    private Set<OutputStream> outputStreams = new LinkedHashSet();
    private Set<File> deletes = new LinkedHashSet();

    public IOResources() {
    }

    public File add(File f) {
        if (f == null) {
            return null;
        } else {
            this.deletes.add(f);
            return f;
        }
    }

    public <T extends InputStream> T add(T t) {
        if (t == null) {
            return null;
        } else {
            this.inputStreams.add(t);
            return t;
        }
    }

    public <T extends OutputStream> T add(T t) {
        if (t == null) {
            return null;
        } else {
            this.outputStreams.add(t);
            return t;
        }
    }

    public Reader add(Reader r) {
        if (r == null) {
            return null;
        } else {
            this.readers.add(r);
            return r;
        }
    }

    public void close() {
        Iterator var1 = this.inputStreams.iterator();

        while(var1.hasNext()) {
            InputStream r = (InputStream)var1.next();
            if (r != null) {
                try {
                    r.close();
                } catch (Exception var6) {
                }
            }
        }

        var1 = this.readers.iterator();

        while(var1.hasNext()) {
            Reader r = (Reader)var1.next();
            if (r != null) {
                try {
                    r.close();
                } catch (Exception var5) {
                }
            }
        }

        var1 = this.outputStreams.iterator();

        while(var1.hasNext()) {
            OutputStream r = (OutputStream)var1.next();
            if (r != null) {
                try {
                    r.close();
                } catch (Exception var4) {
                }
            }
        }

        var1 = this.deletes.iterator();

        while(var1.hasNext()) {
            File file = (File)var1.next();
            if (file.exists()) {
                boolean deleted = file.delete();
                if (log.isTraceEnabled()) {
                    log.trace(file + " deleted: " + deleted);
                }
            }
        }

    }

    public static InputStream toInputStream(String in) {
        return in != null ? new ByteArrayInputStream(in.getBytes()) : null;
    }
}

