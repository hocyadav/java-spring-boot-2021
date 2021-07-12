package io.hari.javareactiveframework.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GreetingHandler {//step 1: create webflux handler : https://spring.io/guides/gs/reactive-rest-service/

    public Mono<ServerResponse> hello(ServerRequest request) {
        log.info("printing trace id and span id ");
        Mono<ServerResponse> responseMono = ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring! Hariom"));
        return responseMono;
    }
}
