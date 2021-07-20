package io.hari.javareactiveframework.core_concept.hooks;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.function.Function;

@Slf4j
public class TestHooks {

    public static void main(String[] args) {
        //step 1: hooks
        //HOOKS type 1: add key/hook name + 2nd parameter as function mapper Publisher to publisher
        Hooks.onEachOperator("hooks-key", new Function<Publisher<Object>, Publisher<Object>>() {
            @Override
            public Publisher<Object> apply(Publisher<Object> objectPublisher) {
                log.info("hooks-key - apply - on each operator");
                return objectPublisher;
            }
        });

        //HOOKS type 2: function mapper publisher to publisher
        Hooks.onEachOperator(new Function<Publisher<Object>, Publisher<Object>>() {
            @Override
            public Publisher<Object> apply(Publisher<Object> objectPublisher) {
                log.info("apply - on each operator");
                return objectPublisher;
            }
        });

        //step 2 : stream/chain
        Flux.range(1, 3)//Range.1 - | onNext(1)
//                .log()
                .map(i -> i + 2)//MapFuseable.2 - | onNext(3)
//                .log()
                .subscribe(//Range.1
                        data -> System.out.println("data :"+data),//data :3
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }

    private static void hooks1() {
        Hooks.onEachOperator(new Function<Publisher<Object>, Publisher<Object>>() {
            @Override
            public Publisher<Object> apply(Publisher<Object> objectPublisher) {
                log.info("apply - on each operator");
                return objectPublisher;
            }
        });
    }

}
