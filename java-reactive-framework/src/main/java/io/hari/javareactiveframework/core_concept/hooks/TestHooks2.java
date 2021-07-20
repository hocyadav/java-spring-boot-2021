package io.hari.javareactiveframework.core_concept.hooks;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class TestHooks2 {

    public static void main(String[] args) {

        Flux.range(1, 4)
                .log()
                .subscribe(new CoreSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
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
}
