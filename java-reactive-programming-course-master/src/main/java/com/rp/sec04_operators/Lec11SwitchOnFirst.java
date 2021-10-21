package com.rp.sec04_operators;

import com.rp.courseutil.Util;
import com.rp.sec04_operators.helper.Person;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec11SwitchOnFirst {

    public static void main(String[] args) {

        //switchOnFirst : execute only one time and that is for 1st data
        //Now 1st data is inside switchOnFirst, we can check and decide what to do pass as it is i.e. old data or we can pass to some other Mapper,
        // but this mapper will be apply for all data that will come after this 1st data
        getPerson()
                .switchOnFirst((signal, personFlux) -> {
                    System.out.println("inside switch-on-first");
                    return signal.isOnNext() && signal.get().getAge() > 10
                            ? personFlux //if condition pass then simply pass that data to downstream
                            : applyFilterMap().apply(personFlux);//this will be apply to all data if condition is fail
                })
                .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPerson(){
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return flux -> flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
    }




}
