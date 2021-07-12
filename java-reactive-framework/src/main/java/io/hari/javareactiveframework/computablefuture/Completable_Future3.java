package io.hari.javareactiveframework.computablefuture;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static io.hari.javareactiveframework.computablefuture.Completable_Future1.mostRecentQuestionsAbout;

public class Completable_Future3 {

    @SneakyThrows
    @Test
    public void test1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAbout("java"));
        CompletableFuture<Integer> futureInteger = future.thenApply(result -> {
            //some operation or method call and return different type
            System.out.println("Thread pool name : " + Thread.currentThread().getName());//this thread pool is same as above Computable Future thread , how ?
            sleep3sec();
            return 123;
        });
        Integer result = futureInteger.get();//blocking
        System.out.println("result = " + result);
    }

    @SneakyThrows
    @Test
    public void test2() {//double wrap problem solution
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> mostRecentQuestionsAbout("java"));
        CompletableFuture<CompletableFuture<Integer>> futureInteger = future.thenApply(result -> method2(result));//method 2 return CF
        sleep3sec();
    }

    private CompletableFuture<Integer> method2(String result) {
        System.out.println("method2 Thread name : "+Thread.currentThread().getName());
        return CompletableFuture.completedFuture(123);
    }

    @SneakyThrows
    private void sleep3sec() {
        TimeUnit.SECONDS.sleep(6);
    }

}
