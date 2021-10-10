package io.hari.javareactiveframework.core_concept.operator;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class Assignment2 {
    @SneakyThrows
    public static void main(String[] args) {
        //month stream
        Flux<Integer> month = Flux.range(1, 12).delayElements(Duration.ofSeconds(1));

        //demand stream
        Flux<Integer> demand = Flux.range(1, 12).delayElements(Duration.ofSeconds(3))
                .map(integer -> {
                    return new Random().nextInt(10);
                });
        Flux<Integer> demand2 = demand.startWith(1);

        //combine both stream
        Integer carPrice = 10000;
        Flux<Integer> flux = Flux.combineLatest(month, demand2, (m, d) -> {
            System.out.println("m = " + m);
            int decrement = m * 100;
            return (carPrice - decrement) * d;
        });

        flux.subscribe(data -> System.out.println("data = " + data));
        Thread.sleep(60000);
    }
}
