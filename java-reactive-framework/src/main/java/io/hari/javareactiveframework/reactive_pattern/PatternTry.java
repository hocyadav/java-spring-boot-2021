package io.hari.javareactiveframework.reactive_pattern;

import lombok.Data;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class PatternTry {
    @SneakyThrows
    public static void main(String[] args) {

        //callable
        //static prefix 's'
        //string buffer

        //pipeline -> log -> pipeline -> log -> then() pipeline


        Callable<String> callable = () -> {
            //some processing
            //return result
            return "input string";
        };

        //m1 same as m2 but it throw exception
        Callable<String> threadNameAndDate1 = () -> {
            String thread = "   [" + Thread.currentThread().getId() + "] ";
            Date date = new Date();
            return thread + date + " - ";
        };

        //m2, simple logic & we know it will not throw exception
        Supplier<String> threadNameAndDate = () -> {
            String thread = "   [" + Thread.currentThread().getId() + "] ";
            Date date = new Date();
            return thread + date + " - ";
        };


        final StringBuffer sbLog = new StringBuffer(threadNameAndDate.get() + "declared sb " + "\n");

        //pipeline start
        Mono<Void> voidMono = Mono.fromCallable(callable)
                .doOnSuccess(s -> sbLog.append("   pipeline start time " + new Date() + "\n"))
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.single())
                //log pipeline intermediate result
                .doOnSuccess(s -> {
                    if (1==1) throw new RuntimeException("oops--");//down pipeline will not run, skip and directly go to the subscribe method
                    //add date in sb log
                    //add common thread variable 'threadNameAndDate'
                    Date date = new Date();
//                    OffsetDateTime date = OffsetDateTime.now();
                    sbLog.append(threadNameAndDate.get() + " - upstream pipeline result 1 : " + s + "\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                //pipeline continue
                .map(String::toLowerCase)
                .doOnSuccess(s -> sbLog.append(threadNameAndDate.get() + " - upstream pipeline result 2 : " + s + "\n"))
                //above pipeline execute & return async signal void
                .doOnNext(s -> {
                    throw new RuntimeException("oops");
                })
//                .onErrorContinue((throwable, o) -> {
//
//                })
//                .onErrorStop()
                .onErrorMap(throwable -> {//take above error and modify and send different exception error
                    throw new RuntimeException("new run time");
                })
                .onErrorResume(throwable -> {//take above error and send default mono [DTO with failed response]
                    sbLog.append("  error -- "+throwable.getLocalizedMessage() +"\n");
                    return Mono.just("exception response");
                })
                .then();

        //subscribe and print final log line
        voidMono.subscribe(item -> System.out.println("item = " + sbLog),
                error -> {
                    sbLog.append("    pipeline end time " + new Date() + "\n");
                    System.out.println("error = " + sbLog.append(threadNameAndDate.get() + " -  error :: " + error.getLocalizedMessage()));
                },
                () -> {
                    sbLog.append("    pipeline end time " + new Date() + "\n");
                    System.out.println("sbLog = " + sbLog);
                });

        Thread.sleep(2000);//waiting for async processing to complete

        //print log outside when all processing done
        System.out.println(("[" + Thread.currentThread().getId() + "] ") +
                new Date() +
                " - printing log :: \n" + sbLog.toString());


        //EXTRA : we can add method name like fetchElasticSearcgPipeline() {mono/flux pipeline} , we can add similar to [thread name]
        //useful for to go to method body by just looking log line... cool idea
        //EXTRA : we can add start and end time, to know when started and when completed or when it throw error.. not time difference

        //Another solution, use zipkin


        //note:
        //convert any upstream exception to different exception : use onError map
        //convert any upstream exception to different pipeline/default pipeline/default dto : use on error resume
        //convert any upstream exception to not break pipeline [in case of flux] : use on error continue
    }
}
