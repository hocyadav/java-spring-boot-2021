package com.hari.jpa1.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 5/14/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Person {
    String name;
    Integer rollNum;
    Address address;
}
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Address {
    String location;
    String state;
    Integer pinCode;
}

public class StreamSorted {

    @Test
    public void test() {
        streamSorted();
    }

    public void streamSorted() {
        final Person person = getPerson("hari", 123, "tulsi theater road", "KA", 560037);
        final Person person2 = getPerson("om", 21, "bangalore", "KA", 560032);
        final Person person3 = getPerson("chandan", 1111, "delhi", "KA", 960057);
        final Person person4 = getPerson("neha", 11, "marathell theater road", "KA", 560030);
        final Person person5 = getPerson("omprakash", 11, "marathelli 2", "KA", 560007);
        final Person person6 = getPerson("asanjay", 11, "patna", "KA", 802220);
        final Person person7 = getPerson("rajat", 235, "delhi", "KA", 495452);

        final List<Person> people = Arrays.asList(person, person2, person3, person4, person5, person6, person7);
        people.stream()
//                .filter(a -> a.getRollNum() != null) //if attribute is null
//                .sorted(Comparator.comparing(Person::getRollNum)) //working
//                .sorted(Comparator.comparingInt(Person::getRollNum)) //working
//                .sorted((a, b) -> a.getRollNum().compareTo(b.getRollNum())) //working
                .sorted(Comparator.comparing(
                        Person::getRollNum, //sort on which object
                        Comparator.nullsLast(Comparator.naturalOrder()))) //logic of sorting : null position + sorting logic
                .forEach(System.out::println);
        System.out.println();

        people.stream()
                .sorted(Comparator.comparing(
                        Person::getRollNum, //sort on object
                        Comparator.nullsLast(Comparator.naturalOrder()) //sorting logic
                        ).thenComparing(Person::getName))//natural order sort + remove null
                .forEach(System.out::println);
        System.out.println();

        //todo : use below logic for sorting , 1st argument on which object, 2nd argument sorting logic(for null we can add just nullLast/nullFirst)
        people.stream()
                .sorted(Comparator.comparing(
                                Person::getRollNum,
                                Comparator.reverseOrder()))
                .forEach(System.out::println);
        System.out.println();


        people.stream()
                .sorted(Comparator.comparing(
                        Person::getRollNum,
                        Comparator.nullsLast(Integer::compareTo)
                )) //natural order sort + keep null last
                .forEach(System.out::println);

        System.out.println();

        people.stream()
                .sorted(Comparator.comparing(
                        Person::getRollNum,
                        Comparator.nullsLast(Integer::compareTo)
                ).thenComparing(Person::getName)) //natural order sort + keep null last + if 2 roll number same then sort based on name
                .forEach(System.out::println);

    }

    private Person getPerson(String name, Integer rollNum, String location, String state, int pinCode) {
        return Person.builder().name(name).rollNum(rollNum).address(Address.builder().location(location).state(state).pinCode(pinCode).build()).build();
    }
}
