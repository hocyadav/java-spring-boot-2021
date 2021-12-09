package io.hari.javareactiveframework.core_concept.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * map
 * 1. t1 to t2 for each input element
 * NOTE. final return type will be same as input, mono -> mono, flux -> flux, only inside object type will be changed
 */
public class MapOperator {
    public static void main(String[] args) {

        Mono<String> mono = Mono.just("hariom");//T1 = string , wrap inside mono
        Mono<Integer> map = mono.map(i -> i.length());//1. map convert T1->T2, 2. that T2 output will be mono

        Flux<String> flux = Flux.just("hari", "om");//T1 = string
        Flux<Integer> map1 = flux
                .map(i -> i.length());//t1 -> t2 for each data, on every onNext call this map() will be called

        //no diff map() of flux and mono, same thing we will get, in flux we will get for multiple values

        map.subscribe(item -> System.out.println("mono item = " + item));

        map1.subscribe(item2 -> System.err.println("flux items = " + item2));

        //todo use case :  in log line LLD we can read all log line as one value in flux,
        //and map each log line to generic class interface, log type: info, warn, error
    }
}
