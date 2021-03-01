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
public class KafkaProducer01 {
    public static void main(String[] args) {
        // producer property
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");//aws ec2 -> install kafka and connect
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // producer object
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        // record object
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "hello from java");
        //send data
        kafkaProducer.send(producerRecord);//this is async, run in background
        kafkaProducer.flush();//make async to flush all data
        kafkaProducer.close();//optional
    }
}
