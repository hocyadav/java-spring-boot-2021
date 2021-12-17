package io.hari.javareactiveframework.core_concept.function_prog.consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class TestA {
    public static void main(String[] args) {
        EntityA entityA = EntityA.builder().name("hari").phone(98877).build();
        System.out.println("entityA = " + entityA);
//        entityA.forEach();
//        entityA.forEach2(entityA1 -> System.out.println("entityA1 .. = " + entityA1));
//        entityA.forEach3(entityA1 -> System.out.println("entityA1 ... = " + entityA1));
//        entityA.forEach_validation(entityA1 -> System.out.println("entityA1 ...: = " + entityA1));
//        entityA.forEach_validation(getConsumer());
//        entityA.forEach_validationEntity(entityA1 -> System.out.println("entityA1 :...: = " + entityA1));

        //calling internal consumer [tips: extract consumer to method and then move to entity class]
        entityA.consumer_validation();

        //validate each entity data in list using same above consumer
        List<EntityA> entityAList = new LinkedList<>() {
            {
                add(EntityA.builder().name("hari").phone(98877).build());
                add(EntityA.builder().name("om").phone(49545).build());
            }
        };
        entityAList.stream().forEach(EntityA::consumer_validation);
    }

    private static Consumer<IEntityA> getConsumer() {
        return entityA1 -> {
            IEntityA entityA11 = entityA1;
            EntityA cast = EntityA.class.cast(entityA11);
            String name = cast.getName();
            System.out.println("name = " + name);
            Integer phone = cast.getPhone();
            System.out.println("phone = " + phone);
            if (phone != 100) {
                System.out.println("not equal");
//                throw new RuntimeException("not equal");
            }
        };
    }
}
