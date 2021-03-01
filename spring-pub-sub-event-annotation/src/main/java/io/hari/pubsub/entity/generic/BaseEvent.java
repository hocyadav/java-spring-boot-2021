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
public abstract class BaseEvent {//1. abstract + 2. T extend(not required)

    private final EventType eventType;//3. field

    public BaseEvent(EventType eventType) {//4. constructor
        this.eventType = eventType;
    }


}
