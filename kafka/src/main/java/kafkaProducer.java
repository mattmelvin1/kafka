
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class kafkaProducer {

    public static void main (String[] args){
        Properties props =  new Properties();
        //server
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", StringSerializer.class.getName());
        //producer acks
        props.setProperty("acks", "1");
        //retries
        props.setProperty("retires", "3");
        //linger time
        props.setProperty("linger.ms", "1");
        //producer
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0;  i < 10; i++) {
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<String, String>("my-first-topic", String.valueOf(i), "Message with id " + i);
            producer.send(producerRecord);
        }
        producer.close();
    }
}
