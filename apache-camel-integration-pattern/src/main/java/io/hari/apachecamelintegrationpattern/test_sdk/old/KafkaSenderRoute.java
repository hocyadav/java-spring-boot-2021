package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 * 1 docker install steps : 3min -> https://youtu.be/LtooWDUL1Js
 * 2 kafka install 2min using docker compose -> see docker-compose yml file for zookeeper and kafka ->  $docker-compose up
 * 3 : add dependency for kafka connector
 * 3.a add camel-kafka application properties for apache camel (where kafka is runing url+port): camel.component.kafka.brokers=localhost:9092
 */
//@Component
public class KafkaSenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input").routeId("kafka-sender-route-1")
                .log("${body}")
                .to("kafka:my-camel-topic");
    }
}
