import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TestProd {
    public static void main(String[] args) throws InterruptedException {
        String id;
        String value;
        for (int i = 0; i < 10; i++) {
            id = IDGenerator.create(20);
            value = new Date().toString();
            Msg msg = new Msg(id, value);
            MsgProducer msgProducer = new MsgProducer("msg");
            try {
                System.out.println("Send:" + System.lineSeparator() + id + " " + value);
                msgProducer.sendMsg(msg);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Thread.sleep(3000);
            System.out.println("Check" + check(id));
        }
    }

    private static boolean check(String id) {
        try (BufferedReader respReader = new BufferedReader(new InputStreamReader(new FileInputStream("resp")))) {
            String line = respReader.readLine();
            String[] result = line.split(":");
            System.out.println("respID: " + result[0] + " respStatus: " + result[1]);
            return id.equals(result[0]) && result[1].equals("OK") ? true : false;
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("error");
        return false;
    }
}