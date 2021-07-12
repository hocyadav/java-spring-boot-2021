package io.hari.javareactiveframework.context;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Test_ {

    /** https://projectreactor.io/docs/core/release/reference/#_simple_context_examples
     *
     * The numbering above versus the actual line order is not a mistake. It represents the execution order.
     * Even though contextWrite is the last piece of the chain,
     * it is the one that gets executed first (due to its subscription-time nature and
     * the fact that the subscription signal flows from bottom to top).
     */
    @Test
    public void test1() {
        String key = "dummy_key";
        Mono<String> r = Mono.just("Hello_value1")
                .flatMap(s -> Mono.deferContextual(ctx ->
                        Mono.just(s + " " + ctx.get(key))))//2. get upstream value s : Hello_value1 and get down stream value from context ctx.get() : World_context_value and concat both & return result
                                .contextWrite(ctx -> ctx.put(key, "World_context_value"));//1. set value key : message , value : World_context_value

        StepVerifier.create(r)
                .expectNext("Hello_value1 World_context_value")
                .verifyComplete();

        //execution order 2 -> 1 coz subscription signal flows from bottom to top
    }

    /** https://projectreactor.io/docs/core/release/reference/#_simple_context_examples
     *
     * In your chain of operators, the relative positions of where you write to the Context and where you read from it matters.
     * The Context is immutable and its content can only be seen by operators above it, as demonstrated in the following example:
     */
    @Test
    public void test2() {
        String key = "dummy_key";

        Mono<String> mono = Mono.just("Hello")
                .contextWrite(context -> context.put(key, "World"))//2
                .flatMap(s -> Mono.deferContextual(contextView -> Mono.just(s + " " + contextView.getOrDefault(key, "Stranger"))));//1

        StepVerifier.create(mono)
                .expectNext("Hello Stranger")
                .verifyComplete();
    }


    /**
     * In the preceding example, the Context is populated with "World" during subscription.
     * Then the subscription signal moves upstream and another write happens.
     * This produces a second immutable Context with a value of "Reactor".
     * After that, data starts flowing. The deferContextual sees the Context closest to it,
     * which is our second Context with the "Reactor" value (exposed to the user as a ContextView).
     */
    @Test
    public void test3() {
        String key = "message";
        Mono<String> r = Mono
                .deferContextual(ctx -> {
                    //OPTIONAL : get context map and print all KV
                    ctx.stream().forEach(objectObjectEntry -> {
                        System.out.println("key1 = " + objectObjectEntry.getKey());
                        System.out.println("value = " + objectObjectEntry.getValue());
                    });
                    return Mono.just("Hello " + ctx.get(key));
                })//3rd finally it will get the 2nd update
                .contextWrite(ctx -> ctx.put(key, "Reactor"))//2nd update, so this is the latest update for that key and new immutable context instance created
                .contextWrite(ctx -> ctx.put(key, "World"));//1st update happen , after subscription

        StepVerifier.create(r)
                .expectNext("Hello Reactor")
                .verifyComplete();

    }

    /**
     *The reason is that the Context is associated to the Subscriber and
     * each operator accesses the Context by requesting it from its downstream Subscriber.
     */
    @Test
    public void test4() {
        String key = "message";
        Mono<String> r = Mono
                .deferContextual(ctx -> Mono.just("Hello " + ctx.get(key)))//3 : it will get data from 2nd write (The top context read sees second write.)
                .contextWrite(ctx -> ctx.put(key, "Reactor"))//2 this is second write to happen
                .flatMap( s -> Mono.deferContextual(ctx -> Mono.just(s + " " + ctx.get(key))))//4 it will get data data from 1st write (The flatMap concatenates the result from initial read with the value from the first write)
                .contextWrite(ctx -> ctx.put(key, "World"));//1 : this is first write to happen

        StepVerifier.create(r)
                .expectNext("Hello Reactor World")
                .verifyComplete();
    }

    /**
     * In the preceding example, the final emitted value is "Hello World Reactor" and not "Hello Reactor World",
     * because the contextWrite that writes "Reactor" does so as part of the inner sequence of the second flatMap.
     * As a consequence, it is not visible or propagated through the main sequence and the first flatMap does not see it.
     * Propagation and immutability isolate the Context in operators that create intermediate inner sequences such as flatMap.
     */
    @Test
    public void test5() {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap( s -> Mono
                        .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key)))//Q. uses P value : Hello(from upstream) + World (from downstream)
                )
                .flatMap( s -> Mono
                        .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key)))//B. uses A value : Hello World(from upstream) + Reactor(from downstream)
                        .contextWrite(ctx -> ctx.put(key, "Reactor"))//A. : it will only impact internal to flatmap
                )
                .contextWrite(ctx -> ctx.put(key, "World"));//P. : it is outside flatmap so it will go up where we need value

        StepVerifier.create(r)
                .expectNext("Hello World Reactor")//output B flatmap, since that flatmap is close to this subscriber
                .verifyComplete();
    }

}
