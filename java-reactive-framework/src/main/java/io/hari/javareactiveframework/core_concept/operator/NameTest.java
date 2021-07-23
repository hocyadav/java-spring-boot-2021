package io.hari.javareactiveframework.core_concept.operator;

import org.junit.Test;
import reactor.core.Scannable;
import reactor.core.publisher.Flux;

public class NameTest {
    @Test
    public void test() {
        Flux<Integer> upstreamFlux = Flux.range(1, 3)
//                .metrics() //??
                .log()
                .name("flux-stream");//??
//                .log();


        Scannable scannable = Scannable.from(upstreamFlux);
        System.out.println("scannable = " + scannable);
        String name = scannable.name();
        System.out.println("name = " + name);//flux-stream

        Flux<Integer> integerFlux = upstreamFlux.name("new-name");
        Scannable scannable1 = Scannable.from(integerFlux);
        System.out.println("scannable1.name() = " + scannable1.name());// new-name


        upstreamFlux.subscribe(
                        data -> System.out.println("data = " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
}
