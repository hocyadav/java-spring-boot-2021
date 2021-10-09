package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Count_operator {
    public static void main(String[] args) {
        //https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#concatWith-org.reactivestreams.Publisher-
        //search count operator

        //in every pic we have 3 things
        //1. input / input publisher
        //2. operator
        //3. output / output publisher
        //4. subscribe 3 (i.e. 1+2+3)
        Flux<String> upStreamInDiagram_Or_publisher = Flux.just("a", "b", "c");

        //m1
//        upStreamInDiagram_Or_publisher//input stream line
//                .count()//operator
//                .subscribe(s -> System.out.println("data = " + s));//output + output subscribe


        //m2 : same as above in
        Mono<Long> downStreamInDiagram_or_publisher2 =  //3. final output
                upStreamInDiagram_Or_publisher          //1. input stream line
                .count();                               //2. operator

        downStreamInDiagram_or_publisher2//1 + 2 + 3
                .subscribe(s -> System.out.println("data = " + s));//4. print output + output subscribe

    }
}
