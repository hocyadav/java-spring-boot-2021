package io.hari.javareactiveframework.core_concept.operator;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

import java.time.Duration;

public class Assignment {
    @SneakyThrows
    public static void main(String[] args) {

        Flux<Integer> month = Flux.range(1, 12).delayElements(Duration.ofSeconds(1));

        Flux<Integer> price = Flux.range(1, 10)
                .map(integer -> {
                    return 10000;
                });
        Flux<Integer> demand = Flux.range(1, 12).delayElements(Duration.ofSeconds(3))
                .map(integer -> {
                    return 2;
                });
        Flux<Integer> demand2 = demand.startWith(1);

        Flux<String> flux = Flux.zip(month, price, demand2)
                .map(tuple3 -> {
                    Integer m = tuple3.getT1();
                    Integer p = tuple3.getT2();
                    Integer d = tuple3.getT3();
                    int finalPrice = p * d;
                    return "Month : " + m + " Price : " + finalPrice;
                });

        flux.subscribe(data -> System.out.println("data = " + data));


        Thread.sleep(60000);


    }
}
