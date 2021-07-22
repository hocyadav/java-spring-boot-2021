package io.hari.javareactiveframework.core_concept.operator;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class NameTest {
    @Test
    public void test() {
        Flux.range(1, 3)
                .metrics() //??
                .log()
                .name("flux-stream")//??
//                .log()
                .subscribe(
                        data -> System.out.println("data = " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
}
