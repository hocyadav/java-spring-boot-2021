package io.hari.java9to16feature.TypeInference;

import java.util.List;
import java.util.stream.Stream;

public class TypeInferenceVar {
    public static void main(String[] args) {
        //left side is hard to read, we can use var, var can be used inside for loop, var can not be null

        List<String> list1 = List.of("hari", "om", "yadav");
        List<String> list2 = List.of("omp", "chandan");

//        List<List<String>> list = List.of(list1, list2);
        var list = List.of(list1, list2);
        list.stream().forEach(System.out::println);


        for (var i = 0; i < 4; i++) {
            System.out.println("i = " + i);
        }

        for (var val : list) {
            System.out.println("val = " + val);
        }

        //var used in chain to make more readable
        List.of(1,2,3,4,5,6,7,8,9,10).stream().filter(i -> 1 % 2 == 0).forEach(System.out::println);

        var filter = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().filter(i -> 1 % 2 == 0);
        filter.forEach(System.out::println);
    }
}
