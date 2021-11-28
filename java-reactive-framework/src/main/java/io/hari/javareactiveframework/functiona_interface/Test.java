package io.hari.javareactiveframework.functiona_interface;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@Builder
class MyEntity {
    String name;

    public boolean foo() {
        return getMyEntityPredicate__()
                .test(MyEntity.builder().name("chandan").build());
    }

    public boolean foo2() {//good way 1
        return getMyEntityPredicate__()
                .test(this);//pass input type
    }

    //good way 2
    public boolean foo3() {//good way
        //step 1: create predicate using input as Entity type + write logic
        Predicate<MyEntity> myEntityPredicate = myEntity -> //input
                myEntity.getName().length() > 5;
        return myEntityPredicate.test(this);//pass input type
    }

    //by writing this seperate predicate we can pass inside the stream/reactor pipeline in filter operator
    public static Predicate<MyEntity> getMyEntityPredicate__() {//step 1: create predicate using input as Entity type + write logic
        return myEntity -> //input
                myEntity.getName().length() > 5;//logic
    }
}

@Data
@Builder
@NoArgsConstructor
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


        System.out.println("=================");

        Predicate<String> stringPredicate = input -> input.length() > 5;
        System.out.println(stringPredicate.test("hariom"));//pass input to predicate as string , this will be the input for the above right side logic

        Predicate<MyEntity> stringPredicate2 = myEntity -> myEntity.getName().length() > 5;
        System.out.println(stringPredicate2.test(MyEntity.builder().name("hariom yadav").build()));

        //extract to predicate method, + pass input type of predicate, here its MyEntity
        System.out.println(getMyEntityPredicate().test(MyEntity.builder().name("hariom yadav").build()));

        System.out.println("foo = " + foo());

        //m1
        MyEntity myEntity = MyEntity.builder().name("iu").build();
        boolean foo = myEntity.foo2();
        System.out.println("foo2 = " + foo);

        //m2 directly call predicate method and then call test() and pass argument type and it will return true or false
        boolean test = myEntity.getMyEntityPredicate__().test(myEntity);//looking bad :P
        System.out.println("test = " + test);

        //m3 static style
//        boolean test1 = MyEntity.getMyEntityPredicate().test(myEntity);
//        System.out.println("test1 = " + test1);

        List.of(MyEntity.builder().name("golu").build(), MyEntity.builder().name("chandan").build())
                .stream()
//                .filter(myEntity11 -> myEntity11.getName().length() > 5)//or
//                .filter(getEntityPredicate())//or
                .filter(myEntity1 -> MyEntity.getMyEntityPredicate__().test(myEntity1))//working
                .forEach(myEntity1 -> {
                    System.out.println("myEntity1 = " + myEntity1);
                });


    }

    public static Predicate<MyEntity> getEntityPredicate() {
        return myEntity1 -> myEntity1.getName().length() > 5;
    }

    //we can write foo() and getMyEntityPredicate() inside MyEntity class
    // and in foo() we can pass "this" in place of  "MyEntity.builder().name("chandan").build()"
    private static boolean foo() {
        return getMyEntityPredicate()
                .test(MyEntity.builder().name("chandan").build());
    }

    private static Predicate<MyEntity> getMyEntityPredicate() {
        return myEntity -> myEntity.getName().length() > 5;
    }

    private static Supplier<String> getStringSupplier() {
        return () -> {
            System.out.println("inside supplier");
            return "hariom yadav";
        };
    }
}
