package cl.sii.crs2.sara.export.util;

public enum Ids {
    INSTANCE;
    private long last = 0;

    public synchronized long id() {
        long id = System.currentTimeMillis();
        while ((id = System.currentTimeMillis()) == last) {
        }
        last = id;
        return id;
    }
}
