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

        BiFunction<Integer, Integer, Integer> biFunction = new BiFunction<>() {
            @Override
            public Integer apply(Integer type1, Integer type2) {//this will be executed when we call apply
                //do some operation on type1 and type2
                int type3 = type1 + type2;
                return type3;//and return type3
            }
        };
        Integer type3 = biFunction.apply(1, 2);//execute apply method
        System.out.println("type3 = " + type3);//3

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
