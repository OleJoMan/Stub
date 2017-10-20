import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Date;

public class TestProd {
    public static void main(String[] args) throws InterruptedException {
        String id = IDGenerator.create(20);
        String value = new Date().toString();
        Msg msg = new Msg(id, value);
        MsgProducer msgProducer = new MsgProducer("msg", msg);
        msgProducer.sendMsg();

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        check(id);
    }

    private static void check(String id) throws InterruptedException {
        MsgConsumer check = new MsgConsumer("check");
//        for (ConsumerRecord<String, byte[]> record : check.getRecords()) {
//            Msg checkMsg = Serializator.deserialize(record.value());
//            System.out.println("Entered ID: " + id + "received ID: " + checkMsg.getId() + " " + checkMsg.getValue());
//        }
        for (ConsumerRecord<String, byte[]> record : check.getRecords()) {
            Msg msg = Serializator.deserialize(record.value());
            String resId = msg.getId();
            String value = msg.getValue();
            System.out.println(resId + " " + value);
        }

    }
}

