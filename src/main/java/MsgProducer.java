import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class MsgProducer extends Thread{
    private String topicName ;
    private Producer<String, byte[]> producer ;

    public MsgProducer(String topicName) {
        this.topicName = topicName;
        Properties props = new Properties(){{
            put("bootstrap.servers", "localhost:9092");
            put("acks", "all");
            put("retries", 1);
            put("linger.ms", 10);
            put("key.serializer", org.apache.kafka.common.serialization.StringSerializer.class.getName());
            put("value.serializer", org.apache.kafka.common.serialization.ByteArraySerializer.class.getName());
        }};
        producer = new KafkaProducer<String,byte[]>(props);
    }

    @Override
    public void run() {

    }

    public void sendMsg(Msg msg){
        producer.send(new ProducerRecord<String, byte[]>(topicName, Serializator.serialize(msg)));
    }
}
