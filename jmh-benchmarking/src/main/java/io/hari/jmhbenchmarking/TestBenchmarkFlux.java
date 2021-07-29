package io.hari.jmhbenchmarking;

import org.openjdk.jmh.annotations.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class TestBenchmarkFlux {

    @OutputTimeUnit(TimeUnit.SECONDS)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)// time/op
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void fluxTest() {
        fib_method(53);
    }

    static String fib_method(int n) {
        Mono<String> mono =
                Mono.just(n)
                        .flatMap(integer -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return Mono.just(integer.toString());
                        })
                        .switchIfEmpty(Mono.just("in case of empty"));
        return mono.block();
    }
    //TestBenchmarkFlux.fluxTest  avgt       ≈ 10⁻⁷           s/op

    //Thread.sleep 1sec
    //TestBenchmarkFlux.fluxTest  avgt        1.003           s/op



//    @SneakyThrows
//    static String fib_method(int n) {
//        String s = String.valueOf(n);
//        Thread.sleep(2000);
//        if (s == null) return "empty";
//        else return s;
//    }
    //TestBenchmarkFlux.fluxTest  avgt        2.003           s/op
}
