package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CollectList_operator {
    public static void main(String[] args) {
        Flux<String> input1 = Flux.just("a", "b", "c", "z", "a");

        Flux<String> input2 = input1//1
                .sort();//2 operator

        input2//3
                .subscribe(s -> System.out.println("s = " + s));//4


        //useful when we want to convert flux -> list , since "flux" is "list" only
        Mono<List<String>> input3 = input2//1
                .collectList();//2
        input3//3
                .subscribe(strings -> System.out.println("data as list = " + strings));
    }
}
