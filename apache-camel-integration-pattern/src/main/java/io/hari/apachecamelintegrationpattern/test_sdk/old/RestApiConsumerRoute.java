package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Controller;

/**
 * @author Hariom Yadav
 * @since 19/06/21
 * error : Cannot find RestProducerFactory in Registry or as a Component to use
 * add dependency
 */
//@Controller
public class RestApiConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().setHost("https://reqres.in");

        route1();
        route2withHeader();
    }

    private void route2withHeader() {
        from("timer:my-rest-api?period=10000")//every 10 sec
                .log("${body}")
                .setHeader("user_id", () -> 3)
                .to("rest:get:/api/users/{user_id}")
                .log("${body}");
    }

    private void route1() {
        from("timer:my-rest-api?period=10000")//every 10 sec
                .log("${body}")
                .to("rest:get:/api/users/2")
                .log("${body}");
    }
}
