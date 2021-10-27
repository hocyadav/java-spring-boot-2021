package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Builder
class EntityA{
    String name;

}

public class Lec06OnError {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
//                .onErrorReturn(-1)//hardcoded value fallback : its like defaultIfEmpty
//                 .onErrorResume(e -> fallback())//fallback Publisher: its like switchIfEmpty
                //above 2 operator will stop pipeline if error happen, onErrorContinue will continue other data
                .onErrorContinue((err, obj) -> {//even in case of error continue

                })
                .subscribe(Util.subscriber());

        //todo : testing is mono.empty return actual entity or null or mono
        Mono<EntityA> entityAMono = Mono.just(EntityA.builder().name("name2").build()).onErrorReturn(null);
        EntityA entityA = entityAMono.block();
        System.out.println("entityA = " + entityA);

        Mono<Object> opps = Mono.error(new RuntimeException("opps")).onErrorResume(e -> Mono.empty());
//        Mono<EntityA> opps = Mono.just(EntityA.builder().name("name2").build()).onErrorResume(e -> Mono.empty());
        Object block = opps.block();
        System.out.println("block = " + block);


    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }

}
