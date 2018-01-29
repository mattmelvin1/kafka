
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class kafkaConsumer {

    public static void main (String[] args){
        Properties props =  new Properties();
        //server
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("key.deserializer", StringDeserializer.class.getName());
        props.setProperty("value.deserializer", StringDeserializer.class.getName());
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer= new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("my-first-topic"));

        while(true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords){

                System.out.println(
                        "Partition: " + consumerRecord.partition() +
                        "    key: " + consumerRecord.key() +
                        "    value: " + consumerRecord.value());


            }
        }

    }
}
