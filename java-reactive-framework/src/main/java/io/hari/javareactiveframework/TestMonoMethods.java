package io.hari.javareactiveframework;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Hariom Yadav
 * @since 15/06/21
 */
public class TestMonoMethods {

    @Test
    public void testMono() {
        Mono.just("A")
                .log()
                .subscribe(
                        data -> System.out.println("DATA :" + data)
                );
    }

    @Test
    public void testMono_m1() {
        Mono.error(new Exception("my exception1"))
                .log()
                .subscribe(
                        data -> System.out.println("DATA :" + data),
                        error -> System.out.println("ERROR :" + error)
                );
    }

    @Test
    public void testMono_m2() {
        Mono.error(new Exception("my exception2"))
//                .log()
                .doOnError(error -> System.out.println("ERROR : " + error))
                .subscribe(
                        data -> System.out.println("DATA :" + data)
//                        error -> System.out.println("ERROR.. :"+error)
                );
    }


    @Test
    public void testMonoJustMethod() {
        String name = "hariom yadav";
        final Mono<String> stringMono = Mono.just(name)
                .log();

        //m1 subscribe
        stringMono.subscribe();
        System.out.println("-------- we can see 2 subscriber for above mono ----");
        //m2 subscribe using create
        StepVerifier.create(stringMono)//Subscribe
                .expectNext(name)
                .verifyComplete();
    }


    @Test
    public void testMono_usingMap_ThrowException() {
        final Mono<String> stringMono = Mono.just("hariom yadav")
                .map(stringMono_ -> {//change monoString to some other type : mapper
                    throw new RuntimeException("my exception");
                });

        stringMono.subscribe(
                dataChannel -> System.out.println("DATA : " + dataChannel),
                errorChannel -> System.out.println("ERROR : " + errorChannel)
        );
    }

    @Test
    public void testMonoCompleteChannel() {
        final Mono<String> stringMono = Mono.just("data 1")
                .log()//reactor.Mono.Just.1
                .map(s -> s.toUpperCase());
//                .log();//reactor.Mono.MapFuseable.1

        stringMono.subscribe(
                dataChannel -> System.out.println("data " + dataChannel),
                errorChannel -> System.out.println("error " + errorChannel),
                () -> System.out.println("complete")
        );
    }

    @Test
    public void testMono4thParameter_requestMethod() {
        final Mono<String> stringMono = Mono.just("data 1")
                .log()//reactor.Mono.Just.1
                .map(s -> s.toUpperCase());
//                .log();//reactor.Mono.MapFuseable.1

        stringMono.subscribe(
                dataChannel -> System.out.println("data " + dataChannel),
                errorChannel -> System.out.println("error " + errorChannel),
                () -> System.out.println("complete"),
                subscription -> subscription.request(5)//4th parameter as subscription
        );
    }

    @Test
    public void testMono4thParameter_cancelMethod() {
        final Mono<String> stringMono = Mono.just("data 1")
                .log()//reactor.Mono.Just.1
                .map(s -> s.toUpperCase());
//                .log();//reactor.Mono.MapFuseable.1

        stringMono.subscribe(
                dataChannel -> System.out.println("data " + dataChannel),
                errorChannel -> System.out.println("error " + errorChannel),
                () -> System.out.println("complete"),
                subscription -> subscription.cancel()//4th parameter as subscription
        );
    }


    @Test
    public void testMono_doOn_method() {
        final Mono<String> stringMono = Mono.just("input data 123")
                .log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> System.out.println("1 mono subscribed :" + subscription))
                .doOnRequest(value -> System.out.println("2 request : " + value))
                .doOnNext(s -> System.out.println("3 next :" + s))
                .doOnSuccess(s -> System.out.println("4 success : " + s));

        stringMono.subscribe(
                dataChannel -> System.out.println("receiving data : " + dataChannel),
                errorChannel -> System.out.println("receiving error : " + errorChannel),
                () -> System.out.println("receiving complete : "),
                subscription -> subscription.request(1)
        );
    }

    //Mono.just("a").flatMap(s -> Mono.empty()) //same as Mono.empty()
    @Test
    public void foo() {
        Mono.just("a")
//                .flatMap(s -> Mono.empty()) //same as Mono.empty()
                .flatMap(s -> Mono.error(new Exception("my exception")))
                .log()
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe = " + subscription))
//                .doOnNext(s -> System.out.println("doOnNext = " + s))//HAPPY FLOW : 1. onNext call <=> 2. then doOnNext call [Bi directional] -> 3. then subscriber get data from dataChannel i.e. publisher will send data to downstream
                //NOT HAPPY FLOW : if there is no next call(i.e. in case of cancel() call) then -> no doOnNext call
                .doOnSuccess(s -> System.out.println("doOnSuccess = " + s))//HAPPY FLOW : 1st doOnSuccess call <=> 2. then onNext() /OR/ onComplete() call [It's a bi-direction]
                //NOT HAPPY FLOW : No doOnSunncess() call <=> if no onNext or no onComplete() call [Bidirectional]
                .subscribe(
                        s -> System.out.println("subscriber get output data = " + s),//2. doOnNext call 1st -> 3. then here data will come [see pic]
                        throwable -> System.out.println("throwable = " + throwable),
                        () -> System.out.println("completed signal"),
                        subscription -> subscription.cancel() //for testing doOnSuccess Not Happy flow
                );
    }

    @Test
    public void doOnSuccess_HAPPY_FLOW_0() {
        Mono.just("hariom").log()
                .doOnSuccess(s -> System.out.println("doOnSuccess = " + s))//HAPPY FLOW : 1st doOnSuccess call <=> 2. then onNext() /OR/ onComplete() call [It's a bi-direction]
                //NOT HAPPY FLOW : No doOnSunncess() call <=> if no onNext or no onComplete() call [Bidirectional]
                .subscribe(
                        s -> System.out.println("subscriber get output data = " + s),//2. doOnNext call 1st -> 3. then here data will come [see pic]
                        throwable -> System.out.println("throwable = " + throwable),
                        () -> System.out.println("completed signal")

                        //for testing doOnSuccess Not Happy flow,
                        // but here no use we can uncomment also, since we are using mono.empty it will call onComplete instead of onNext, so for doOnSuccess will execute
//                        subscription -> subscription.cancel()
                );
    }

    @Test
    public void doOnSuccess_HAPPY_FLOW() {
        Mono.empty().log()
                .doOnSuccess(s -> System.out.println("doOnSuccess = " + s))//HAPPY FLOW : 1st doOnSuccess call <=> 2. then onNext() /OR/ onComplete() call [It's a bi-direction]
                //NOT HAPPY FLOW : No doOnSunncess() call <=> if no onNext or no onComplete() call [Bidirectional]
                .subscribe(
                        s -> System.out.println("subscriber get output data = " + s),//2. doOnNext call 1st -> 3. then here data will come [see pic]
                        throwable -> System.out.println("throwable = " + throwable),
                        () -> System.out.println("completed signal"),

                        //for testing doOnSuccess Not Happy flow,
                        // but here no use we can uncomment also, since we are using mono.empty it will call onComplete instead of onNext, so for doOnSuccess will execute
                        subscription -> subscription.cancel()
                );
    }

    @Test
    public void doOnSuccess_NOT_HAPPY_FLOW() {
        Mono.just("a").log()
                .doOnSuccess(s -> System.out.println("doOnSuccess = " + s))
                //HAPPY FLOW : 1st doOnSuccess call <=> 2. then onNext() /OR/ onComplete() call [It's a bi-direction]
                //NOT HAPPY FLOW : No doOnSunncess() call <=> if no onNext or no onComplete() call [Bidirectional]
                .subscribe(
                        s -> System.out.println("subscriber get output data = " + s),//2. doOnNext call 1st -> 3. then here data will come [see pic]
                        throwable -> System.out.println("throwable = " + throwable),
                        () -> System.out.println("completed signal"),

                        //for testing doOnSuccess "NOT HAPPY FLOW"
                        // but here no use we can uncomment also, since we are using mono.empty it will call onComplete instead of onNext, so for doOnSuccess will execute
                        subscription -> subscription.cancel()
                );
    }

    @Test
    public void testMono_doOn_method_executingSuccessAfterFlatMap() {
        final Mono<Object> stringMono = Mono.just("input data 123")
                .log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> System.out.println("1 mono subscribed :" + subscription))
                .doOnRequest(value -> System.out.println("2 request : " + value))
                .doOnNext(s -> System.out.println("3 next :" + s))
                .flatMap(s -> Mono.empty())//1 changing mono type + 2.change output type String -> Object
                .doOnNext(s -> System.out.println("3.b next :" + s))//this will not execute because of above mono.empty
                .doOnSuccess(s -> System.out.println("4 success : " + s));//this will execute because we have successfully send input data but above we have change type so we can see null

        stringMono.subscribe(
                dataChannel -> System.out.println("receiving data : " + dataChannel),
                errorChannel -> System.out.println("receiving error : " + errorChannel),
                () -> System.out.println("receiving complete : "),
                subscription -> subscription.request(1)
        );
    }

    @Test
    public void testMono_doOn_method_executingSuccessAfterFlatMap2() {
        final Mono<Object> stringMono = Mono.just("input data 123")
                .log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> System.out.println("1 mono subscribed :" + subscription))
                .doOnRequest(value -> System.out.println("2 request : " + value))
                .doOnNext(s -> System.out.println("3 next :" + s))
                .doOnSuccess(s -> System.out.println("4.. success : " + s))//this will execute because we have successfully send input data

                .flatMap(s -> Mono.empty())//1 changing mono type + 2.change output type String -> Object

                .doOnNext(s -> System.out.println("3.b next :" + s))//this will not execute because of above mono.empty
                .doOnSuccess(s -> System.out.println("4 success : " + s));//this will execute because we have successfully send input data but above we have change type so we can see null

        stringMono.subscribe(
                dataChannel -> System.out.println("receiving data : " + dataChannel),
                errorChannel -> System.out.println("receiving error : " + errorChannel),
                () -> System.out.println("receiving complete signal : "),
                subscription -> subscription.request(1)
        );
    }
}
