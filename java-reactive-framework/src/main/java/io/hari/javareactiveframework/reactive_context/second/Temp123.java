package io.hari.javareactiveframework.reactive_context.second;

import lombok.SneakyThrows;

/**
 * @author Hariom Yadav
 * @since 14/04/23
 */
public class Temp123 {


    @SneakyThrows
    public static void foo() {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
    }
}
