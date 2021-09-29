package io.hari.javareactiveframework.core_concept.hooks;

import lombok.SneakyThrows;
import org.reactivestreams.Publisher;
import reactor.core.Scannable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Test_EventPublisherSubscriber {
    @SneakyThrows
    public static void main(String[] args) {
        Function<? super Publisher<Object>,
                ? extends Publisher<Object>> publisher_FunctionMapper = EventPublisherSubscriber.publisherOperator();

//        Hooks.onEachOperator(publisher_FunctionMapper);//this will add on each publisher
        Hooks.onLastOperator(publisher_FunctionMapper);
//        Hooks.onOperatorDebug();
        extracted();
//        extracted1();
//        extracted2();

//        testOnLastPublisher();

        //call on last subscription
    }

    public static void testOnLastPublisher() throws Exception {
        List<Publisher> l = new ArrayList<>();
        Hooks.onLastOperator(p -> {
            System.out.println(Scannable.from(p).parents().count());
            System.out.println(Scannable.from(p).stepName());
            l.add(p);
            return p;
        });
        StepVerifier.create(Flux.just(1, 2, 3)
                .map(m -> m)
                .takeUntilOther(Mono.never())
                .flatMap(d -> Mono.just(d).hide()))
                .expectNext(1, 2, 3)
                .verifyComplete();

        Hooks.resetOnLastOperator();
        System.out.println("l.size() = " + l.size());
        System.out.println("l = " + l);

    }

    private static void extracted2() {//total 3 call for each publisher - Hooks call.. see output 9 on next
        Flux.range(1, 3)//one publisher
                .map(i -> i + 2)
                .contextWrite(context -> context.put("user", "hariom"))//2nd publisher
                .subscribe(
                        data -> System.out.println("data " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
    /*
    --EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onNext
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 3
--EventPublisherSubscriber.onNext
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 4
--EventPublisherSubscriber.onNext
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 5
--EventPublisherSubscriber.onComplete
--EventPublisherSubscriber.onComplete
--EventPublisherSubscriber.onComplete
DONE signal
     */

    private static void extracted1() {//total 3 call for each publisher - Hooks call.. see output 6on next
        Flux.range(1, 3)//one publisher
//                .map(i -> i + 2)
                .contextWrite(context -> context.put("user", "hariom"))//2nd publisher
                .subscribe(
                        data -> System.out.println("data " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
    /*
    --EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 1
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 2
--EventPublisherSubscriber.onNext
user1 = hariom
--EventPublisherSubscriber.onNext
data 3
--EventPublisherSubscriber.onComplete
--EventPublisherSubscriber.onComplete
DONE signal
     */

    private static void extracted() {//total 3 call for each publisher - Hooks call.. see output 3 on next
        Flux.range(1, 2)
                .map(i -> i + 2)
                .contextWrite(context -> context.put("my_service", new MyService()))
                .contextWrite(context -> context.put("user", "hariom"))
                .subscribe(
                        data -> System.out.println("data : " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
    /*
    --EventPublisherSubscriber.onSubscribe
--EventPublisherSubscriber.onNext
data 1
--EventPublisherSubscriber.onNext
data 2
--EventPublisherSubscriber.onNext
data 3
--EventPublisherSubscriber.onComplete
DONE signal
     */
}
