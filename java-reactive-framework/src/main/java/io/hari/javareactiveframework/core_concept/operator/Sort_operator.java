package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;

public class Sort_operator {
    public static void main(String[] args) {
        Flux<String> input1 = Flux.just("a", "b", "c", "z", "a");

        Flux<String> input2 = input1//1
                .sort();//2 operator

        input2//3
                .subscribe(s -> System.out.println("s = " + s));//4
    }
}
