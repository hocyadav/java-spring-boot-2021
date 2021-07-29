package io.hari.jmhbenchmarking.hooks_n_benchmarking;

import org.openjdk.jmh.annotations.*;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MyBenchmark3 {
    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Trial)
        public void doSetup() {
            Function<? super Publisher<Object>,
                    ? extends Publisher<Object>> publisher_FunctionMapper = EventPublisherSubscriber.publisherOperator();

//            Hooks.onEachOperator(publisher_FunctionMapper);//this will add on each publisher

            sum = 0;
            System.out.println("Do Setup -- Hooks.onEachOperator");
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.println("Do TearDown -- Hooks.onEachOperator");
        }

        public int a = 1;
        public int b = 2;
        public int sum;
    }


    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)// time/op
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public static void extractedUsingHooks(MyState state) {//total 3 call for each publisher - Hooks call.. see output 3 on next
        Flux.range(1, 3)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
                .map(i -> i + 2)
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

}
//not using hooks : comment
//    hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt        0.023          ms/op
//using hooks
//    hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt        0.496          ms/op

//not using hooks
//    hooks_n_benchmarking.Test_EventPublisherSubscriber.extractedNotUsingHooks  avgt        0.020          ms/op

//with long chain + without using Hooks
//hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt        0.025          ms/op

//with long chain + using Hooks
//hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt        2.003          ms/op

//with hooks + long chain
//hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt       2041.328          us/op

//without hooks + long chain
//hooks_n_benchmarking.MyBenchmark3.extractedUsingHooks                      avgt       24.882          us/op

