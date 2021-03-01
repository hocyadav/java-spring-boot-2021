package io.hari.pubsub.entity.generic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HariomYadav
 * @since 30/01/21
 */
@Getter
@Setter
@ToString
public class Event1 extends BaseEvent {
    String name;

    EventType eventType;

    public Event1(String name) {
        super(EventType.cancelled);
        this.name = name;
        this.eventType = EventType.cancelled;
    }
}
