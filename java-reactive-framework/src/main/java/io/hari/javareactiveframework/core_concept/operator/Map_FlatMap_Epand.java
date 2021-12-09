package io.hari.javareactiveframework.core_concept.operator;

import lombok.SneakyThrows;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
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
public class Map_FlatMap_Epand {
    @SneakyThrows
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("hari", "om");//T1 = string

        flux.map(i -> i.length());//1. mapper t1 -> t2
        //same as above, see internal
        flux.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String type1) {
                int type2 = type1.length();//1. mapper t1 -> t2
                return type2;
            }
        });

        flux.flatMap(i -> {
            String upperCase = i.toUpperCase();//1. mapper t1 -> t2
            return Flux.just(upperCase);//2. return input type as flux
        });

        flux.flatMap(new Function<String, Publisher<String>>() {
            @Override
            public Publisher<String> apply(String s) {
                String upperCase = s.toUpperCase();//1. mapper t1 -> t2
                //m1
                Flux<String> publisherFlux = Flux.just(upperCase);//2. return input type as flux, and flux is more generic its a Publisher
                return publisherFlux;
                //m2: we can store Flux into Publisher and return
//                Publisher<String> stringPublisher = publisherFlux;//same as above see internal flux class
//                return stringPublisher;
            }
        });
    }
}
