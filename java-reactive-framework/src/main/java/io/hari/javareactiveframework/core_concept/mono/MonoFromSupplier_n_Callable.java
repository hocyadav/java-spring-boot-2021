package io.hari.javareactiveframework.core_concept.mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class MonoFromSupplier_n_Callable {
    public static void main(String[] args) {
        //supplier
        Supplier<String> supplier = () -> Util.getFakeName();
        Mono<String> supplierProducer = Mono.fromSupplier(supplier);
        supplierProducer.subscribe(Util.getOnNext());

        //callable
        Callable<String> callable = () -> Util.getFakeName();
        Mono<String> callableProducer = Mono.fromCallable(callable);
        callableProducer.subscribe(Util.getOnNext());
    }
}
