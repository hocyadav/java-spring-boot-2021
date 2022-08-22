package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

public class PipelineIntoDifferentLayer2 {
    public static void main(String[] args) {
        Mono<String> mono =
                //converted pipeline into different layer
                MyAPI.getFromAPI();

        //this subscriber we can add as part of API layer
        mono.subscribe(
                item -> System.out.println("item = " + item),
                errm -> System.out.println("errm.getMessage() = " + errm.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    class MyAPI {//API layer method
        public static Mono<String> getFromAPI() {
            return MyService.getFromService()
                    .map(input -> input.toUpperCase())
                    .map(input -> input.toUpperCase().concat(" - new string added "));
        }
    }

    class MyService {//service layer method
        public static Mono<String> getFromService() {
            return MyDomain.getFromDAO()
                    .map(input -> input.toString());
        }
    }

    class MyDomain {//Entity/Domain layer method/business logic
        public static Mono<Integer> getFromDAO() {
            return Mono.just(1);
        }
    }
}
//EXTRA : every class have there own exception, so that by seeing exception we can easily know from which layer error is coming.
