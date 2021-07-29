package io.hari.jmhbenchmarking;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark2 {
    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Trial)
        public void doSetup() {
            sum = 0;
            System.out.println("Do Setup");
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.println("Do TearDown");
        }

        public int a = 1;
        public int b = 2;
        public int sum ;
    }

    @OutputTimeUnit(TimeUnit.SECONDS)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)// time/op
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void testMethodSum(MyState state) {
        state.sum = state.a + state.b;
    }
}
