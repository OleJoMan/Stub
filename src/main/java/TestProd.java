import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Date;

public class TestProd {
    public static void main(String[] args) throws InterruptedException {
        String id = IDGenerator.create(20);
        String value = new Date().toString();
        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg(id, value);
            MsgProducer msgProducer = new MsgProducer("msg");
            msgProducer.start();
            msgProducer.sendMsg(msg);
            check(id);
        }
    }
    private static void check(String id){
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MsgConsumer check = new MsgConsumer("check");
                    check.start();
                    String resId = "";
                    String value;
                    while(resId.length() == 0)
                    for (ConsumerRecord<String, byte[]> record : check.consumer.poll(1)) {
                        Msg msg = Serializator.deserialize(record.value());
                        resId = msg.getId();
                        value = msg.getValue();
                        System.out.println("Received msg's: " + resId + " " + value);
                    }
                }
            }).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
