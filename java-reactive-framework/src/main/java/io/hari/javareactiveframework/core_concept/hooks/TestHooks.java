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
        hooks1();


        Flux.range(1, 3)//Range.1 - | onNext(1)
//                .log()
                .map(i -> i + 2)//MapFuseable.2 - | onNext(3)
//                .log()
                .subscribe(//Range.1
                        data -> System.out.println("data :"+data),//data :3
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );

        Flux.range(1, 4)
                .log()
                .subscribe(new CoreSubscriber<Integer>() {
                    Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        s.request(100);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("next - "+integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("on error - ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete -- ");
                    }
                });
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
