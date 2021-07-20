package io.hari.javareactiveframework.core_concept.hooks.extra;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

/**
 * DomainEvent interface.
 */
public interface DomainEvent extends Serializable {

    /**
     * Id to associate with.
     * @return
     */
    String id();

    /**
     * Different correlation ids from the source.
     *
     * @return
     */
    Map<String, String> correlationId();

    /**
     * Name of the domain event.
     *
     * @return
     */
    String name();

    /**
     * timestamp of the domain event.
     *
     * @return
     */
    default long createdAt(){
        return Instant.now().toEpochMilli();
    }

    /**
     * Source of the domain event.
     *
     * @return
     */
    Class eventClass();
}
