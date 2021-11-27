package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

public class PipelineIntoDifferentLayer02 {
    public static void main(String[] args) {
//16 lec
        Mono<String> mono =
                //pipeline : start any HL into LL like this , and then break down into different methods, then different class
                        //domain layer
                getApiLayer();//pipeline will stop if come here

        mono.subscribe(
                item -> System.out.println("item = " + item),
                errm -> System.out.println("errm.getMessage() = " + errm.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    private static Mono<String> getApiLayer() {
        return getService2()
                .map(input -> input.toUpperCase())
                .map(input -> input.toUpperCase().concat(" - new string added "))
                .switchIfEmpty(Mono.error(new RuntimeException("api oops")));
    }

    private static Mono<String> getService2() {
        return getServiceLayer()//pipeline will stop if come here
                //api layer
                .map(integer -> integer)//validation : move as a one method including switch if empty
                .switchIfEmpty(Mono.error(new RuntimeException("validation oops")));
    }

    private static Mono<String> getServiceLayer() {
        return getDomain_oops()//pipeline will stop if come here
                //service layer
                .map(integer -> integer)//validation
                .switchIfEmpty(Mono.error(new RuntimeException("validation oops")))
                .map(input1 -> input1.toString())//business logic
                .switchIfEmpty(Mono.error(new RuntimeException("service oops")));
    }

    private static Mono<Integer> getDomain_oops() {
        return Mono.just(1)
                .switchIfEmpty(Mono.error(new RuntimeException("domain oops")));
    }

}
