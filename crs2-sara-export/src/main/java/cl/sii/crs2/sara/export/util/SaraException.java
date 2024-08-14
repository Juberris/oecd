package cl.sii.crs2.sara.export.util;

public class SaraException extends RuntimeException {

    public SaraException() {
        super();
    }
    public SaraException(Throwable e) {
        super(e);
    }

    public static Throwable source(Throwable e) {
        Throwable t = e;
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t;
    }
}
