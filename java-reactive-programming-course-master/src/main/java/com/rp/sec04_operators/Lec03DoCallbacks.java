package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {
        //one thing common in all doOn operator is that they return same type here Flux<T> , its like plug and play in pipeline

        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
           // fluxSink.complete();
            fluxSink.error(new RuntimeException("oops"));
            System.out.println("--completed");
        })

                //most of callback takes consumer as input or Runnable type argument
        .doOnComplete(() -> System.out.println("doOnComplete"))
                //when we create pipeline : (P)<----f3--f2--f1----(S) , So bottom to up (bottom to up during pipeline creating time)
        .doFirst(() -> System.out.println("doFirst"))
        .doOnNext(o -> System.out.println("doOnNext : " + o))
                //when we execute pipeline: (P)----s1--s2--s3---->(S) , So up to down (up to down possible when pipeline created, i.e. execution time)
        .doOnSubscribe(s -> System.out.println("doOnSubscribe" + s))
        .doOnRequest(l -> System.out.println("doOnRequest : " + l))
        .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))
        .doOnTerminate(() -> System.out.println("doOnTerminate"))
        .doOnCancel(() -> System.out.println("doOnCancel"))
        .doFinally(signal -> System.out.println("doFinally 1 : " + signal))
        .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
        .take(2)
                .doFinally(signal -> System.out.println("doFinally 2 : " + signal))
        .subscribe(Util.subscriber());

        //IMP : we have 2 case : operator use during pipeline CREATION or during pipeline EXECUTION

    }

}
