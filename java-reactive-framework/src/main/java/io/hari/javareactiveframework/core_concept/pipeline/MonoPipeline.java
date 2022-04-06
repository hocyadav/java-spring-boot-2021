package io.hari.javareactiveframework.core_concept.pipeline;

import lombok.SneakyThrows;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class MonoPipeline {
    @Test
    public void foo(){
//        monoPipeline1();
//        monoPipeline1Async1();
//        monoPipeline1Async2();
//        monoPipelineJoin2Pipeline();
//        monoPipelineError();
//        monoCombine2Pipeline();
//        monoCombine3Pipeline0();
//        monoCombine3Pipeline();
        errorResumeAndThenAddFilter();
        errorResumeAndThenAddFilter2();
    }

    /**
     * p1 : eager + sync(run by main thread tm)
     */
    public void monoPipeline1(){
        Mono<String> p1 = Mono.just("some processing in thread - "+ Thread.currentThread().getId());
        p1.subscribe(
                data -> System.out.println("data signal= " + data),
                error -> System.out.println("error signal = " + error.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    public void monoPipeline1Async1(){
        Mono<String> p1 = Mono.just("some processing in thread - "+ Thread.currentThread().getId());//eager -> main thread tm
        p1
                .doOnNext(s -> System.out.println(Thread.currentThread().getId() + " - do on next"))//run async , t1
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                data -> System.out.println("data signal= " + data),
                error -> System.out.println("error signal = " + error.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    public void monoPipeline1Async2(){
        Mono<String> p1 = Mono.fromCallable(() -> "some processing in thread - "+ Thread.currentThread().getId());//lazy : async (if subscribeon present else in main thread)
        p1
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                data -> System.out.println("data signal= " + data),
                error -> System.out.println("error signal = " + error.getMessage()),
                () -> System.out.println("complete signal")
        );
    }

    /**
     * p1 : lazy + async
     * p2 : lazy + async
     */
    @SneakyThrows
    public void monoPipelineJoin2Pipeline(){
        Mono<String> p1 = Mono.fromCallable(() -> "p1 some processing in thread - " + Thread.currentThread().getId());

        //join p1 -1:m-> p1, // p1 then p2,// for each p1 execute p2
        p1.flatMap(p1UpstreamData -> {
            return getP2(p1UpstreamData);
        })
                .subscribeOn(Schedulers.single())
                .subscribe(
                        data -> System.out.println("data signal= " + data),
                        error -> System.out.println("error signal = " + error.getMessage()),
                        () -> System.out.println("complete signal")

                );
        Thread.sleep(111);
    }

    private Mono<String> getP2(String fromP1) {
        return Mono.fromCallable(() -> fromP1 +
                " - p2 some processing in thread - " +
                Thread.currentThread().getId());
    }

    @SneakyThrows
    public void monoPipelineError() {
        Mono<String> p1 = Mono.fromCallable(() -> "some processing - " + Thread.currentThread().getId());
        p1
                .map(s -> {
                    System.out.println("map - "+Thread.currentThread().getId());
//                    return s;
                    return null;//error in downstream pipeline, execute onErrorResume
                })
                //some error in pipeline, dont stop set default / empty
                .onErrorResume(throwable -> {
                    System.out.println("executing onErrorResume - "+Thread.currentThread().getId());
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                        data -> System.out.println("data signal= " + data),
                        error -> System.out.println("error signal = " + error.getMessage()),
                        () -> System.out.println("complete signal")
                );
        Thread.sleep(111);
    }

    /**
     * p1 lazy + async
     * p2 lazy + async
     * p1 + p2 run parallel in async mode and combine + do some processing
     */
    @SneakyThrows
    public void monoCombine2Pipeline(){
        Mono<String> p1 = Mono.fromCallable(() -> " p1 some processing thread - "+Thread.currentThread().getId());
        Mono<String> p2 = Mono.fromCallable(() -> " p2 some processing thread - "+Thread.currentThread().getId());

//        p1.zipWith(p2)
        p1.zipWith(p2, //run parallel and combine
                String::concat) //take result and do processing

                .subscribeOn(Schedulers.parallel())
                .subscribe(
                        data -> System.out.println("data signal= " + data),
                        error -> System.out.println("error signal = " + error.getMessage()),
                        () -> System.out.println("complete signal")
                );
        Thread.sleep(1111);
    }

    /**
     * p1 p2 p3 lazy + async
     * all run by 1 background thread
     * to run in different thread then see next example
     *
     */
    @SneakyThrows
    public void monoCombine3Pipeline0(){
        Mono<String> p1 = Mono.fromCallable(() -> " p1 do some processing thread - " + Thread.currentThread().getId());
        Mono<String> p2 = Mono.fromCallable(() -> " p2 do some processing thread - " + Thread.currentThread().getId());
        Mono<String> p3 = Mono.fromCallable(() -> " p3 do some processing thread - " + Thread.currentThread().getId());

        //t1 : array, t2 : single value
        Function<Object[], String> arrayToSomeValueMapper = new Function<Object[], String>() {
            @Override
            public String apply(Object[] objects) {
                Stream<Object> stream = Arrays.stream(objects);
                Stream<String> stringStream = stream.map(o -> String.class.cast(o));
                String reduce = stringStream.reduce("default value + ", String::concat);
                return reduce;
            }
        };

//        Mono.zip(p1, p2, p3)
        Mono.zip(arrayToSomeValueMapper, //2 then use mapper to do something with array result
                new Mono[]{p1, p2, p3})//1 run all in async
                .subscribeOn(Schedulers.parallel())//all above to down pipeline will be run in this background thread
                .doOnNext(s -> {
                    System.out.println("doOnNext - "+Thread.currentThread().getId());
                })
                .subscribe(
                        data -> System.out.println("data signal= " + data),
                        error -> System.out.println("error signal = " + error.getMessage()),
                        () -> System.out.println("complete signal")
                );
        Thread.sleep(1000);
    }

    /**
     * p1, p2, p3 lazy + async
     * p4 : DAG pipeline will be run in different thread
     * total 4 thread
     *
     */
    @SneakyThrows
    public void monoCombine3Pipeline(){
        Mono<String> p1 = Mono.fromCallable(() -> " p1 do some processing thread - " + Thread.currentThread().getId()).subscribeOn(Schedulers.parallel());
        Mono<String> p2 = Mono.fromCallable(() -> " p2 do some processing thread - " + Thread.currentThread().getId()).subscribeOn(Schedulers.parallel());
        Mono<String> p3 = Mono.fromCallable(() -> " p3 do some processing thread - " + Thread.currentThread().getId()).subscribeOn(Schedulers.parallel());

        //t1 : array, t2 : single value
        Function<Object[], String> arrayToSomeValueMapper = new Function<Object[], String>() {
            @Override
            public String apply(Object[] objects) {
                Stream<Object> stream = Arrays.stream(objects);
                Stream<String> stringStream = stream.map(o -> String.class.cast(o));
                String reduce = stringStream.reduce("default value + ", String::concat);
                return reduce;
            }
        };

//        Mono.zip(p1, p2, p3)
        Mono.zip(arrayToSomeValueMapper, //2 then use mapper to do something with array result
                new Mono[]{p1, p2, p3})//1 run all in async
                .publishOn(Schedulers.boundedElastic())//from here onward all down steps will run in different threads
                .doOnNext(s -> {
                    System.out.println("doOnNext - "+Thread.currentThread().getId());
                })
                .subscribe(
                        data -> System.out.println("data signal= " + data),
                        error -> System.out.println("error signal = " + error.getMessage()),
                        () -> System.out.println("complete signal")
                );
        Thread.sleep(1000);
    }

    public void errorResumeAndThenAddFilter(){
        //problem is that we can not set default value for the error/exception one
        Flux.just(1,2,3,4,5)
                .map(integer -> {
                    if (integer == 3) throw new RuntimeException("oops");
                    return integer;
                })
//                .onErrorResume(throwable -> {//no use of onErrorResume
//                    return Flux.just(-1);
//                })
                .onErrorContinue((throwable, o) -> {
                    //log line
//                    System.out.println("throwable.getMessage() = " + throwable.getMessage());
                })
//                .filter(o -> !o.equals(-1))//not required if we are using onErrorContinue coz it will not do processing further for that data
                .doOnNext(o -> {
                    System.out.println("o = " + o);
                })
                .subscribe();
        /** output1
         *
         * o = 1
         * o = 2
         * throwable.getMessage() = oops //this is simple log line , this will not part of final result
         * o = 4
         * o = 5
         *
         * comment log line, and see output
         *          * o = 1
         *          * o = 2
         *          * o = 4
         *          * o = 5
         */
    }

    public void errorResumeAndThenAddFilter2(){
        //we can set default value for the error one and we can ignore by just adding filter operator
        Flux.just(1,2,3,4,5)
                .flatMap(integer -> {
                    //mono pipeline for each flatmap,
                    //we know for some data it will throw exception, and for that data we will send default value from here
                    //and outside flux pipeline will filter out these defalut value
                    return Mono.fromCallable(() -> integer)
                            .map(integer1 -> {
                                if (integer1 == 3) throw new RuntimeException("oops");
                                return integer1;
                            })
                            .onErrorResume(throwable -> {
//                                System.out.println("throwable.getMessage() = " + throwable.getMessage());
                                return Mono.just(-1);
                            });
                })
                //input : 1,2,-1,4,5
                .filter(o -> !o.equals(-1))//not required if we are using onErrorContinue coz it will not do processing further for that data
                //here : 1,2,4,5
                .doOnNext(o -> {
                    System.out.println("o = " + o);
                })
                .subscribe();
        /**
         * output 2 : here 3 value error will not present in final output
         *
         * o = 1
         * o = 2
         * o = 4
         * o = 5
         *
         * or if we not use filter (this output we cant get in above simple flux pipeline)
         *
         * o = 1
         * o = 2
         * o = -1
         * o = 4
         * o = 5
         */
    }
}
/**
 * mono async
 * - all the pipeline will execute by same background thread
 * - to run each pipeline p1 p2 p3..in different background thread then add  .subscribeOn(Schedulers.parallel() to each pipeline
 */
