package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07FluxInterval {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .log()
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }

}
