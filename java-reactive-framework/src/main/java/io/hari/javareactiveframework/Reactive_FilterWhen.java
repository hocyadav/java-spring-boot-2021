package io.hari.javareactiveframework;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class Reactive_FilterWhen {

    @Test
    public void foo(){
        Mono<Boolean> isAuthenticatedMono = getIsAuthenticatedMono();
        Mono<Integer> ageMono = fun();

        ageMono
                .filterWhen(integer -> getIsAuthenticatedMono())
                .subscribe(age -> System.out.println("Age: " + age));
    }

    private Mono<Integer> fun() {
        log.info("fun called ");
        return Mono.just(123);
    }

    private Mono<Boolean> getIsAuthenticatedMono() {
        log.info("getIsAuthenticatedMono ");
//        return Mono.just(true);//Age: 123
        return Mono.just(true);// no output
    }
}

/**
 * true
 19:39:37.457 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - getIsAuthenticatedMono
 19:39:37.520 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
 19:39:37.520 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - fun called
 19:39:37.531 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - getIsAuthenticatedMono
 Age: 123
 *
 *
 * false
 *
 19:39:03.402 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - getIsAuthenticatedMono
 19:39:03.463 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
 19:39:03.463 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - fun called
 19:39:03.475 [main] INFO io.hari.javareactiveframework.Reactive_FilterWhen - getIsAuthenticatedMono
 */
