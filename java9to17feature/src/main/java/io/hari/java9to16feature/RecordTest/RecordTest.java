package io.hari.java9to16feature.RecordTest;

import java.util.Objects;

record Person(String name, String email, Integer phoneNum) {}

record Entity(String name, Integer phoneNum) {
    Entity(String name, Integer phoneNum) {//override above constructor
        //add validation : optional
        this.name = name;
        this.phoneNum = phoneNum;
    }
}

record Entity2(String name, Integer phoneNum) {
    Entity2(String name, Integer phoneNum) {
        //add validation : optional
        if (name == null) {
            throw new RuntimeException("oops : name is null");
        }
        Objects.requireNonNull(phoneNum, "oops : number is null");

        this.name = name;
        this.phoneNum = phoneNum;
    }
}

record Entity3(String name, Integer phoneNum) {
    Entity3(String name, Integer phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String name() {
        return name+" new string added";
    }

}

public class RecordTest {
    public static void main(String[] args) {
        Person person = new Person("hariom", "hari@io.com", 9887700);
        System.out.println("person = " + person);

        Entity2 entity2 = new Entity2("omprakash", 98877);
        Entity2 entity2_a = new Entity2("omprakash", 98877);
        Entity2 entity2_b = new Entity2("omprakash yadav", 98877);
        System.out.println("entity2 = " + entity2);

        System.out.println("compare record = " + entity2.equals(entity2_a));//compare each and every field value
        System.out.println("compare record = " + entity2.equals(entity2_b));

//        Entity2 entity3 = new Entity2(null, 98877);
//        Entity2 entity4 = new Entity2("chandan", null);


        Entity3 entity3_ = new Entity3("name 1", 988768);
        System.out.println("entity3_.name() = " + entity3_.name());

    }
}
