package io.hari.javareactiveframework.core_concept.operator;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class FilterOperator {
    public static void main(String[] args) {

        Flux.fromIterable(List.of(1,2,3,4,5,6)).doOnNext(s -> System.out.println("doOnNext 0 = " + s))
                .map(i -> i.toString().concat("-str")).doOnNext(s -> System.out.println("doOnNext 1 = " + s))
                .filter(i -> i.contains(String.valueOf(1))).doOnNext(s -> System.out.println("doOnNext 2 = " + s))
                .subscribe(i -> System.out.println("i = " + i));


        //1. all layer is publisher (if we extract as var then it will be Flux/Mono type), so we can attach hooks/callback like do on next
        Flux.fromIterable(List.of( 1,2,3,4,5,6))
                .map(i -> i.toString().concat("-str")).doOnNext(s -> System.out.println("doOnNext 1 = " + s))
                .filter(i -> i.contains(String.valueOf(1)))
                .subscribe(i -> System.out.println("i = " + i));

        //added callback for every publisher
        Flux.fromIterable(List.of(1,2,3,4,5,6)).doOnNext(s -> System.out.println("doOnNext 0 = " + s))
                .map(i -> i.toString().concat("-str")).doOnNext(s -> System.out.println("doOnNext 1 = " + s))
                .filter(i -> i.contains(String.valueOf(1))).doOnNext(s -> System.out.println("doOnNext 2 = " + s))
                .subscribe(i -> System.out.println("i = " + i));

        //(if we extract as var then it will be Flux/Mono type)
        Flux<Integer> flux = Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6)).doOnNext(s -> System.out.println("doOnNext 0 = " + s));//doOnNext callback don't change the input type
        Flux<String> stringFlux = flux.map(i -> i.toString().concat("-str")).doOnNext(s -> System.out.println("doOnNext 1 = " + s));
        Flux<String> stringFlux1 = stringFlux.filter(i -> i.contains(String.valueOf(1))).doOnNext(s -> System.out.println("doOnNext 2 = " + s));
        stringFlux1.subscribe(i -> System.out.println("i = " + i));

        Function<? super Mono<String>, ? extends Publisher<? extends Object>> t1 = new Function<Mono<String>, Publisher<? extends Object>>() {
            @Override
            public Publisher<? extends Object> apply(Mono<String> stringMono) {
                return null;
            }
        };






    }
}
