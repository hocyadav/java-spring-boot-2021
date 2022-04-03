package io.hari.javareactiveframework.core_concept.simpletest;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import io.vavr.Tuple;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestA {
    public static void main(String[] args) {
        Mono<List<Integer>> mono = Mono.just(List.of(1, 2, 3, 4));

        Mono<Integer> mono1 = mono.flatMap(list -> {
            List<Integer> list1 = list;
            return Mono.just(1);
        });


        Mono<List<Integer>> listMono = Mono.just(List.of(1, 2, 3));
        Flux<Integer> integerFlux = listMono.flatMapMany(list -> {
            return Flux.fromIterable(list);
        });
        Flux<Mono<Integer>> map = integerFlux.map(integer -> {
            //call operation for each obj
            return Mono.just(integer);
        });


        Mono<Map<String, List<Integer>>> monoMap = Mono.just(Map.of("key", List.of(1, 2)));

        Mono<Mono<String>> map1 = monoMap.map(stringListMap -> {
            Map<String, List<Integer>> stringListMap1 = stringListMap;
            return Mono.just("");
        });

        Flux<Integer> flux = Flux.fromIterable(List.of(12, 34));

        Mono<Mono<List<Tuple2<Integer, String>>>> monoMono = monoMap.map(stringListMap -> {
            Map<String, List<Integer>> stringListMap1 = stringListMap;

            List<Integer> list = List.of(1, 2, 3);

            Flux<Tuple2<Integer, String>> map2 = flux.map(f -> Tuples.of(f, "single value from list - using mono.flatmap "));
            Mono<List<Tuple2<Integer, String>>> listMono1 = map2.collectList();

//            return Tuples.of("", "");
            return listMono1;
        });


        Mono<List<Tuple2<Integer, String>>> monoMono2 = monoMap.flatMap(stringListMap -> {
            Map<String, List<Integer>> stringListMap1 = stringListMap;

            List<Integer> list = List.of(1, 2, 3);

            Flux<Tuple2<Integer, String>> map2 = flux.map(f -> Tuples.of(f, "single value from list - using mono.flatmap "));
            Mono<List<Tuple2<Integer, String>>> listMono1 = map2.collectList();

//            return Tuples.of("", "");
            return listMono1;
        });
        Mono<String> map2 = monoMap.flatMap(stringListMap -> {
            Map<String, List<Integer>> stringListMap1 = stringListMap;
            return Mono.just("");
        });



//        monoMap.flatMapMany(stringListMap -> {
//            Map<String, List<Integer>> stringListMap1 = stringListMap;
//            Set<Map.Entry<String, List<Integer>>> entries = stringListMap1.entrySet();
//            stringListMap1.entrySet().stream()
//                    .collect(Collectors.groupingBy(
//                            z -> z.getKey();
//                    ))
//
//            return Flux.fromIterable(entries);
//        });


        Mono<List<Integer>> just = Mono.just(List.of(1, 2, 3));
        Flux<List<Integer>> listFlux = just.flatMapMany(list -> {
            return Flux.just(list);
        });
        Flux<String> stringFlux = listFlux.flatMap(list -> {
            //t1 to t2
            //call mono method //do operation
            return Mono.just("");
        });


    }
}
