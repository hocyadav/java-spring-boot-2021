package io.hari.javareactiveframework.core_concept.function_prog.consumer;

import java.util.function.Consumer;

public interface IEntityA {
    default void forEach() {
        System.out.println("for each");
    }

    default void forEach2(Consumer<EntityA> aConsumer) {//working
        aConsumer.accept((EntityA) this);
    }

    default void forEach3(Consumer<IEntityA> aConsumer) {//working
        aConsumer.accept(this);
    }

    default void forEach_validation(Consumer<IEntityA> aConsumer) {//working
        EntityA entityA = EntityA.class.cast(this);
        aConsumer.accept(entityA);
    }
}
