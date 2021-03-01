package com.example.demojava.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HariomYadav
 * @since 29/10/20
 */
@Slf4j
public class KafkaConsumer01 {
    public static void main(String[] args) {
        // property
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "fourth_group");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("first_topic"));

        // record
        while (true) {
            final ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(10000));
            consumerRecords.forEach(consumerRecord -> {
                System.err.println(
                        (" .key() = " + consumerRecord.key()) + (" .value() = " + consumerRecord.value()) + (" .offset() = " + consumerRecord
                                .offset()) + (" .partition() = " + consumerRecord.partition()));
            });
        }
    }
}
