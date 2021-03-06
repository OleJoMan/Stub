import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TestCons {
    public static void main(String[] args) throws InterruptedException {
        MsgConsumer msgConsumer = new MsgConsumer("msg");
        msgConsumer.start();
        String id;
        String value;
        try (FileWriter fileWriter = new FileWriter("msg.csv", true)) {
            while (true) {
                for (ConsumerRecord<String, byte[]> record : msgConsumer.consumer.poll(1)) {
                    Msg msg = Serializator.deserialize(record.value());
                    id = msg.getId();
                    value = msg.getValue();
                    System.out.println(id + " " + value);
                    sendResponse(id);
                    fileWriter.write(id + ";" + value + System.lineSeparator());
                    fileWriter.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void sendResponse(String id) {
        System.out.println("send ID: " + id);
        Msg respMsg = new Msg(id, new Date().toString());
        MsgProducer respProd = new MsgProducer("check");
        respProd.start();
        respProd.sendMsg(respMsg);
    }
}
