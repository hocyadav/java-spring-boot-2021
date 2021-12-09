package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

public class FluxReduce {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3);

        Mono<Integer> mono = flux.reduce(100, getBiFunction());//reduce input flux , output mono
        mono.subscribe(i -> System.out.println("i = " + i));


        //same as above , but to add initial is good practice
        Flux.just(1, 2, 3)
                .reduce(getBiFunction())
                .subscribe(j -> System.out.println("j = " + j));

    }

    //Bi Function t1, t2 -> t3, here 2 input , (Function ; t1 -> t2 here one input)
    private static BiFunction<Integer, Integer, Integer> getBiFunction() {
        return (initialValue, upstreamValue) -> initialValue + upstreamValue;//100 + 1, 1 + 2, 3 + 3, first time initial value and upstream
    }
}
/*
i = 106
j = 6
 */

