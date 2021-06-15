package io.hari.javareactiveframework;

import lombok.SneakyThrows;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;

/**
 * @author Hariom Yadav
 * @since 15/06/21
 */
public class ReactiveStream_Filter_Map_andOthers {

    @Test
    public void testFilter() {
        Flux<String> flux = Flux.just("hari", "om", "omp", "rajat", "chandan", "sanjay");
        flux.filter(data -> data.length() < 4)
                .log()
                .subscribe(
                        data -> System.out.println("DATA : "+data)
                );
    }


    @Test
    public void testMap() {
        Flux.range(1, 4)
                .log()
                .map(data -> data * data)//sync operation
                .subscribe(
                        data -> System.out.println("DATA : "+data)
                );

    }


    @Test
    public void testFlatMap() {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .flatMap(id -> dbCall(id))//aync operation
                .log()
                .subscribe(
                        data -> System.out.println("DATA : "+data)
                );
    }


    @Test
    public void testFlatMap2() {
        Flux<String> stringFlux = Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .window(2)
                .flatMap(identifiers -> identifiers.flatMap(id -> dbCall(id))
                        .subscribeOn(Schedulers.parallel()))
                .log();

        stringFlux.subscribe(
                        data -> System.out.println("DATA : "+data)
                );
    }

    @SneakyThrows
    private Mono<String> dbCall(Integer id) {
        Map<Integer, String> map = Map.of(1, "hari");
        Thread.sleep(2000);
        return Mono.just(map.getOrDefault(id, "Not found"));
    }


    @Test
    public void flatMapTest() {//working
        Flux.fromIterable(List.of("1", "2", "3", "4", "5", "6", "7", "8")) //Flux<String>
                .flatMap(id -> getEmployeeDetails(id)) //DB or external service call that return a flux or Mono// Mono<String>
                .log()
                .subscribe(
                        data -> System.out.println("..." + data)
                );
    }


    @Test
    public void flatMapTest2() {//working..?
        Flux.fromIterable(List.of("1", "2", "3", "4", "5", "6", "7", "8")) //Flux<String>
                .window(2)
                .flatMap(it -> it.flatMap( id -> getEmployeeDetails(id)))
                .log()
                .subscribe(
                        data -> System.out.println("..." + data)
                );
    }

    @Test
    public void flatMapTest3() {
        Flux.fromIterable(List.of("1", "2", "3", "4", "5", "6", "7", "8")) //Flux<String>
                .window(2)
                .flatMap(it -> it.flatMap( id -> getEmployeeDetails(id)).subscribeOn(Schedulers.parallel()))
//                .subscribeOn(Schedulers.parallel())
                .log()
                .subscribe(
                        data -> System.out.println("..." + data)
                );
    }

    //Mock DB or external service
    @SneakyThrows
    private Mono<String> getEmployeeDetails(String id)  {
        Map<String, String> employees = Map.of("1", "hari");
        Thread.sleep(2000);
        return Mono.just(employees.getOrDefault(id, "Not found"));
    }


}
