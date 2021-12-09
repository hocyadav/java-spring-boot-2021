package io.hari.javareactiveframework.core_concept.operator;

import lombok.SneakyThrows;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * flatmap == async (final output order may change)
 * 1. convert t1 to t2
 * 2. wrap t2 to input type
 * 3. final return type will be mono.mono.t2 in case of mono, flatmap will cancel out one mono, so final output will be mono.t2
 *
 * NOTE. final return type will be same as input, mono -> mono, flux -> flux, only inside object type will be changed
 *
 * flatmap == concatmap, both are async but concat map will preserver order
 *
 */
public class FlatMapOperator {
    @SneakyThrows
    public static void main(String[] args) {


        Mono<String> mono = Mono.just("hariom");//T1 = string , wrap inside mono

        Mono<Integer> map = mono.flatMap(i -> {
            int length = i.length();//1. t1-> t2
            return Mono.just(length);//2. wrap over mono (this step is not present in map())
        });
        //1. map convert T1->T2, 2. that T2 output will be mono
        //1. flat map convert T1->T2, 1.5 wrap over mono, 2. that T2 output will be mono so final mono.mono.t2 and, flat map will cancel one mono

        Flux<String> flux = Flux.just("hari", "om");//T1 = string

        Flux<Integer> map1 = flux
                .flatMap(i -> {
                    int length = i.length();//1. mapping operation [NOTE here conversion one t1 to t2 is single object]
                    return Flux.just(length);//2. wrap over flux since input is flux type
                });//output will be flux.flux.t2 , but flatmap will cancel out one flux


        map.subscribe(item -> System.out.println("mono item = " + item));
        map1.subscribe(item2 -> System.err.println("flux item2 = " + item2));

        Thread.sleep(100);
        System.out.println("-------------");

        Flux<String> flux2 = Flux.just("hari", "om");//T1 = string
        Flux<String> map3 = flux2.flatMap(i -> {
            String[] arrayOrList = i.split("");//[NOTE here conversion one t1 to t2 is multi object]
            return Flux.just(arrayOrList);
        });

        map3.subscribe(item3 -> System.err.println("flux item3 = " + item3));

        Thread.sleep(100);
        System.out.println("-------------");

        Mono<String> mono1 = Mono.just("hari");
        Mono<List<String>> map2 = mono1.flatMap(i -> {
            List<String> list = Arrays.stream(i.split("")).toList();//1. mapping operation,
            return Mono.just(list);//2. wrap t2 to mono, since input is mono type
        });//output will be mono.mono.t2, flatmap will cancel out one mono, and final type will be mono.t2

        map2.subscribe(item4 -> System.out.println("mono item4 = " + item4));

    }
}
