package io.hari.javareactiveframework.core_concept.mono;

import reactor.core.publisher.Mono;

public class MonoSupplier {

    public static void main(String[] args) {
        //use when we have fixed data
//        Mono<String> monoProducer = Mono.just(getNameString());

        Mono<String> monoProducerSupplier = Mono.fromSupplier(() -> Util.getFakeName());
        monoProducerSupplier.subscribe(
                Util.getOnNext()
        );

    }

}


