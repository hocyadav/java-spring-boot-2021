package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo01 {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
        .doOnNext(i -> printThreadName("next " + i));


      flux
               .doFirst(() -> printThreadName("first2"))//4. run in new scheduler thread, and above all pipeline will be run in this new thread
               .subscribeOn(Schedulers.boundedElastic())//3. switch to new scheduler thread
               .doFirst(() -> printThreadName("first1"))//2. run in main thread
               .subscribe(v -> printThreadName("sub " + v));//1. started as main thread

        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
/*

first1		: Thread : main
first2		: Thread : boundedElastic-1
create		: Thread : boundedElastic-1
next 1		: Thread : boundedElastic-1
sub 1		: Thread : boundedElastic-1

 */
