package com.hari.jpa1.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
public class StreamListAddTest {

    @Test
    public void test() {
        listAdd();
        listContain();
        extracted();
    }

    private void listContain() {
        List<String> inputList = Arrays.asList("om", "chandan");

        final List<String> collect = Stream.of("om", "chandan", "neha", "rajat", "hari", "omp")
                .filter(inputList::contains)//if stream list present in input list then collect
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    private void extracted() {
        List<Object> list = new LinkedList<>();
        list.add(1);
        list.add("hariom");
    }

    private void listAdd() {
        List<String> result = new LinkedList<>();
        Stream.of("hari", "om", "yadav")
                .forEach(result::add);
        System.out.println("result = " + result);

    }
}
