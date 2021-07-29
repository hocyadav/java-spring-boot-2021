package jhm_official_sample;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class JMHSample_02_BenchmarkModes {

    /* DONE
     * Mode.Throughput, as stated in its Javadoc, measures the raw throughput by
     * continuously calling the benchmark method in a time-bound iteration, and
     * counting how many times we executed the method.
     *
     * We are using the special annotation to select the units to measure in,
     * although you can use the default.
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException {//9.748 ops/s --close to--> 9 operation in 1 sec, not 10 operation
        TimeUnit.MILLISECONDS.sleep(100);//expected 10 operation in 1 sec, but only 9 operations executed
    }

    /* DONE
     * Mode.AverageTime measures the average execution time, and it does it
     * in the way similar to Mode.Throughput.
     *
     * Some might say it is the reciprocal throughput, and it really is.
     * There are workloads where measuring times is more convenient though.
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAvgTime() throws InterruptedException {//101848.131 us/op --close to--> 101.848 milli/s
        TimeUnit.MILLISECONDS.sleep(100);//expected to execute in 100 milli sec, but it took 101.848
    }

    /* ??
     * Mode.SampleTime samples the execution time. With this mode, we are
     * still running the method in a time-bound iteration, but instead of
     * measuring the total time, we measure the time spent in *some* of
     * the benchmark method calls.
     *
     * This allows us to infer the distributions, percentiles, etc.
     *
     * JMH also tries to auto-adjust sampling frequency: if the method
     * is long enough, you will end up capturing all the samples.
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Mode.SingleShotTime measures the single method invocation time. As the Javadoc
     * suggests, we do only the single benchmark method invocation. The iteration
     * time is meaningless in this mode: as soon as benchmark method stops, the
     * iteration is over.
     *
     * This mode is useful to do cold startup tests, when you specifically
     * do not want to call the benchmark method continuously.
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * We can also ask for multiple benchmark modes at once. All the tests
     * above can be replaced with just a single test like this:
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /*
     * Or even...
     */
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_02_BenchmarkModes.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
/**
 Benchmark                                                              Mode  Cnt       Score     Error   Units

 JMHSample_02_BenchmarkModes.measureAll                                thrpt           ≈ 10⁻⁵            ops/us
 JMHSample_02_BenchmarkModes.measureMultiple                           thrpt           ≈ 10⁻⁵            ops/us
 JMHSample_02_BenchmarkModes.measureThroughput                         thrpt            9.748             ops/s
 JMHSample_02_BenchmarkModes.measureAll                                 avgt       102186.051             us/op
 JMHSample_02_BenchmarkModes.measureAvgTime                             avgt       101848.131             us/op
 JMHSample_02_BenchmarkModes.measureMultiple                            avgt       102273.217             us/op
 JMHSample_02_BenchmarkModes.measureAll                               sample   98  102649.438 ± 595.034   us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.00              sample       100007.936             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.50              sample       102957.056             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.90              sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.95              sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.99              sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.999             sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p0.9999            sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll:measureAll·p1.00              sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple                          sample   98  102688.225 ± 615.487   us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.00    sample       100007.936             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.50    sample       102694.912             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.90    sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.95    sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.99    sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.999   sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p0.9999  sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureMultiple:measureMultiple·p1.00    sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureSamples                           sample   98  102373.919 ± 591.984   us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.00      sample       100007.936             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.50      sample       102301.696             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.90      sample       104857.600             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.95      sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.99      sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.999     sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p0.9999    sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureSamples:measureSamples·p1.00      sample       104988.672             us/op
 JMHSample_02_BenchmarkModes.measureAll                                   ss       100669.477             us/op
 JMHSample_02_BenchmarkModes.measureMultiple                              ss       102862.318             us/op
 JMHSample_02_BenchmarkModes.measureSingleShot                            ss       104933.176             us/op
 */