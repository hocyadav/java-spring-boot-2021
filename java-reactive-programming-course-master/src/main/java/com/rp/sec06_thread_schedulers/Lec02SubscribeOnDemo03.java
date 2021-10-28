package com.rp.sec06_thread_schedulers;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo03 {

    public static void main(String[] args) {
        //some operator run during pipeline creation: e.g. doFirst, subscribeOn
        //some operator run during pipeline execution: e.g. doOnNext, subscribe, Flux.create

        Flux<Object> flux = Flux.create(fluxSink -> {//4. execute in below Scheduler-vins thread
            printThreadName("create");
            fluxSink.next(1);
        })
         .subscribeOn(Schedulers.newParallel("vins"))//3 then we are changing thread
        .doOnNext(i -> printThreadName("next " + i));//this will run after 4, so it will also execute in Scheduler-vins thread


      Runnable runnable = () ->  flux
               .doFirst(() -> printThreadName("first2"))//2 - execute in below scheduler thread : (this will run during pipeline creation)
               .subscribeOn(Schedulers.boundedElastic())//during pipeline creation we are switching thread , that's why 2 is running in this thread
               .doFirst(() -> printThreadName("first1"))//1 - execute in main thread : (this will run during pipeline creation)
               .subscribe(v -> printThreadName("output " + v));//this is running in the vins thread, (running during pipeline execution not during pipeline creation)

        for (int i = 0; i < 1; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);
    }

    private static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
