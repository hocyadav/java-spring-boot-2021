package io.hari.java9to16feature.predicateNot;

import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        //m1
        System.out.println("------even------");
        list.stream().filter(evenNumber).forEach(System.out::println);//even number
        System.out.println("------odd------");
        list.stream().filter(evenNumber.negate()).forEach(System.out::println);//odd number

        //m2
        System.out.println("------even------");
        list.stream().filter(PredicateTest::isEven).forEach(System.out::println);//even number
        System.out.println("------odd------");
        list.stream().filter(Predicate.not(PredicateTest::isEven)).forEach(System.out::println);//odd number
    }

    public static boolean isEven(Integer i) {
        return i % 2 == 0;
    }
}
