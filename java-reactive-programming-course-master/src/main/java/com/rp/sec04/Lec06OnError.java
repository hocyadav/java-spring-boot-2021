package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
//                .onErrorReturn(-1)//hardcoded value fallback : its like defaultIfEmpty
//                 .onErrorResume(e -> fallback())//fallback Publisher: its like switchIfEmpty
                //above 2 operator will stop pipeline if error happen, onErrorContinue will continue other data
                .onErrorContinue((err, obj) -> {//even in case of error continue

                })
                .subscribe(Util.subscriber());


    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }

}
