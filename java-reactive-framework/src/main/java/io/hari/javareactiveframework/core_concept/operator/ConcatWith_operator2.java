package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;

public class ConcatWith_operator2 {
    public static void main(String[] args) {
        Flux<String> input1 = Flux.just("a", "b", "c");
        Flux<String> input2 = Flux.just("d", "e", "f");

//        Flux<String> input3 = input1.concatWith(input2);//1 + 2 + 3
        Flux<String> input3 = Flux.concat(input1, input2);//1 + 2 + 3 //using static method/factory method

        input3.subscribe(data -> System.out.println("data = " + data));//4


    }
}
