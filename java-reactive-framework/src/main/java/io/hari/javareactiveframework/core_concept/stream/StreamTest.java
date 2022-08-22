package io.hari.javareactiveframework.core_concept.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

public class StreamTest {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Student {
        String name;
        Integer roll;
        OffsetDateTime time;
    }

    public static void main(String[] args) {
        System.out.println("StreamTest.main");

        //working
        Stream<Student> stream = Stream.of(
                Student.builder().name("name1").roll(1).build(),
                Student.builder().name("name2").roll(2).build(),
                Student.builder().name("name3").roll(3).build()
        );
//        stream.sorted((o1, o2) -> o1.roll - o2.roll)
        stream.sorted((o1, o2) -> o2.roll - o1.roll)
                .forEach(student -> {
            System.out.println("student = " + student);
        });


        //working
        OffsetDateTime now = OffsetDateTime.now();
        Stream<Student> stream2 = Stream.of(
                Student.builder().name("name1").roll(1).time(now.plusDays(2L)).build(),
                Student.builder().name("name2").roll(2).time(now.plusDays(4L)).build(),
                Student.builder().name("name3").roll(3).time(now.plusDays(6L)).build()
        );

//        stream2.sorted((o1, o2) -> o1.getTime().compareTo(o2.getTime()))
        stream2.sorted((o1, o2) -> o2.getTime().compareTo(o1.getTime()))
                .forEach(student -> {
                    System.out.println("student = " + student);
                });
    }
}
