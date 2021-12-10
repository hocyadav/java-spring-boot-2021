package io.hari.javareactiveframework.core_concept.operator;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class TimeOperator {

    @SneakyThrows
    public static void main(String[] args) {

        Flux.just(1,2,3,4,5,6,7,8,9,9,9,10).delayElements(Duration.ofMillis(200))//get process data and delay and then send to downstream
//                .take(Duration.ofSeconds(5))//from up only take data till 2 sec
//                .take(4).log("take")//from up only take 4 out of all data
//                .limitRate(2).log("limit rate")//make small small req to upstream
//                .repeat(2)//repeat whole pipeline 2 times
//                .buffer(3)
                .buffer(Duration.ofSeconds(1))
                .doOnNext(list -> {
                    List<Integer> list1 = list;
                })
                .subscribe(i -> System.out.println("i = " + i));

        Thread.sleep(50000);
    }
}
