package io.hari.javareactiveframework.core_concept.hooks.extra;

import java.util.List;

/**
 * Represents an evented aggregate root.
 */
public interface AggregateRoot {

    /**
     * A set of domain events held by this aggregate root.
     *
     * @return domain events
     */
    List<DomainEvent> domainEvents();

    /**
     * Clears all held domain events.
     */
    void clearEvents();
}
