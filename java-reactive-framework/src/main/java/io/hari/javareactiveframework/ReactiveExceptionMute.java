package io.hari.javareactiveframework;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveExceptionMute {

    @Test
    public void noStacktrace(){
        Mono.just("Hello")
                .map(s -> s.charAt(10)) // This will throw an IndexOutOfBoundsException
                // based on exception type, log error
                .doOnError(IndexOutOfBoundsException.class, e -> log.error("IndexOutOfBoundsException occurred 123: " + e.getMessage()))
                // based on exception type, return empty to mute exception, and it will not print whole stacktrace
                .onErrorResume(IndexOutOfBoundsException.class, e -> Mono.empty())
                // downstream
                .subscribe(System.out::println, e -> log.error("Error 123: " + e.getMessage()));
    }




    @Test
    public void stacktrace(){
        Mono.just("Hello")
                .map(s -> s.charAt(10)) // This will throw an IndexOutOfBoundsException
                // based on exception type, log error
                .doOnError(IndexOutOfBoundsException.class, e -> log.error("IndexOutOfBoundsException occurred 123: " + e.getMessage()))
                // based on exception type, return empty to mute exception, and it will not print whole stacktrace
//                .onErrorResume(IndexOutOfBoundsException.class, e -> Mono.empty())

                //m1 downstream, consumer handle exceptio, so no stacktrace
//                .subscribe(System.out::println, e -> log.error("Error 123: " + e.getMessage()));
                //m2 downstream , consumer not handle exception so stacktrace, only success is handle here
                .subscribe(System.out::println);

    }
}
