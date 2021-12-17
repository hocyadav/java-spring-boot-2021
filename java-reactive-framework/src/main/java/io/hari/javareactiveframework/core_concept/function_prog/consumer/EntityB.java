package io.hari.javareactiveframework.core_concept.function_prog.consumer;

import lombok.*;

import java.util.function.Predicate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityB {
    String name;
    String address;

    //step 1: add simple True/false validation method, this can be used any where, in if else block, even in predicate body
    //make static
    public static boolean isHariom(EntityB entityB) {//anyone can use , since it is independent to current entity class
        return entityB.getName().equals("hariom");
    }//this can be use in declarative coding style


    //step 2 : use above as body for predicate : this can be only used where predicate is as input
    //wrap boolean step 1 method over predicate
    //make static
    public static Predicate<EntityB> isHariomPredicate() {//anyone can use, this qill be use in Function/imperatove style pipeline coding
        return entityB -> isHariom(entityB);
    }

    public boolean isHariom_entityLevel() {//use case : use any field from this Entity and iterate and do some validation and retutn T/F or some other type
//        return this.getName().equals("hariom");//simple
        return isHariom(this);//or use above step 1 method
    }//this method can be used only with this entity object
    //this method can be used as a direct predicate if we use method reference
//    .filter(EntityB::isHariom_entityLevel)//use step 2 predicate method
    //so no need to create predicate, but one use case of predicate obj is that we can chain multiple predicate - awesome
    //rule : create simple boolean methods return T/F --> for each T/F methods create predicate

}
