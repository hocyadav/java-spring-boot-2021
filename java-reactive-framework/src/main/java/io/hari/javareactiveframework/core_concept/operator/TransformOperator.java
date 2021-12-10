package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class TransformOperator {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8)
                .transform(integerFlux -> {//1. p1 -> p2 (publisher conversion)
                    Flux<Integer> inputP1 = integerFlux;
                    Flux<String> outputP2 = inputP1.map(integer -> integer.toString().concat("-STR"));
                    Mono<List<String>> monop2 = outputP2.collectList();
                    return monop2;
                })
                .subscribe(i -> System.out.println("k = " + i));
        System.out.println("---------------------------");

        Flux.fromIterable(List.of("1", "2", "3", "4", "5", "6"))
                .map(str -> Integer.valueOf(str))
                .take(Duration.ofSeconds(2))
                .filter(integer -> integer % 2 == 0)//if we want to do map + filter operator we can use transform
                .map(integer -> integer.toString())
                .subscribe(item -> System.out.println("item = " + item));
        //use case : transform() clean/filter input n convert to another type, validate input and convert to another type

        //this is same as above: in transform we can move pipeline logic
        Flux.fromIterable(List.of("1", "2", "3", "4", "5", "6"))
                .transform(getPublisherP1ToP2Function())
                .subscribe(item -> System.out.println("item = " + item));


        //Mono to Flux conversion
        Mono<List<String>> mono = Mono.just("hariom")
                .flatMap(s -> Mono.just(List.of(s.split(""))));//1. t1 to t2 or do some operation or internal type conversion, 2. wrap over same type

        Flux<String> flux = Mono.just("hariom")
                .flatMapMany(s -> Flux.just(s.split("")));//1. t1 to t2, 2. wrap over Flux since we have multi value

        //same as above mono
        Mono<List<String>> mono1 = Mono.just("hariom")
                .transform(stringMono -> stringMono.flatMap(s -> Mono.just(List.of(s.split("")))));//1. P1 to p2 + do some pipeline operation
        System.out.println("----------------");
        Mono.just("hariom")
                .transform(stringMono -> {
                    Flux<String> stringFlux = stringMono.flatMapMany(s -> Flux.just(s.split("")));
                    return stringFlux;
                })
                .subscribe(item -> System.out.println("item = " + item));

    }

    private static Function<Flux<String>, Flux<String>> getPublisherP1ToP2Function() {
        return flux -> {
            Flux<String> inputP1 = flux;//1. input Publisher
            Flux<String> flux1 = inputP1//2. do some pipeline operation
                    .map(str -> Integer.valueOf(str))
                    .filter(integer -> integer % 2 == 0)
                    .map(integer -> integer.toString());
            return flux1;//3. output Publisher
        };
    }
}
