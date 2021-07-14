package io.hari.javareactiveframework.reactive_context.temp;

import brave.propagation.TraceContext;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

import java.util.concurrent.Callable;
import java.util.function.Consumer;


@Log4j2
@RestController
@SpringBootApplication
public class SimpleApplicationTemp {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApplicationTemp.class, args);
    }

    @GetMapping("/data")
    Mono<String> getData() {
        return monoPublishData();
    }

    //todo : get context object in MONO pipeline : (Reactor Context)
    //M1: using doOnEach -> signal -> get context -> get trace id/span id
    //M2: using Mono.deferContextual : give contextView object
    Mono<String> monoPublishData() {
        return Mono.just("A")
                .doOnEach(new Consumer<Signal<String>>() {
                    @Override
                    public void accept(Signal<String> signal) {
                        if (!signal.isOnNext()) return;

                        Context context = signal.getContext();
                        System.out.println("traceId = " + captureTraceId(context));//null ??

                        //printing all context map KV
                        context.stream().forEach(objectObjectEntry -> {
                            Object key = objectObjectEntry.getKey();
                            System.out.println("objectObjectEntry.getKey() = " + key);
                            System.out.println("objectObjectEntry.getValue() = " + objectObjectEntry.getValue());

                            if (key instanceof org.springframework.cloud.sleuth.TraceContext) {//Not working
                                System.out.println("instance of TC");
                            }
                            if (key instanceof TraceContext) {//not working
                                System.out.println("instance of TC 2");
                            }
                        });
                        log.info("inside mono doOnEach - printing trace id and span id : WORKING\n");
                    }
                })
                .flatMap(s -> Mono.deferContextual(contextView -> {
                            contextView.stream().forEach(objectObjectEntry -> {
                                Object key = objectObjectEntry.getKey();
                                System.out.println("f objectObjectEntry.getKey() = " + key);
                                System.out.println("f objectObjectEntry.getValue() = " + objectObjectEntry.getValue());

                                if (org.springframework.cloud.sleuth.TraceContext.class.isInstance(key)) {
                                    System.out.println("class instance of");
                                }
                                if (TraceContext.class.isInstance(key)) {
                                    System.out.println("class ins of");
                                }
                                if (key instanceof org.springframework.cloud.sleuth.TraceContext) {
                                    System.out.println("instance of TC");
                                }
                                if (key instanceof TraceContext) {
                                    System.out.println("instance of TC 2");
                                }
                            });
                            log.info("inside flux deferContextual - printing trace id and span id : WORKING\n");
                            return Mono.just(s);
                        }
                ));
    }

    private String captureTraceId(final Context context) {
        Callable<String> stringCallable = () -> {
            String[] split = context.get(TraceContext.class).toString().split("/");
            String join = StringUtils.join(split, "-");
            return join;
        };
        String orElse = Try.ofCallable(stringCallable).getOrElse((String) null);
        return orElse;
    }
}
/** Output

traceId = null
objectObjectEntry.getKey() = interface org.springframework.cloud.sleuth.TraceContext
objectObjectEntry.getValue() = 0e3804f7cd1a03fb/0e3804f7cd1a03fb
objectObjectEntry.getKey() = reactor.onDiscard.local
objectObjectEntry.getValue() = reactor.core.publisher.Operators$$Lambda$702/0x00000008010e10f0@7337ef8e
2021-07-14 08:33:09.090  INFO [java-reactive-framework,0e3804f7cd1a03fb,0e3804f7cd1a03fb] 5745 --- [ctor-http-nio-2] i.h.j.r.temp.SimpleApplicationTemp       : inside mono doOnEach - printing trace id and span id : WORKING

f objectObjectEntry.getKey() = interface org.springframework.cloud.sleuth.TraceContext
f objectObjectEntry.getValue() = 0e3804f7cd1a03fb/0e3804f7cd1a03fb
f objectObjectEntry.getKey() = reactor.onDiscard.local
f objectObjectEntry.getValue() = reactor.core.publisher.Operators$$Lambda$702/0x00000008010e10f0@7337ef8e
2021-07-14 08:33:09.091  INFO [java-reactive-framework,0e3804f7cd1a03fb,0e3804f7cd1a03fb] 5745 --- [ctor-http-nio-2] i.h.j.r.temp.SimpleApplicationTemp       : inside flux deferContextual - printing trace id and span id : WORKING

* */
