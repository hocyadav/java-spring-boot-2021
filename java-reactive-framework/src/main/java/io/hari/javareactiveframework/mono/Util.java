package io.hari.javareactiveframework.mono;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {

    public static Consumer<Object> getOnNext() {
        return data -> System.out.println("Received = " + data);
    }

    public static Consumer<Throwable> getOnError() {
        return error -> System.out.println("ERROR = " + error.getMessage());
    }

    public static Runnable getOnComplete() {
        return () -> System.out.println("Completed..send emails");
    }

    public static String getFakeName() {
        return Faker.instance().name().fullName();
    }
}
