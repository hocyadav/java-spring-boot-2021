package io.hari.javareactiveframework.reactive_pattern;

import reactor.core.publisher.Mono;

public class PipelineIntoDifferentLayer01 {
    public static void main(String[] args) {
//16 lec
        Mono<String> mono =
                //pipeline : start any HL into LL like this , and then break down into different methods, then different class
                //domain layer
                Mono.just(1)
                        .onErrorMap(throwable -> new RuntimeException("repo error " + throwable.getMessage()))//when above will throuw some db fetch error, and pipeline will stop here
                        .switchIfEmpty(Mono.error(new RuntimeException("domain oops")))//pipeline will stop if come here

                        //service layer
                                //service layer : validation start
                                .map(inputArg -> {
                                    //some validation logic: like fetch from db and check null or not
                                    return true;
                                })
                                .filter(aBoolean -> aBoolean)
                                .switchIfEmpty(Mono.error(new RuntimeException("validation oops")))
                                //service layer : validation end
                        .map(inputArg -> inputArg.toString())//business logic
                        .switchIfEmpty(Mono.error(new RuntimeException("service oops")))//pipeline will stop if come here

                        //api layer
                        .map(integer -> integer)//validation : move as a one method including switch if empty
                        .switchIfEmpty(Mono.error(new RuntimeException("validation oops")))
                        .map(input -> input.toUpperCase())
                        .map(input -> input.toUpperCase().concat(" - new string added "))
                        .switchIfEmpty(Mono.error(new RuntimeException("api oops")));//pipeline will stop if come here

        mono.subscribe(
                item -> System.out.println("item = " + item),
                errm -> System.out.println("errm.getMessage() = " + errm.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

}
