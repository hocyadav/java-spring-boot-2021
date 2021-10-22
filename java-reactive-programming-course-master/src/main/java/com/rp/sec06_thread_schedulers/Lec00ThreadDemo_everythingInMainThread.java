package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec00ThreadDemo_everythingInMainThread {

    public static void main(String[] args) {
        //proof: all pipeline will run in main thread
        //create a method that will print thread name for running task
        //pass that method everywhere

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));

        flux.subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5);
    }


    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }


}
/**
 * create		: Thread : main
 * next 1		: Thread : main
 * sub 1		: Thread : main
 */
