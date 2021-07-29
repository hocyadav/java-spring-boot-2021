package jhm_official_sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * https://github.com/hariomyadav/jmh/tree/master/jmh-samples/src/main/java/org/openjdk/jmh/samples
 */
public class JMHSample_01_HelloWorld {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_01_HelloWorld.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)

    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }
}
