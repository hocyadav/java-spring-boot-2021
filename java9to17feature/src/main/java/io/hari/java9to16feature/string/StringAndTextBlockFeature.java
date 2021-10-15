package io.hari.java9to16feature.string;

import java.util.function.Function;
import java.util.stream.Stream;

public class StringAndTextBlockFeature {
    public static void main(String[] args) {
        System.out.println("blank = " + "abc".isBlank());
        System.out.println("blank = " + "".isBlank());
        System.out.println("blank = " + " ".isBlank());

        System.out.println("strip = " + " abc ".strip());//delete both side spaces
        System.out.println("strip = " + " abc ".stripLeading());//delete starting spaces
        System.out.println("strip = " + " abc ".stripTrailing());//delete ending spaces


        System.out.println("transform = " + "UPPER".transform(s -> s.substring(2)));//apply Function mapper
        System.out.println("transform = " + "UPPER".transform(s -> s.toLowerCase()));//apply Function mapper
        System.out.println("transform = " + "123".transform(s -> Integer.valueOf(s)));//apply Function mapper
        System.out.println("transform = " + "STRING".transform(getStringObjectFunction()));//apply Function mapper

        Stream<String> lines = "line1\nline2\nline3".lines();
        lines.forEach(System.out::println);

        String formatted = "My name is %s, My age is %d".formatted("Hariom", 31);//order is important
        System.out.println("formatted = " + formatted);


        String textBlock = """
                line11
                line22
                line33
                """;
        System.out.println(textBlock);

        String textBlock2 = """
                line 1 : %s
                line 2 : %d
                """.formatted("Hariom Yadav", 12345);//create template (email..etc..)
        System.out.println(textBlock2);


        String str = null;
        System.out.println("str = " + str.isBlank());//enhanced null point exception message

    }

    private static Function<String, Integer> getStringObjectFunction() {
        return s -> {
            //do some operation
            return 007;
        };
    }
}
