package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 *
 * read topic events/msg : https://kafka.apache.org/quickstart
 * $ bin/kafka-console-consumer.sh --topic my-camel-topic --from-beginning --bootstrap-server localhost:9092
 */
//@Component
public class KafkaConsumerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {//todo: add host name in : sudo vim /private/etc/hosts
        from("kafka:my-camel-topic").routeId("kafka-consumer-route-1")
                .log("${body}")
                .to("log:my-camel-topic-log");
    }
}
