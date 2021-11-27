package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

public class PipelineIntoDifferentLayer0 {
    public static void main(String[] args) {
//16 lec
        Mono<String> mono =
                //pipeline : start any HL into LL like this , and then break down into different methods, then different class
                        //domain layer
                        Mono.just(1)
                        //service layer
                        .map(input1 -> input1.toString())
                        //api layer
                        .map(input -> input.toUpperCase())
                        .map(input -> input.toUpperCase().concat(" - new string added "));

        mono.subscribe(
                item -> System.out.println("item = " + item),
                errm -> System.out.println("errm.getMessage() = " + errm.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

}
