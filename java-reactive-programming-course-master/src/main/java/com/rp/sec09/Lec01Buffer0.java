package com.rp.sec09;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer0 {

    public static void main(String[] args) {
        //input Flux<Type> --> buffer(~group/list) --> output Flux<List<Type>>

        //use case 1 : collect 3 item           - buffer(size)
        //use case 2 : collect item in 2 sec    - buffer(time)
        //use case 3 : case 1 or case 2         - bufferTimeout(size or time)

        eventStream()
                .buffer(3)//case 1 : collect 3 item
//                .buffer(Duration.ofSeconds(2))//case 2 : collect item in 2 sec
//                .bufferTimeout(3, Duration.ofSeconds(2))//case 3 : case 1 or case 2
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(800))
                    .map(i -> "event"+i);
    }


}
