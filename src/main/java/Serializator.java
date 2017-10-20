import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

public class Serializator {
    private static Kryo KRYO = new Kryo();
    public static byte[] serialize(Msg object) {
        ByteArrayOutputStream objStream = new ByteArrayOutputStream();
        Output objOutput = new Output(objStream);
        KRYO.writeClassAndObject(objOutput, object);
        objOutput.close();
        return objStream.toByteArray();
    }

    public static Msg deserialize(byte[] data) {
        return (Msg) KRYO.readClassAndObject(new Input(data));
    }
}
