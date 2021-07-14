package io.hari.javareactiveframework.reactive_context.josh_simple;

import brave.Tracer;
import brave.propagation.TraceContext;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Log4j2
@RestController
@SpringBootApplication
public class SimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    private static Scheduler SCHEDULER = Schedulers.fromExecutor(Executors.newFixedThreadPool(10));

    @GetMapping("/data")
    Mono<String> getData() {
//        return Flux_publisher_PublishData();//working
        return monoPublishData();//working
    }

    //todo : get context object in MONO pipeline :
    // M1: Approach using doOnEach -> signal -> get context -> get trace id/span id
    //M2 : using deferContextual : give contextView object
    Mono<String> monoPublishData() {
        return Mono.just("A")
                .doOnEach(new Consumer<Signal<String>>() {
                    @Override
                    public void accept(Signal<String> stringSignal) {
                        if (!stringSignal.isOnNext()) return;
                        Context context = stringSignal.getContext();

                        String traceId = captureTraceId(context);
                        System.out.println("traceId = " + traceId);


                        context.stream().forEach(objectObjectEntry -> {
                            Object key = objectObjectEntry.getKey();
                            System.out.println(key.getClass().getName());
                            System.out.println("objectObjectEntry.getKey() = " + key);
                            System.out.println("objectObjectEntry.getValue() = " + objectObjectEntry.getValue());

                            if (key instanceof org.springframework.cloud.sleuth.TraceContext) {//Not working
                                System.out.println("instance of TC");
                            }
                            if (key instanceof TraceContext) {//not working
                                System.out.println("instance of TC 2");
                            }

                        });
                        log.info("inside mono doOnEach - printing trace id and span id : WORKING");
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
                            return Mono.just(s);
                        }
                ));
    }

    //todo : get context object in FLUX stages  :  Approach using doOnEach -> signal -> get context -> get trace id/span id
    Flux<String> Flux_publisher_PublishData() {//publisher class with multiple stages
        Flux<String> letters = Flux.just("A", "B", "C");
        Flux<String> letters_wrapper = prepare_wrapperFluxPrintLogAndRunInThreadPool(letters);

        Flux<Integer> numbers = Flux.just(1, 2, 3);
        Flux<Integer> numbers_wrapper = prepare_wrapperFluxPrintLogAndRunInThreadPool(numbers);

        Flux<String> combine = Flux.zip(letters_wrapper, numbers_wrapper).map(tuple -> tuple.getT1() + ':' + tuple.getT2());
        Flux<String> combine_wrapper = prepare_wrapperFluxPrintLogAndRunInThreadPool(combine);

        Flux<String> stringFlux = combine_wrapper//2.a for each flux item we are calling doONEach and in that we are getting Signal and then context object and then key userid and logging
                //creating doOnEach for only OnNext signal
                .doOnEach(signal -> {//2. this doOnEach() will be applied to each flux item above
                    //approach :
                    // 1. check for input is signal onNext,onError,onCoplete signal
                    //2. get onNext signal -> get onNext context(its a map) from signal object
                    //3. get map key-value -> logging or do some other operation
                    if (!signal.isOnNext()) {//if other than onNext dont do anything just return
                        return;
                    }
                    //get Context obj(map) -> get k-v from map(context)
                    Context context = signal.getContext();//1.b. IMP NOTE : before executing this 1 will execute and then upstream 2.a will use this immutable context so that all above upstream 2.a will see same k-v

                    //todo DONE : get all KV from map : we are able to see all data traceid, span id
//					printAllKV_usingStream1(context);

                    //todo : M1 : get TraceContext and Tracer object : NOT working
//					getKeyClass(context);

                    //todo M2 : iterate map and get
                    printAllKV_usingStream(context);

                    Object userId = context.get("userId");
                    //do operation
                    log.info("user id for this pipeline stage for data '" + signal.get() + "'  is '" + userId + "'");
                })
                .subscriberContext(Context.of("userId", UUID.randomUUID().toString()));//1. this will be used to upstream to get userid value from Context object

        //todo : outside flux :  print trace id using MDC , not working
        System.out.println("-- X-B3-TraceId = " + MDC.get("X-B3-TraceId"));
        System.out.println("-- X-B3-SpanId = " + MDC.get("X-B3-SpanId"));

        Flux<String> stringFlux1 = stringFlux
                .flatMap(upstreamData -> Mono.deferContextual(contextView -> {
                    Object traceContext = contextView.getOrDefault(TraceContext.class, null);
                    System.out.println("traceContext = " + traceContext);

                    //todo :inside flux : print trace id using MDC , not working
                    //not working , since value is not present
                    System.out.println("X-B3-TraceId = " + MDC.get("X-B3-TraceId"));
                    System.out.println("X-B3-SpanId = " + MDC.get("X-B3-SpanId"));

                    return Mono.just("downstream data : test " + " upstream data : " + upstreamData);
                }));

        return stringFlux;
    }

    //wrapper class to print flux publisher data
    private static <T> Flux<T> prepare_wrapperFluxPrintLogAndRunInThreadPool(Flux<T> in) {//wrapper/transformer class for flux, take flux return flux
        Flux<T> extraWrapperOverAnyFlux = in//1. upstream
                .doOnNext(log::info)//print data : thread name + content //2. for upstream this is subscriber -> and its printing content-data
                .subscribeOn(SCHEDULER);//run on different thread//3. for upstream its running in separate thread pool
        return extraWrapperOverAnyFlux;
    }

    // -------------- utility -----------------
    private void printAllKV_usingStream(Context context) {
        context.stream().forEach(objectObjectEntry -> {
            Object key = objectObjectEntry.getKey();
            Object value = objectObjectEntry.getValue();
            System.out.println("key = " + key);
            System.out.println("value = " + value);
        });
    }

    private void getKeyClass(Context context) {//not working
        org.springframework.cloud.sleuth.TraceContext traceContext = context.get(org.springframework.cloud.sleuth.TraceContext.class);
        Tracer tracer = context.get(Tracer.class);
        System.out.println("traceContext = " + traceContext.toString());
        System.out.println("tracer = " + tracer.toString());
    }

    private void printAllKV_usingStream1(Context context) {//working
        List<Map.Entry<Object, Object>> allKV = context.stream().collect(Collectors.toList());
        System.out.println("allKV = " + allKV);
        //output allKV =
        // [interface org.springframework.cloud.sleuth.TraceContext=30bf5a3d2288e2b1/30bf5a3d2288e2b1,
        // interface org.springframework.cloud.sleuth.Tracer=org.springframework.cloud.sleuth.brave.bridge.BraveTracer@7b6bfef6,
        // interface org.springframework.cloud.sleuth.CurrentTraceContext=org.springframework.cloud.sleuth.brave.bridge.BraveCurrentTraceContext@647f5bcd,
        // userId=b86aabe5-1d1e-4fd3-ac88-c980e6857498]
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
