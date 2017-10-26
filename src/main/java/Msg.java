import java.io.Serializable;
import java.util.Date;

public class Msg implements Serializable {
    private String id;
    private String value;

    public Msg(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Msg() {
    }

    public String getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }
}


