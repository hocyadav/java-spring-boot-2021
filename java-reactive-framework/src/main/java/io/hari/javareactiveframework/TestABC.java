package io.hari.javareactiveframework;

import io.vavr.Tuple;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class TestABC {
    public static void main(String[] args) {

        Mono<String> test = Mono.just("test");

        Mono<Tuple2<String, Integer>> tuple2Mono = test.zipWith(Mono.just(1));

        Mono<Tuple2<String, Tuple2<String, Integer>>> zip = Mono.zip(test, tuple2Mono);

        Mono<Tuple2<String, Integer>> zip1 = Mono.zip(test, Mono.just(123));


        Mono<Integer> integerMono = test.flatMap(s -> {
            return Mono.just(122);
        });

        Mono<Tuple2<String, Integer>> test13 = test.flatMap(s -> {
            return Mono.zip(Mono.just("test13"), Mono.just(123));
        });


        //start mono -> add 2 mono do some processing -> map operator return same mono
        Mono<Tuple2<String, String>> tuple2Mono1 = test.flatMap(s -> {
            return Mono.zip(Mono.just("hariom").map(s1 -> s1.toUpperCase()), Mono.just("key"));
        });
        Mono<Tuple2<String, String>> map = tuple2Mono1.map(objects -> {
            Tuple2<String, String> objects1 = objects;
            String t1 = objects1.getT1();
            String t2 = objects1.getT2();
            return objects;
        });

    }
}
