package io.hari.javareactiveframework.core_concept.computablefuture;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

public class Completable_Future1 {
    public static ExecutorService executorService = Executors.newFixedThreadPool(5);
    @Test
    public void test1() {
        String recentQuestionsAbout = mostRecentQuestionsAbout("java");//direct function call
        System.out.println("recentQuestionsAbout = " + recentQuestionsAbout);
    }

    @SneakyThrows
    @Test
    public void test2() {
        Callable<String> callable = () -> mostRecentQuestionsAbout("java");
        Future<String> future = executorService.submit(callable);//function call via thread pool/executor service

        System.out.println("future = " + future);//non blocking : we can see output fast
        String result = future.get();//blocking operation
        System.out.println("future.get() = " + result);
    }

    @SneakyThrows
    @Test
    public void test3() {
        Callable<String> callable1 = () -> mostRecentQuestionsAbout("java");
        Future<String> future1 = executorService.submit(callable1);

        Callable<String> callable2 = () -> mostRecentQuestionsAbout("java");
        Future<String> future2 = executorService.submit(callable2);

        //use case : i want one future to execute first and get result and dont care about the other one
        //this we cant achieve using Future, use Completable future
        //work around 1: use while loop and check for every milisecond it is completed or not
        //work around 2: call get and check which one has arrive
        //work around 3: Executor Completion Service : it takes bunch of futures
        future1.get(1, TimeUnit.MILLISECONDS);
        future2.get(1, TimeUnit.MILLISECONDS);
    }
    @SneakyThrows
    public static String mostRecentQuestionsAbout(String str) {
        System.out.println("My Method Thread name : "+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        return Faker.instance().address().fullAddress();
    }
}
