package com.rp.sec10_repeat_retry;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03RetryWhen {

    public static void main(String[] args) {

        getIntegers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))//same as previous but we added delay of 3 sec
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
        //todo: explore other Retry.backOff() and its other methods
    }

    private static Flux<Integer> getIntegers(){
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error"));
    }

}
