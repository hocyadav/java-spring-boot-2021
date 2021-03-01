package com.example.demojava.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
@Slf4j
public class KafkaProducer02KeysWithCallback {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Properties properties = getProperties();
        final KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //add key before value
        for (int i = 0; i < 10; i++) {
            final String key = "key_id_"+Integer.valueOf(i); // same key will always go to same partition , it will not change its partition
            System.out.println("key = " + key);
            final ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", key, "hello from java " + Integer.valueOf(i));
            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("Received new metadata \n" +
                                "Topic: " + recordMetadata.topic() + "\n" +
                                "Partition: " + recordMetadata.partition() + "\n" +
                                "Offset: " + recordMetadata.offset() + "\n" +
                                "Timestamp: " + recordMetadata.timestamp());
                    } else {
                        log.error("Error while producing", e);
                    }
                }
            }).get();//this is async, by calling .get makes sync
        }
        kafkaProducer.flush();//make async to flush all data
        kafkaProducer.close();//optional
    }

    private static Properties getProperties() {
        final Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
