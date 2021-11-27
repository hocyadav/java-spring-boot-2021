package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        //publisher pipeline lik stream, add other operators : filter, map, flatmap...etc
        Mono<Integer> mono = Mono.just(1);//type T1 /this is also called handler
        Mono<Integer> monoE = Mono.error(new RuntimeException("oops"));//type Throwable /this is also called error handler

        Consumer<Integer> consumerByProducer = integer -> {
            Integer integer1 = integer;
        };

        mono.subscribe(consumerByProducer);//consumer<T1> or consumer<Throwable> or Runnable for complete signal : consumer means take some input and process it or print and it will not return anything
        //many overloaded subscribe method in Mono class


        mono
                .doOnNext(integer -> System.out.println("consumer do on next"))
                .doOnSuccess(integer -> System.out.println("on success "))
                .subscribe(
                item -> System.out.println("item = " + item),//handler for upstream data : consumer : take upstream data and do something and return nothing coz no downstream to take , else we can use mapper/map in upstream that uses Function
                err -> System.out.println("err = " + err),//if we not write this then no error handler then whole exception will be printed out in console
                () -> System.out.println("completed")//handler for complete signal
        );

    }
}
