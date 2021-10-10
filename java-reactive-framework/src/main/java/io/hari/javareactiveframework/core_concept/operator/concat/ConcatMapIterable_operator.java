package io.hari.javareactiveframework.core_concept.operator.concat;

import reactor.core.publisher.Flux;

import java.util.List;

public class ConcatMapIterable_operator {
    public static void main(String[] args) {
        Flux<String> input1 = Flux.just("a", "b", "c");//1

        //concatMapIterable / mapper with iterable / mapper with list /Function t1 -> t2 : "a" --> 1,2,3
        Flux<Integer> integerFlux = input1//1
                //2. operator
                .concatMapIterable(type1 -> {//type1 is simple , "a"
                    List<Integer> type2 = List.of(1, 2, 3);
                    return type2;//type2 is list of data for "a"
                });

        integerFlux//3
                .subscribe(integer -> System.out.println("integer = " + integer));//4

    }
}
