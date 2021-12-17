package io.hari.javareactiveframework.core_concept.function_prog.consumer;

import java.util.LinkedList;

import static io.hari.javareactiveframework.core_concept.function_prog.consumer.EntityB.*;

public class TestB {
    public static void main(String[] args) {
        EntityB entityB1 = EntityB.builder().name("hariom").address("bangalore").build();
        boolean b = entityB1.isHariom_entityLevel();
        System.out.println("b = " + b);

        LinkedList<EntityB> list = new LinkedList<>() {{
            add(EntityB.builder().name("hariom").address("bangalore").build());
            add(EntityB.builder().name("chandan").address("delhi").build());
        }};
        list.stream()
                .filter(entityB -> isHariom(entityB))//use step 1 simple boolean method
                .filter(isHariomPredicate())//use step 2 predicate method
                .filter(EntityB::isHariom_entityLevel)//use step 2 predicate method
                .forEach(entityB -> System.out.println("entityB = " + entityB));

    }

//    private static Predicate<EntityB> isHariomPredicate() {
//        return entityB -> isHariom(entityB);
//    }
//
//    private static boolean isHariom(EntityB entityB) {
//        return entityB.getName().equals("hariom");
//    }
}
