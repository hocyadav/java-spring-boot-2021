package io.hari.javareactiveframework.core_concept.operator;

import com.github.javafaker.Faker;
import org.junit.Test;
import reactor.core.publisher.Flux;

/** Hariom Yadav : 17/07/21
 *
 * handle = filter + map
 * return type is Object , coz of map operation we can apply any mapper on upstream data
 *
 * limitRange = handler between upstream and downstream/intermediate Queue
 */
public class Handle_n_limitRange {

    @Test
    public void handleSynchronousSink_test0() {
        Flux<Object> flux = Flux.range(1, 20) // whay Object type ?
                .handle((upstreamData, synchronousSink) -> {
                    synchronousSink.next(upstreamData);//1. pass all data to downstream
                });
        flux.subscribe(
                data -> System.out.println("data = " + data),
                error -> System.out.println("error.getMessage() = " + error.getMessage()),
                () -> System.out.println("DONE Signal")
        );
    }

    @Test
    public void handleSynchronousSink_test1() {
        Flux<Object> flux = Flux.range(1, 20)
                .handle((upstreamData, synchronousSink) -> {
                    if (upstreamData % 2 == 0)//filter
                        synchronousSink.next(upstreamData);
                    else
                        synchronousSink.next(upstreamData + " map operation");//map
                    //this makes Object type coz we are applying mapper and above one is filter that will send original data
                });
        flux.subscribe(
                data -> System.out.println("data = " + data),
                error -> System.out.println("error.getMessage() = " + error.getMessage()),
                () -> System.out.println("DONE Signal")
        );
    }

    @Test
    public void handleSynchronousSink_test2() {
        //1. generate give synchronous sync -> it will give object type + internally it will run in for loop infinite times
        Flux<Object> flux = Flux.generate(synchronousSink1 -> synchronousSink1.next(Faker.instance().country().name()))
                .map(Object::toString)//object type to string
                .handle((upstreamData, synchronousSink) -> {
                    synchronousSink.next(upstreamData);
                    if (upstreamData.toLowerCase().equals("india"))
                        synchronousSink.complete();
                });//synchronous type gives object type output
        flux.subscribe(
                data -> System.out.println("data = " + data),
                error -> System.out.println("error.getMessage() = " + error.getMessage()),
                () -> System.out.println("DONE Signal")
        );
    }


    @Test
    public void limitRange_test() {
        //1st request 100
        //downstream get 75 data , then 2nd request 75
        //downstream get 150 data, next request 75
        //downstream get 225 data, next request 75
        //downstream get 300 data, --""---
        //....
        Flux.range(1, 1000)
                .log()
                .limitRate(100)//this is handler/Queue in between upstream and downstream..
                .subscribe(
                        data -> System.out.println("data : "+data),
                        error -> System.out.println("error.getMessage() = " + error.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }

    @Test
    public void limitRange_test2() {
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 99)// 99% data consumer then send 99 as next request
                .subscribe(
                        data -> System.out.println("data : "+data),
                        error -> System.out.println("error.getMessage() = " + error.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }

    @Test
    public void limitRange_test3() {
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 0)// 100% data consumer then send 100 as next request
                .subscribe(
                        data -> System.out.println("data : "+data),
                        error -> System.out.println("error.getMessage() = " + error.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }

    @Test
    public void limitRange_test4() {
        Flux.range(1, 1000)
                .log()
                .limitRate(101, 0)// 100% data consumer i.e. 101 data consume downstream then send 101 as next request
                .subscribe(
                        data -> System.out.println("data : "+data),
                        error -> System.out.println("error.getMessage() = " + error.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
}
