package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo02 {

    public static void main(String[] args) {
        //same as Lec02SubscribeOnDemo01, only difference is starting of pipeline is not done by main but by runnable/newThread : machine coding tips

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
        .doOnNext(i -> printThreadName("next " + i));


      Runnable runnable = () -> flux
               .doFirst(() -> printThreadName("first2"))//4. run in new scheduler thread, and above all pipeline will be run in this new thread
               .subscribeOn(Schedulers.boundedElastic())//3. switch to new scheduler thread
               .doFirst(() -> printThreadName("first1"))//2. run in main thread
               .subscribe(v -> printThreadName("output " + v));//1. started as main thread (here main thread is current thread i.e. thread-0)

        new Thread(runnable).start();

        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
/*
old output : Lec02SubscribeOnDemo01
first1		: Thread : main
first2		: Thread : boundedElastic-1
create		: Thread : boundedElastic-1
next 1		: Thread : boundedElastic-1
sub 1		: Thread : boundedElastic-1

current output : Lec02SubscribeOnDemo02
first1		: Thread : Thread-0
first2		: Thread : boundedElastic-1
create		: Thread : boundedElastic-1
next 1		: Thread : boundedElastic-1
sub 1		: Thread : boundedElastic-1

 */
