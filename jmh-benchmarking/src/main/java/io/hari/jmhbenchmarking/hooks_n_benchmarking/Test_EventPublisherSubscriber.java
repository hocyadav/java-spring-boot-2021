package io.hari.jmhbenchmarking.hooks_n_benchmarking;

import lombok.SneakyThrows;

public class Test_EventPublisherSubscriber {
    @SneakyThrows
    public static void main(String[] args) {
        org.openjdk.jmh.Main.main(args);
    }
}
/**
 *
 not using hooks
 594.568 ±(99.9%) 11.824 ops/ms [Average]
 (min, avg, max) = (558.109, 594.568, 633.937), stdev = 17.698
 CI (99.9%): [582.744, 606.392] (assumes normal distribution)

using hooks
 134.474 ±(99.9%) 6.216 ops/ms [Average]
 (min, avg, max) = (113.967, 134.474, 146.962), stdev = 9.303
 CI (99.9%): [128.258, 140.690] (assumes normal distribution)


 209.393 ±(99.9%) 15.913 ops/ms [Average]
 (min, avg, max) = (142.833, 209.393, 236.575), stdev = 23.818
 CI (99.9%): [193.479, 225.306] (assumes normal distribution)
 */

