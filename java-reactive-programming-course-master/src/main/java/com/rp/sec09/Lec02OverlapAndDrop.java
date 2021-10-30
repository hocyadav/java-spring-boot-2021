package com.rp.sec09;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02OverlapAndDrop {

    public static void main(String[] args) {
        //use case 1: check for some patterns in events (required windows size data, so subsequence group need to know info about each other)
        //use case 2: sampling of events (only required few events/data)

        //use case 1: small skip value : size > skip
        //use case 2: large skip value : size < skip
        //NOTE : size == skip ===> buffer(size)

        eventStream()
                .buffer(3, 5)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event"+i);
    }
}
