package com.example.demojava.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HariomYadav
 * @since 02/12/20
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println("test");
        List<String> list = Arrays.asList("aaq","bb", "zzqqq", "cc");
        System.out.println("list = " + list);
        list.sort(Comparator.comparing(String::length));
        System.out.println("list = " + list);

        List<Integer> list1 = Arrays.asList(1, 2);
        final List<Integer> collect = list1.stream().limit(0).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
