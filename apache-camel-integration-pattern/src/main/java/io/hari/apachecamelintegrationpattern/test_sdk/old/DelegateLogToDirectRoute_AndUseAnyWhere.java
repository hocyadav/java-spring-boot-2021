package io.hari.apachecamelintegrationpattern.test_sdk.old;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Hariom Yadav
 * @since 19/06/21
 *
 * org.apache.camel.FailedToStartRouteException: Failed to start route route2 because of Multiple consumers for the same endpoint is not allowed: file://files/input
 */
//@Component
public class DelegateLogToDirectRoute_AndUseAnyWhere extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        simpleRoute(); // comment : if we run all route with same endpoints then not allowed

        from("file:files/input").routeId("delegate-route-1")
                .to("direct:common-route")
                .to("file:files/output");

        from("direct:common-route")//common direct route we can use anywhere, we can add here QUEUE 
                .log("body of file : ${body} size : ${file:size}")
                .log("name : ${file:name}")
                .log("name : ${file:absolute.path}")
                .log("name : ${file:size}")
                .log("name : ${file:modified}");
    }

    private void simpleRoute() {
        from("file:files/input").routeId("SIMPLE_ROUTE")
                .log("body of file : ${body} size : ${file:size}")
                .log("name : ${file:name}")
                .log("name : ${file:onlyname}")
                .log("name : ${file:size}")
                .log("name : ${file:modified}")
                .to("file:files/output");
    }
}
