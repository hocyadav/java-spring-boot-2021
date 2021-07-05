package io.hari.javareactiveframework;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Util {
    private static final String DUMMY_NAME = Faker.instance().name().fullName();

    public static Consumer<String> getNextData() {
        return data -> System.out.println("Received = " + data);
    }

    public static String getNameString() {
        return DUMMY_NAME;
    }
}
