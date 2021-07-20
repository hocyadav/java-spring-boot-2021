package io.hari.javareactiveframework.core_concept.hooks.extra;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation tracks spring-data's aggregate root, minus the spring-i-ness.
 */
public class AbstractAggregateRoot implements AggregateRoot {

    private final transient List<DomainEvent> domainEvents = new ArrayList<>();

    @Override
    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearEvents() {
        this.domainEvents.clear();
    }

    /**
     * Registers the event.
     *
     * @param event a domain event event
     * @param <T> domain event type
     * @return event
     */
    protected <T extends DomainEvent> T registerEvent(final T event) {

        Assert.notNull(event, "Domain event must not be null!");

        this.domainEvents.add(event);
        return event;
    }
}
