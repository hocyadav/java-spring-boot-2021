package io.hari.javareactiveframework.core_concept.computablefuture;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static io.hari.javareactiveframework.core_concept.computablefuture.Completable_Future1.executorService;
import static io.hari.javareactiveframework.core_concept.computablefuture.Completable_Future1.mostRecentQuestionsAbout;

public class Completable_Future2 {
    /**
     * Already completed
     */
    @SneakyThrows
    @Test
    public void test1() {
        CompletableFuture<Integer> completedFuture = CompletableFuture.completedFuture(42);
        Integer result = completedFuture.get();//non blocking , coz already completed
        System.out.println("result = " + result);
    }

    /**
     * Build in thread pool(Fork Join Pool), not using our own thread pool/executor service
     * open CompletableFuture class and see static method commonPool() that return Fork Join Pool
     */
    @SneakyThrows
    @Test
    public void test2() {
        Supplier<String> supplier = () -> mostRecentQuestionsAbout("java");//my method call -> wrap with supplier
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);//send my supplier -> non blocking call

        String result = future.get();//blocking
        System.out.println("future.get = " + result);
    }

    /**
     * using our own thread pool/executor service ==> equivalent to submit()
     */
    @SneakyThrows
    @Test
    public void test3() {
        System.out.println("Client/Main Thread name: "+Thread.currentThread().getName());
        Supplier<String> supplier = () -> mostRecentQuestionsAbout("java");//my method call -> wrap with supplier
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executorService);//send my supplier + my own thread pool
//        Future<String> future = CompletableFuture.supplyAsync(supplier, executorService);//CompletableFuture is implementation of Future interface

        String result = future.get();//blocking
        System.out.println("future.get = " + result);
    }

}
