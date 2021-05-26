package com.hari.jpa1.stream;

import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

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
    }

    @Test
    public void nullTest() {
        requiredNotNull_Method1(null);
    }

    @Test
    public void nullTest2() {
        lombokTest_Method2(null);
    }

    private void requiredNotNull_Method1(String name) {
//        Objects.requireNonNull(name);//simple check
        Objects.requireNonNull(name, "fail fast : name value is null");//check with own msg
        System.out.println(name + " yadav");
    }

    public void lombokTest_Method2(@NonNull String name) {
        System.out.println(name + " yadav");
    }

    @Test
    public void testListNullCheck() {
        listNullCheck();
    }

    public void listNullCheck() {//throw null pointer exception and not run
        List<String> list = null;
        Objects.requireNonNull(list);//this is like null check
        //normal stream operation
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testNullSafeStream() {
        nullSafeStream();
    }

    //goal : convert List string -to-> stream of string (i.e. collection of object to stream of object + NULL check)
    //here we are converting list -> stream type with null check
    public void nullSafeStream() {//https://www.baeldung.com/java-null-safe-streams-from-collections
        List<String> list = null;

        final Stream<String> stringStream = Optional.ofNullable(list)
                .map(Collection::stream).orElseGet(Stream::empty);

        stringStream.forEach(System.out::println);
    }

    @Test
    public void testStreamListDoubleCheck() {
        streamListDoubleCheck();
    }

    public void streamListDoubleCheck() {
        List<String> list = Arrays.asList("hariom", null);

        final Stream<String> stringStream = Optional.ofNullable(list)
                .map(Collection::stream).orElseGet(Stream::empty)//this is null check for collection(this step we can remove if we know input list is not null)
                .filter(Objects::nonNull);//this is null check for collection inside Object

        stringStream.forEach(System.out::println);
    }
}
