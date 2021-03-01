package com.example.demojava.kafka;

import java.util.Properties;
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
public class KafkaProducer01WithCallbackForLoop {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // record object
        for (int i = 0; i < 10; i++) {// msg will go to any partition round robin (coz we have not mention key)
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "hello from java " + Integer.valueOf(i));
            kafkaProducer
                    .send(producerRecord, new Callback() {// callback used to get info about data that is sent on kafka, using recordMetadata object
                        @Override
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            //execute every time when record is sebt or an exception
                            if (e == null) { //successfully sent
                                log.info("Received new metadata \n" + "Topic: " + recordMetadata.topic() + "\n" + "Partition: " + recordMetadata
                                        .partition() + "\n" + "Offset: " + recordMetadata.offset() + "\n" + "Timestamp: " + recordMetadata
                                        .timestamp());
                            } else { // not successfully sent
                                log.error("Error while producing", e);
                            }
                        }
                    });//this is async, run in background
        }

        kafkaProducer.flush();//make async to flush all data
        kafkaProducer.close();//optional
    }
}
