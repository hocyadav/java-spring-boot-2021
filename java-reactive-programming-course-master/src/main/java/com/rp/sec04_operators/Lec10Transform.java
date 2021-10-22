package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import com.rp.sec04_operators.helper.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

        //todo : https://projectreactor.io/docs/core/release/reference/index.html#advanced-mutualizing-operator-usage

        //simply converting one "input" type to another "input" type, and that "input" are publisher
        //if input pipeline data is Flux
        //Flux<> --> do processing/transform --> Flux<>
        //Flux<> --> do processing/transform --> Mono<>
        //no above is not correct, return type from Function Mapper will be same as input

        //if input pipeline data is Mono
        //Mono<> --> do processing/transform --> Flux<> / Mono<>

        getPerson()
//                .transform(applyFilterMap())
                .transform(personFlux -> {
                    Flux<Person> personFlux1 = personFlux;
                    return Mono.just(1);//but the return publisher will be Flux<Int> not Mono<Int>
                })
                .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> //input T1
                flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));//after lambda outut T2
    }


}
