package io.hari.javareactiveframework;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.scheduling.annotation.Schedules;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Hariom Yadav
 * @since 13/06/21
 */
@Slf4j
public class TestFluxMethods {

    @Test
    public void testFlux() {
        Flux<String> flux = Flux.just("A", "B", "C");
        flux.subscribe(data -> System.out.println(data));
    }

    @Test
    public void testFlux2() {
        Flux.just("A", "B", "C")
                .log()
                .subscribe(data -> System.out.println(data));
    }

    @Test
    public void testFlux3() {
        Flux.just("A", "B", "C")
                .log()
                .subscribe(data -> System.out.println(data));
    }

    @Test
    public void testFluxError() {
        Flux.just("A", "B", "C").concatWith(Flux.error(new RuntimeException("My error")))
                .log()
                .subscribe(data -> System.out.println(data));//subscriber is not catching error
    }

    @Test
    public void fluxWithHandleError() {
        Flux.fromIterable(List.of("hari", "om", "yadav"))
                .concatWith(Flux.error(new RuntimeException("My Error")))
                .onErrorReturn("Some item on exception")
//                .log()
                .subscribe(
                        data -> System.out.println("DATA :" + data),
                        error -> System.out.println("ERROR :" + error),
                        () -> System.out.println("DONE : done signal")
                );
    }

    /**
     * DATA :hari
     * DATA :om
     * DATA :yadav
     * DATA :Some item on exception
     * DONE : done signal
     */

    @Test
    public void testFluxError2() {
        Flux.just("A", "B", "C").concatWith(Flux.error(new RuntimeException("My error")))
                .log()
                .subscribe(data -> System.out.println(data),
                        error -> System.out.println(error));//subscriber is catching error
    }

    @Test
    public void testFluxError3() {
        Flux.just("A", "B", "C").concatWith(Flux.error(new RuntimeException("My error")))
                .log()
                .subscribe(data -> System.out.println(data),
                        error -> System.out.println(error));//subscriber is catching error
    }


    @Test
    public void testFluxError4() {
        Flux.just("A", "B", "C")
//                .concatWith(Flux.error(new RuntimeException("My error")))
                .log()
                .subscribe(data -> System.out.println(data),
                        error -> System.out.println(error),
                        () -> System.out.println("got onComplete signal"));//subscriber is catching error + added DONE channel
    }

    @Test
    public void fluxWithIterable() {
        Flux.fromIterable(List.of("A", "B", "C"))
                .log()
                .subscribe(data -> System.out.println(data));
    }

    @Test
    public void fluxWithRangeInfinite() {
        int startFrom = 1;
        int totalCountOrPageSize = 5;
        Flux.range(startFrom, totalCountOrPageSize)
                .log()
                .subscribe(data -> System.out.println(data));
    }

    @SneakyThrows
    @Test
    public void fluxWithInterval() {
        Flux.interval(Duration.ofSeconds(1))//run on different thread
                .log()
                .subscribe(data -> System.out.println(data));
        Thread.sleep(10_000);//run on main thread, to see output we need to pause main thread
    }

    @SneakyThrows
    @Test
    public void fluxWithInterval2() {
        Flux.interval(Duration.ofSeconds(1))//run on different thread
                .log()
                .take(3)//request for first 3 and then send cancel signal
                .subscribe(data -> System.out.println(data));
        Thread.sleep(10_000);//run on main thread, to see output we need to pause main thread
    }

    @SneakyThrows
    @Test
    public void fluxWithRangeAndSubscriptionRequest() {
        Flux.range(1, 9)
                .log()
                .subscribe(data -> System.out.println(data),
                        error -> System.out.println(error),
                        () -> System.out.println("Done signal"),
                        subscription -> subscription.request(3));//request for first 3
        Thread.sleep(10_000);//run on main thread, to see output we need to pause main thread
    }

    //todo : by default Flux and Mono are synchronous , now run in different thread using subscribeOn(scheduler obj) OR publishOn()
    @SneakyThrows
    @Test
    public void testRunInDifferentThread() {
        Flux<Integer> integerFlux =
                Flux.range(1, 4)//1 to 4
                        .subscribeOn(Schedulers.single())//all publisher code will run in separate thread : used for SLOW Producer(IO/NW call) & FAST Consumer
                        .publishOn(Schedulers.single())//same as subscribeOn but only below code will run in separate thread : used for FAST Producer & SLOW Consumer
                        .log()
                        .map(integer -> {
                            System.out.println("value = " + integer +" Thread :" + Thread.currentThread().getName()) ;
                            return integer;
                        });

        //working : add sleep after subscribe
        integerFlux.subscribe(
                integer -> System.out.println("integer = " + integer),
                throwable -> System.out.println("throwable = " + throwable),
                () -> System.out.println("Completed ")
        );
        TimeUnit.SECONDS.sleep(2);

        System.out.println("---------------------------");
        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }

    @SneakyThrows
    @Test
    public void monoRunInDifferentThread() {
        Mono<String> mono = Mono.just("data 123").log().subscribeOn(Schedulers.single());
        mono.subscribe(log::info);
        TimeUnit.SECONDS.sleep(2);
    }

    @SneakyThrows
    @Test
    public void testRunInDifferentThread_withMergeSequential() {
        Flux<Integer> integerFlux =
                Flux.range(1, 4)//1 to 4
                        .subscribeOn(Schedulers.single())//all publisher code will run in separate thread : used for SLOW Producer(IO/NW call) & FAST Consumer
                        .publishOn(Schedulers.single())//same as subscribeOn but only below code will run in separate thread : used for FAST Producer & SLOW Consumer
                        .log()
                        .map(integer -> {
                            System.out.println("value = " + integer +" Thread :" + Thread.currentThread().getName()) ;
                            return integer;
                        });

        Flux<Integer> integerFlux2 =
                Flux.range(10, 15)//1 to 4
                        .subscribeOn(Schedulers.single())//all publisher code will run in separate thread : used for SLOW Producer(IO/NW call) & FAST Consumer
                        .publishOn(Schedulers.single())//same as subscribeOn but only below code will run in separate thread : used for FAST Producer & SLOW Consumer
                        .log()
                        .map(integer -> {
                            System.out.println("value = " + integer +" Thread :" + Thread.currentThread().getName()) ;
                            return integer;
                        });

        Flux<Integer> mergeSequential = Flux.mergeSequential(integerFlux, integerFlux2, integerFlux);
        mergeSequential.subscribe(
                integer -> System.out.println("integer = " + integer)
        );
        TimeUnit.SECONDS.sleep(5);
    }

}
