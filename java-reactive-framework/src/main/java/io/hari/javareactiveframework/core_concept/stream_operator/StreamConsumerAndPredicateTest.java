package io.hari.javareactiveframework.core_concept.stream_operator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class StreamConsumerAndPredicateTest {
    @Test
    public void simpleStream(){
        List<String> list = new ArrayList<>();
        list.add("hari");
        list.add("om");
        list.add("yadav");

        list.stream()
                .filter(getPredicate())//input : predicate object
                .forEach(s -> System.out.println("s = " + s));//input consumer object
    }

    @Test
    public void extractedOut(){
        List<String> list = new ArrayList<>();
        list.add("hari");
        list.add("om");
        list.add("yadav");

        Predicate<String> predicate = s -> s.length() > 2;
        Consumer<String> consumer = s -> System.out.println("s = " + s);
        list.stream()
                .filter(predicate)//input : predicate object
                .forEach(consumer);//input consumer object
    }

    @Test
    public void extractedOutToMethod(){
        List<String> list = new ArrayList<>();
        list.add("hari");
        list.add("om");
        list.add("yadav");

        Predicate<String> predicate = getPredicate();
        Consumer<String> consumer = getConsumer();
        list.stream()
                .filter(predicate)//input : predicate object
                .forEach(consumer);//input consumer object

        list.stream()
                .filter(getPredicate())//input : predicate object
                .forEach(getConsumer());//input consumer object
    }

    private Consumer<String> getConsumer() {
        return s -> System.out.println("s = " + s);
    }

    private Predicate<String> getPredicate() {
        return s -> s.length() > 2;
    }

    @Test
    public void extractedOutToClass(){//TIPS machine coding
        List<String> list = new ArrayList<>();
        list.add("hari");
        list.add("om");
        list.add("yadav");

        PredicateClass presicateClass = new PredicateClass();
        ConsumerClass consumerClass = new ConsumerClass();
        list.stream()
                .filter(presicateClass)//input : predicate object
                .forEach(consumerClass);//input consumer object
    }

    //s -> s.length() > 2
    class PredicateClass implements Predicate<String>{//impl feature
        @Override
        public boolean test(String s) {//s --> : left side
            return s.length() > 2;//s.length() > 2 : right side
        }
    }

    //s -> System.out.println("s = " + s)
    class ConsumerClass implements Consumer<String>{

        @Override
        public void accept(String s) {//s -->
            System.out.println("s = " + s);//System.out.println("s = " + s)
        }
    }


}
