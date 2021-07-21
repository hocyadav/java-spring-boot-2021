package io.hari.javareactiveframework.reactive_context.temp;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

public class Test_writeContext_subscribeContext {

    @Test
    public void test() {
        Flux<Integer> flux = Flux.range(1, 3)
//                .subscriberContext()//set value inside context object, deprecated , use contextWrite
                .contextWrite(context -> Context.of("key1", "value1"));//set value inside context obeject

        flux.subscribe(
                next -> System.out.println("next = " + next),
                err -> System.out.println("err.getMessage() = " + err.getMessage()),
                () -> System.out.println()
        );
    }
}
