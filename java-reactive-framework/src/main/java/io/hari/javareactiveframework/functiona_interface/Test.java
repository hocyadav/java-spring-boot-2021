package io.hari.javareactiveframework.functiona_interface;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Data @Builder @NoArgsConstructor
class Entity {

    public void fromSupplier(Supplier<String> stringSupplier) {
        System.out.println("fromSupplier name = " + Thread.currentThread().getName());
        String getDataFromSupplier = stringSupplier.get();
        System.out.println("getDataFromSupplier = " + getDataFromSupplier);
    }

    public Entity fromSupplier2(Supplier<String> stringSupplier) {
        System.out.println("fromSupplier name = " + Thread.currentThread().getName());
        String getDataFromSupplier = stringSupplier.get();
        System.out.println("getDataFromSupplier = " + getDataFromSupplier);
        return this;
    }


    public void withoutSupplier(String simpleStr) {
        System.out.println("withouySupplier name = " + Thread.currentThread().getName());
        System.out.println("simpleStr = " + simpleStr);
    }

    @SneakyThrows
    public Entity fromCallable(Callable<String> stringCallable) {
        System.out.println("fromCallable name = " + Thread.currentThread().getName());
        String call = stringCallable.call();
        System.out.println("call = " + call);
        return this;
    }
}

public class Test {
    public static void main(String[] args) {
        Entity entity = new Entity();

        entity.withoutSupplier("omprakash yadav");

        Supplier<String> stringSupplier = () -> "hariom yadav";
        entity.fromSupplier(stringSupplier);

        //??
        Supplier<String> stringSupplier2 = getStringSupplier();
        entity.fromSupplier(stringSupplier2);

        Callable<String> stringCallable = () -> "chandan";
        entity.fromCallable(stringCallable);

        //create own pipeline
        entity.fromSupplier2(stringSupplier)
                .fromCallable(stringCallable);
    }

    private static Supplier<String> getStringSupplier() {
        return () -> {
            System.out.println("inside supplier");
            return "hariom yadav";
        };
    }
}
