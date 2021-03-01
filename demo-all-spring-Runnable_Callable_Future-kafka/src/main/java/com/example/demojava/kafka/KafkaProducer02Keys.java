package com.example.demojava.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
public class KafkaProducer02Keys {
    public static void main(String[] args) {
        final Properties properties = getProperties();
        final KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //add key before value, same key makes msg to send to same partition
        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "key01","hello from java");
        kafkaProducer.send(producerRecord);//this is async, run in background
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
