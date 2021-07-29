package io.hari.jmhbenchmarking.hooks_n_benchmarking;

import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.*;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Test_EventPublisherSubscriber {
    @SneakyThrows
    public static void main(String[] args) {
        Function<? super Publisher<Object>, ? extends Publisher<Object>> publisher_FunctionMapper = EventPublisherSubscriber.publisherOperator();
        Hooks.onEachOperator(publisher_FunctionMapper);//this will add on each publisher

        org.openjdk.jmh.Main.main(args);
        extractedNotUsingHooks();
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)// time/op
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public static void extractedNotUsingHooks() {//total 3 call for each publisher - Hooks call.. see output 3 on next
        Flux.range(1, 3)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .contextWrite(context -> context.put("my_service", new MyService()))
                .contextWrite(context -> context.put("user", "hariom"))
                .subscribe(
                        data -> System.out.println("data " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
    //with Hooks
    // hooks_n_benchmarking.Test_EventPublisherSubscriber.extracted  avgt       ≈ 10⁻⁵           s/op

    //without Hooks : comment hooks.onEachOperator
    //hooks_n_benchmarking.Test_EventPublisherSubscriber.extracted  avgt       ≈ 10⁻⁵           s/op
}

