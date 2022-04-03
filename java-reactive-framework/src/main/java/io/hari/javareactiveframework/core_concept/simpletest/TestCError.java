package io.hari.javareactiveframework.core_concept.simpletest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TestCError {
    public static void main(String[] args) {

        Flux.just(1, 2, 3)
                .map(integer -> {
                    if (integer == 2) throw new RuntimeException("oops 2");
                    else return integer;
                })
                .onErrorResume(throwable -> {
                    return Flux.just(-1);
                })
                .subscribe(
                        i -> System.out.println("i = " + i)
                );
        System.out.println("==============");
        Flux.just(1, 2, 3)
                .map(integer -> {
                    if (integer == 2) throw new RuntimeException("oops 2");
                    else return integer;
                })
                .onErrorContinue((throwable, o) -> {
                    System.out.println("throwable = " + throwable);
                })
                .subscribe(
                        i -> System.out.println("i = " + i)
                );

        System.out.println("==============");
        //same as above one, on error continue will remove the on error resume
        Flux.just(1, 2, 3)
                .map(integer -> {
                    if (integer == 2) throw new RuntimeException("oops 2");
                    else return integer;
                })
                .onErrorResume(throwable -> {
                    System.out.println("log : on error resume 1");
                    return Flux.just(-1);
                })
                .onErrorContinue((throwable, o) -> {
                    System.out.println("throwable = " + throwable);
                })
                .subscribe(
                        i -> System.out.println("i = " + i)
                );

        System.out.println("==============");
        //add on error stop to == this step is similar to first one i.e. only on error resume
        Flux.just(1, 2, 3)
                .map(integer -> {
                    if (integer == 2) throw new RuntimeException("oops 2");
                    else return integer;
                })
                .onErrorResume(throwable -> {
                    System.out.println("log : on error resume 2");//this will print
                    return Flux.just(-1);//this will also print , but further pipeline values will be not executed
                }).onErrorStop()
                .onErrorContinue((throwable, o) -> {
                    System.out.println("throwable = " + throwable);
                })
                .subscribe(
                        i -> System.out.println("i = " + i)
                );

        System.out.println("==============");
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(throwable -> {
                    return Mono.just("just");
                })
                .subscribe(
                        i -> System.out.println("i = " + i),
                        throwable -> System.out.println("throwable = " + throwable.getMessage()),
                        () -> System.out.println("done")
                );

        System.out.println("==============");
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(throwable -> {
                    return Mono.empty();
                })
                .subscribe(
                        i -> System.out.println("i = " + i),
                        throwable -> System.out.println("throwable = " + throwable.getMessage()),
                        () -> System.out.println("done")
                );

        System.out.println("---------------");
        Flux.fromIterable(List.of(1, 2))
                .flatMap(integer -> {
                    return Mono.zip(Mono.just("hari"), Mono.just(2));
                })


                //some processing that can return error
                .map(objects -> {
                    if (objects.getT2() == 2) throw new RuntimeException("oops..");
                    return objects;
                })
                //handle error
                .onErrorResume(throwable -> {
                    System.out.println("    on error resume called");
                    return Mono.empty();
                })


                //Flux to Mono
                //t1 to t2 : tuple2<k,v> --> map<k,v>
                .collectMap(objects -> {//create key from tuple2
                    Tuple2<String, Integer> objects1 = objects;
                    System.out.println("t1 to t2 : tuple = " + objects1);
                    return "sample";
                }, objects -> {//create value from tuple2
                    return 123;
                })
                //t1 -> t2 : map<k,v> --> single object
//                .switchIfEmpty(Mono.error(new RuntimeException("0000")))
                .map(stringIntegerMap -> {
                    Map<String, Integer> stringIntegerMap1 = stringIntegerMap;
                    System.out.println("t1 to t2 : stringIntegerMap1 = " + stringIntegerMap1);
                    return "combine to one object";
                })



                .subscribe(i -> System.out.println("i = " + i),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("    DONE signal"));
    }
}
