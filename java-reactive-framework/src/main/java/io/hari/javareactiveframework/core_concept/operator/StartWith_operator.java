package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;

public class StartWith_operator {
    public static void main(String[] args) {
        Flux<String> input2 = Flux.just("a", "b", "c");
        Flux<String> input1 = Flux.just("d", "e");//cache data

        Flux<String> input3 = input2//1
                .startWith(input1);//2 : startwith --> 1st execute/subscribe/print/complete input1 and then input2

        input3//3
                .subscribe(s -> System.out.println("data = " + s));//4
    }
}
