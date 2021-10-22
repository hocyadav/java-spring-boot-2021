package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01ThreadDemo {

    public static void main(String[] args) {
        //here everything is done by our new Thread
        //we have make our subscriber to run in different thread,
        // that means it also make its pipeline to run in that thread,
        // its not like its above pipeline will run in main thread and subscriber will be in new Thread

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));


       Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }
        Util.sleepSeconds(5);

        //todo : we can pass own Executor service and pass different different subscriber , so that they will use our thread pool thread to run pipeline
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
/**
 * create		: Thread : Thread-1
 * create		: Thread : Thread-0
 * next 1		: Thread : Thread-0
 * next 1		: Thread : Thread-1
 * sub 1		: Thread : Thread-0
 * sub 1		: Thread : Thread-1
 */
