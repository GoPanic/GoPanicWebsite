package models;

import play.mvc.Scope;

/**
 * @author Michael Ruf
 * @since 2015-11-28
 */
public class Panic {

    public static String getPanicHash(Scope.Session session) {
        return session.getId();
    }

    public static long getPanicTime() {
        return System.currentTimeMillis();
    }

    private final String id;
    private long time;

    public Panic(String id, long time) {
        this.id = id;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
