package io.hari.javareactiveframework.core_concept.operator.concat;

import reactor.core.publisher.Flux;

public class ConcatWith_operator4 {
    public static void main(String[] args) {
        //https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#concatWith-org.reactivestreams.Publisher-
        Flux<String> input1 = Flux.just("a", "b", "c");
        Flux<String> input2 = Flux.error(new RuntimeException("oops"));
        Flux<String> input3 = Flux.just("d", "e", "f");

//        Flux<String> input3 = input1.concatWith(input2);//1 + 2 + 3
//        Flux<String> input4 = Flux.concat(input1, input2, input3);//1 + 2 + 3 , after input2 not execute input3
        Flux<String> input4 = Flux.concatDelayError(input1, input2, input3);//1 + 2 + 3 , after input2 execute input3

        input4.subscribe(data -> System.out.println("data = " + data),
                error -> System.out.println("error = " + error.getMessage()),
                () -> System.out.println("completed")
        );//4


    }
}
