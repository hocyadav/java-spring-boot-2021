package io.hari.javareactiveframework;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                        data -> System.out.println("DATA :"+data)
                );
    }

    @Test
    public void testMono_m1() {
        Mono.error(new Exception("my exception1"))
                .log()
                .subscribe(
                        data -> System.out.println("DATA :"+data),
                        error -> System.out.println("ERROR :"+error)
                );
    }

    @Test
    public void testMono_m2() {
        Mono.error(new Exception("my exception2"))
//                .log()
                .doOnError(error -> System.out.println("ERROR : "+error))
                .subscribe(
                        data -> System.out.println("DATA :"+data)
//                        error -> System.out.println("ERROR.. :"+error)
                );
    }




}
