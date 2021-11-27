package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

public class PipelineIntoDifferentLayer1 {
    public static void main(String[] args) {

        Mono<String> mono =
                //converted pipeline into different layer
                getFromAPI();

        //this subscriber we can add as part of API layer
        mono.subscribe(
                item -> System.out.println("item = " + item),
                errm -> System.out.println("errm.getMessage() = " + errm.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    private static Mono<String> getFromAPI() {//API layer method
        return getFromService()
                .map(input -> input.toUpperCase())
                .map(input -> input.toUpperCase().concat(" - new string added "));
    }

    private static Mono<String> getFromService() {//service layer method
        return getFromDAO()
                .map(input -> input.toString());
    }

    private static Mono<Integer> getFromDAO() {//Entity/Domain layer method/business logic
        return Mono.just(1);
    }
}
