package io.hari.apachecamelintegrationpattern;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 */
@Component
public class KafkaSenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .log("${body}")
                .to("kafka:my-camel-topic");
    }
}
