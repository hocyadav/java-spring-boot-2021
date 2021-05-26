package com.hari.jpa1.java_methods;

import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @Author Hariom Yadav
 * @create 26-05-2021
 * https://stackoverflow.com/questions/45632920/why-should-one-use-objects-requirenonnull/45632988
 */
public class Objects_requiredNonNull {
    @Test
    public void test() {
        requiredNotNull_Method1("hariom");
        lombokTest_Method2("hariom");

        lombokTest_Method2(null);
        requiredNotNull_Method1(null);
    }

    private void requiredNotNull_Method1(String name) {
//        Objects.requireNonNull(name);//simple check
        Objects.requireNonNull(name, "fail fast : name value is null");//check with own msg
        System.out.println(name + " yadav");
    }

    public void lombokTest_Method2(@NonNull String name) {
        System.out.println(name+" yadav");
    }
}
