package io.hari.java9to16feature.streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        System.out.println("-------iterate--------");
        //java 8
//        Stream.iterate(1, i -> i + 1).forEach(System.out::println);//infinite
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
        System.out.println("---------------");
        //java 9 : no need to use limit, its like for loop
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::println);

        System.out.println("-------takeWhile, dropWhile--------");
        // takeWhile, dropWhile
        List<Integer> list = List.of(10, 20, 12, 14, 5, 6, 7, 8, 9, 10);
        list.stream().takeWhile(i -> i % 2 == 0).forEach(System.out::println);//print till matching

        System.out.println("---------------");
        list.stream().dropWhile(i -> i % 2 == 0).forEach(System.out::println);//negate of above
    }
}
