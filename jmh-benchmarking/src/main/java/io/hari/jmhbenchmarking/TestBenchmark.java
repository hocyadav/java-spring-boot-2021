package io.hari.jmhbenchmarking;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBenchmark {

    @OutputTimeUnit(TimeUnit.SECONDS)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)// time/op
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void init() {
        fib_method(2);
    }

    static int fib_method(int n) {
        if (n<2) return 1;
        else return fib_method(n-1) + fib_method(n-2);
    }
}
