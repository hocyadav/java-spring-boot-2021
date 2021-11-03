package io.hari.javavavr;

import io.vavr.*;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class VarvTest {

    //funtion2, 3..
    //tuple2, 3...
    @Test
    public void foo() {
        //java 8 mapper type1 -> type2
        Function<String, Integer> functionMapper = s -> s.length();

        String input = "hariom yadav";
        Integer output = functionMapper.apply(input);
        System.out.println("output = " + output);
    }

    @Test
    public void foo2() {
        //varv 8 mapper type1 -> type2
//        Function<String, Integer> functionMapper = s -> s.length();
        Function1<String, Integer> function2 = s -> s.length();//just change Function to Function1

        String input = "hariom yadav";
        Integer output = function2.apply(input);
        System.out.println("output = " + output);
    }

    @Test
    public void foo3() {
        //tuple store different types inside Tuple type
        Tuple1<String> tuple1 = new Tuple1<>("hariom yadav");
        String output = tuple1._1();
        System.out.println("output = " + output);

        //store 2 different types in one type
        Tuple2<String, Integer> tuple2 = new Tuple2<>("hariom yadav", 495452);
        String type1 = tuple2._1();
        Integer type2 = tuple2._2();
        System.out.println("type1 = " + type1);
        System.out.println("type2 = " + type2);

    }

    @Test
    public void foo4() {
        //mapper type1 , type2 --> type3
        Function2<String, String, Integer> function2 = (s1, s2) -> s1.length() + s2.length();

        String input1 = "hariom";
        String input2 = "yadav";

        Integer output = function2.apply(input1, input2);
        System.out.println("output = " + output);
    }


    //todo : API methods
    @Test
    public void foo5() {
        List<Integer> list = API.List(1, 2, 3, 4, 5);
        //support stream methods, since internally it uses Iterable interface
        //here no need to call stream(), simply we can start using stream operators
        list
                .filter(integer -> integer % 2 == 0)
                .forEach(System.out::println);
    }

    @Test
    public void foo6() {
        //case 1 : optional with value, it will work fine
//        Optional<String> optional = Optional.of("hariom yadav");
        // case 2 : optional with no value, in this case it will break : java.lang.NullPointerException, solution we need to check for null
        Optional<String> optional = Optional.of(null);

        Optional<String> optional2 = optional.filter(i -> i.contains("hariom"));
        optional.ifPresent(s -> System.out.println("s = " + s));
    }


    @Test
    public void foo7() {
        //todo : check for value or NULL

        //case 1 : with value - work
        Option<String> option = API.Option("hariom yadav");
        // case 2 : with no value - work
//        Option<String> option = API.Option(null);

        //we can perform stream operators
        Option<String> option1 = option
                .filter(s1 -> s1.contains("hariom"))
                .peek(s -> System.out.println("s = " + s));
        //in end we can call .getOrElse("fallback default value")

        //convert to java optional
        Optional<String> javaOptional = option1.toJavaOptional();
    }

    @Test
    public void foo71() {
        //1. in end we can call .getOrElse("fallback value from hardcoded string")
        //2. in end we can call .getOrElse(() -> fallback value from method)
        String input = null;

        String output = API.Option(input)
                .filter(s1 -> s1.contains("hariom"))//this pipeline will only execute if above have some value, so in VARV we are not checking for null
                .peek(s -> System.out.println("s = " + s))
                .getOrElse("hardcoded string");

        System.out.println("output = " + output);

        String output2 = API.Option(input)
                .filter(s1 -> s1.contains("hariom"))//this pipeline will only execute if above have some value, so in VARV we are not checking for null
                .peek(s -> System.out.println("s = " + s))
                .getOrElse(() -> fallbackMethod());

        System.out.println("output2 = " + output2);

        //3. in end we can also call .getOrElseThrow(() -> new RuntimeException("oops"))
        String output3 = API.Option(input)
                .filter(s1 -> s1.contains("hariom"))
                .peek(s -> System.out.println("s = " + s))
                .getOrElseThrow(() -> new RuntimeException("oops"));
        System.out.println("output3 = " + output3);
    }

    private String fallbackMethod() {
        //some processing
        return "some string";
    }

    @Test
    public void foo80() {
        //todo : check for value or Exception, same as above one
        API.Try(() -> new URI("www.google.com"))
                .map(uri -> uri.toString())
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("s = " + s));

        //in end we can call .getOrElse("fallback default value")
    }
    @Test
    public void foo801() {
        //1. in end we can call .getOrElse("fallback value from hardcoded string")
        //2. in end we can call .getOrElse(() -> fallback value from method)

        String output = API.Try(() -> new URI(null))
                .map(uri -> uri.toString())
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("s = " + s))
                .getOrElse("hardcoded value");
        System.out.println("output = " + output);


        String output2 = API.Try(() -> new URI(null))
                .map(uri -> uri.toString())
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("s = " + s))
                .getOrElse(() -> fallbackMethod());
        System.out.println("output2 = " + output2);


        //3. in end we can also call .getOrElseThrow(() -> new RuntimeException("oops"))
        String output3 = API.Try(() -> new URI(null))
                .map(uri -> uri.toString())
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("s = " + s))
                .getOrElseThrow(() -> new RuntimeException("oops"));
        System.out.println("output3 = " + output3);
    }

    @Test
    public void foo8() {
        //todo : check for value or Exception, same as above one

//        recoverWith === fallback

        // case 1 : with value, recoverWith will not trigger
//        String url = "www.google.com";
        // case 2 : with NULL, exception will come and recoverWith trigger change to "www.facebook.com"
        String url = null;

        API.Try(() -> new URI(url)).recoverWith(Exception.class, e -> Try.of(() -> new URI("www.facebook.com")))//exception type, fallback
                .map(uri -> uri.toString())
                .filter(s -> s.length() % 2 == 0)
                .peek(s -> System.out.println("s = " + s));

        //in end we can call .getOrElse("fallback default value")
    }

    @Test
    public void foo9(){
        Supplier<String> supplier = () -> {
            System.out.println("do processing");
            return "hariom yadav";
        };

        supplier.get();
        supplier.get();
        //supplier body will be called every time
    }

    @Test
    public void foo91(){
        Supplier<String> supplier = () -> {
            System.out.println("do processing");
            return "hariom yadav";
        };


        Lazy<String> lazy = Lazy.of(supplier);
        //lazy have all stream operators, whereas Supplier doesn't have
        lazy
                .filter(s -> s.contains("hariom"))
                .peek(s -> System.out.println("s1 = " + s));

        lazy
                .filter(s -> s.contains("hariom"))
                .peek(s -> System.out.println("s2 = " + s));
        //supplier body will be called only one time and then pipeline method will call


        //1. in end we can call .getOrElse("fallback value from hardcoded string")
        //2. in end we can call .getOrElse(() -> fallback value from method)
        //3. in end we can also call .getOrElseThrow(() -> new RuntimeException("oops"))
    }

    @Test
    public void foo10(){
        //this is vavr List same as java 9 List.of
        List<Integer> originalImmutableList = List.of(1, 2, 3, 4);//java varv immutable
        java.util.List<Integer> javaList = originalImmutableList.asJava();//java 8 immutable, we cant add new element
        //explore other awesome methods
        java.util.List<Integer> javaMutable = originalImmutableList.asJavaMutable();//java 8 mutable, we can add element

        List<Integer> list1 = originalImmutableList.drop(1);//create new list
        System.out.println("list1 = " + list1);

        List<Integer> list2 = originalImmutableList.filter(integer -> integer % 2 == 0);//create new list
        System.out.println("list2 = " + list2);

        System.out.println("originalImmutableList = " + originalImmutableList);//no change

        //others methods
        List<String> stringList = List.of("hariom", "yadav");
        List<Tuple2<Integer, String>> zip = originalImmutableList.zip(stringList);
        System.out.println("zip = " + zip);

        List<Tuple2<String, Integer>> zipWithIndex = stringList.zipWithIndex();
        System.out.println("zipWithIndex = " + zipWithIndex);

        //java mutable to java immutable
        java.util.List<Integer> list = List.ofAll(javaMutable)//java varv immutable
                .asJava();//java 8 immutable

//        Map<Integer, java.util.List<Integer>> listMap =
//                originalImmutableList.collect(Collectors.groupingBy(i -> i.intValue()));
//        System.out.println("listMap = " + listMap);


    }

}
