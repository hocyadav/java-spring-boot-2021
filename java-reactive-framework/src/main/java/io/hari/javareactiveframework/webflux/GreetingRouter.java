package io.hari.javareactiveframework.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter { // step 2: create router : https://spring.io/guides/gs/reactive-rest-service/
    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RouterFunction<ServerResponse> route = RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), greetingHandler::hello);
        return route;
    }
}
