package io.hari.javareactiveframework;

import reactor.core.publisher.Mono;

public class MonoSupplier {

    public static void main(String[] args) {
        //use when we have fixed data
//        Mono<String> monoProducer = Mono.just(getNameString());

        Mono<String> monoProducerSupplier = Mono.fromSupplier(() -> Util.getNameString());
        monoProducerSupplier.subscribe(
                Util.getNextData()
        );

    }

}


