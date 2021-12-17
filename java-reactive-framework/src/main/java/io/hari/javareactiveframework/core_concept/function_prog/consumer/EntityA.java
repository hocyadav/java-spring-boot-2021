package io.hari.javareactiveframework.core_concept.function_prog.consumer;

import lombok.*;

import java.util.function.Consumer;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityA implements IEntityA{
    String name;
    Integer phone;

    public void forEach_validationEntity(Consumer<EntityA> aConsumer) {//working
        aConsumer.accept(this);
    }

    public void consumer_validation() {//working
        getValidationConsumer().accept(this);
    }

    private static Consumer<EntityA> getValidationConsumer() {//other way to validate using hibernate validation annotation
        return entityA -> {
            String name = entityA.getName();
            System.out.println("name = " + name);
            Integer phone = entityA.getPhone();
            System.out.println("phone = " + phone);
            if (phone != 100) {
                System.out.println("not equal");
//                throw new RuntimeException("not equal");//working : throw error in case of in valid data
            }
        };
    }

}
