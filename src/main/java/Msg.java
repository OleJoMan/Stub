import java.io.Serializable;
import java.util.Date;

public class Msg implements Serializable {
    private static String id;
    private static String value;

    public Msg(String id, String value) {
        this.id = id;
        this.value = value;
    }
    public Msg() {
        this.id = IDGenerator.create(20);
        this.value = new Date().toString();
    }

    public static String getId() {
        return id;
    }

    public static String getValue() {
        return value;
    }
}


