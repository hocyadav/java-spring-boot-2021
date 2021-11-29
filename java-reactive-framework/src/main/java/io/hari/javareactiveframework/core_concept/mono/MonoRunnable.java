package io.hari.javareactiveframework.core_concept.mono;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

public class MonoRunnable {
    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("some time consuming operation");
        Runnable runnable2 = getSomeTimeConsumingOperation();
        Mono.fromRunnable(runnable)
                .subscribe(
                        item -> System.out.println("item = " + item),//no data from upstream, only we will get complete signal
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),//in case if we get error
                        () -> System.out.println("complete event from time consuming task - sending mail"));//complete signal: this is also runnable


        Mono.fromRunnable(getSomeTimeConsumingOperation())
                .doOnNext(o -> System.out.println("o = " + o))//runnable will not send any data so it will not execute
                .doOnSuccess(o -> System.out.println("o = " + o))//on success it will execute but no data so it will print null
                .subscribe(
                        item -> System.out.println("item = " + item),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("complete event from time consuming task - sending mail"));

        //m1
        Mono.fromRunnable(() -> deleteFile())//after delete file send some signal - email or event
                .subscribe(item -> System.out.println("item = " + item),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("done"));//send some event

        //m2
        Mono.fromRunnable(() -> deleteFile()).block();
        System.out.println("send mail");
    }

    @SneakyThrows
    private static void deleteFile() {
        System.out.println("deleting file STARTED");
        Thread.sleep(2000);
        System.out.println("deleting file DONE");

    }

    private static Runnable getSomeTimeConsumingOperation() {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("some time consuming operation");
        };
    }


}
