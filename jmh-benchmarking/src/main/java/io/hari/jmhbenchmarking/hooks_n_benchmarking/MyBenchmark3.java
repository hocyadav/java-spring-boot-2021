package io.hari.jmhbenchmarking.hooks_n_benchmarking;

import org.openjdk.jmh.annotations.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.concurrent.TimeUnit;

public class MyBenchmark3 {
    @State(Scope.Thread)
    public static class MyState {
        @Setup(Level.Trial)
        public void doSetup() {
            Hooks.onEachOperator(EventPublisherSubscriber.publisherOperator(new MyService()));//this will add on each publisher
            System.out.println("Do Setup -- Hooks.onEachOperator");
        }
        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.println("Do TearDown -- Hooks.onEachOperator");
        }
    }


    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)// ops/time
    @Fork(value = 1)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 30, time = 1)
    public static void extractedNotUsingHooks(MyState state) {//total 3 call for each publisher - Hooks call.. see output 3 on next
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
                .subscribe();
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

