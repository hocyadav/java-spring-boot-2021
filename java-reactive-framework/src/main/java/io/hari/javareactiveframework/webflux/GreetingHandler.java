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
//                .body(BodyInserters.fromValue(getStringMono("dummy_key")));//not working
        return responseMono;
    }

    private String getStringMono(String key) {
        Mono<String> r = Mono
                .deferContextual(ctx -> {
                    //OPTIONAL : get context map and print all KV
                    ctx.stream().forEach(objectObjectEntry -> {
                        System.out.println("key1 = " + objectObjectEntry.getKey());
                        System.out.println("value = " + objectObjectEntry.getValue());
                    });
                    return Mono.just("Hello " + ctx.get(key));
                })//3rd finally it will get the 2nd update
                .contextWrite(ctx -> ctx.put(key, "Reactor"))//2nd update, so this is the latest update for that key and new immutable context instance created
                .contextWrite(ctx -> ctx.put(key, "World"));//1st update happen , after subscription
        return r.block();
    }
}
