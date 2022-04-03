package io.hari.javareactiveframework.core_concept.simpletest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.stream.Collectors;

public class TestB {
// approach
//mono -> mono(list integer) -> mono(list Tuple type) -> flux(Tuple) -> do operation -> flux(obj to mono using collect) -> mono(list) -> mono(list response to one obj) -> return one obj
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3);
        //start : we have mono type
        Mono<List<Integer>> listMono = Mono.just(integerList);
        Mono<List<Tuple2<String, Integer>>> listTupleMono = listMono.map(list -> {
            List<Tuple2<String, Integer>> collect = list.stream()
                    .map(integer -> {
                        return Tuples.of("get from integer obj value : done", integer);
                    }).collect(Collectors.toList());
            return collect;
        });
        Flux<Tuple2<String, Integer>> tuple2Flux1 = listTupleMono.flatMapMany(tuple2s -> {
            List<Tuple2<String, Integer>> tuple2s1 = tuple2s;
            Flux<Tuple2<String, Integer>> tuple2Flux = Flux.fromIterable(tuple2s1);

            return tuple2Flux;
//            return Mono.just(tuple2s1);
        });


        Flux<String> stringFlux = tuple2Flux1.flatMap(objects -> {
            Tuple2<String, Integer> objects1 = objects;
            //do operation , call mono fun
            return Mono.just("");
        });

        Mono<List<String>> listMono1 = stringFlux.collectList();
        Mono<String> stringMono = listMono1.flatMap(strings -> {
            List<String> strings1 = strings;
            //collect list into one object + return mono.just(sinle obj that conatins all list response)
            return Mono.just("final all response list in one obj type");
        });

        //end : now we have mono type

    }
}
