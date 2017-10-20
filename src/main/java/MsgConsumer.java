import com.esotericsoftware.kryo.Kryo;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class MsgConsumer implements Runnable{
    private String topicName;
    KafkaConsumer<String, byte[]> consumer;

    public MsgConsumer(final String topicName) {
        this.topicName = topicName;
        Properties props = new Properties() {{
            put("bootstrap.servers", "localhost:9092");
            put("group.id", topicName);
            put("enable.auto.commit", true);
            put("auto.offset.reset", "earliest");
            put("auto.commit.interval.ms", 1000);
            put("session.timeout.ms", 30000);
            put("key.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class.getName());
            put("value.deserializer", org.apache.kafka.common.serialization.ByteArrayDeserializer.class.getName());
        }};
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));
    }

    public ConsumerRecords<String, byte[]> getRecords() {
        ConsumerRecords<String, byte[]> records = consumer.poll(1);
        return records;
    }

    @Override
    public void run() {

    }
}
