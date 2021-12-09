package io.hari.javareactiveframework.core_concept.operator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * https://dzone.com/articles/notes-on-reactive-programming-part-ii
 */
public class FluxSubscribeAndSubscriber {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3)
//                .log()
                .take(1);
        flux.subscribe(i -> System.out.println("i = " + i));


        //this is similar to above one
        Flux<Integer> flux1 = Flux.just(10, 20, 30).log();
        flux1.subscribe(new Subscriber<Integer>() {//subscribe == new Subscriber() overloaded methods
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}

