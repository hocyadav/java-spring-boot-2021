package io.hari.pubsub.entity;

import org.springframework.context.ApplicationEvent;
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
public class EventEntity extends ApplicationEvent {
    String name;

    public EventEntity(Object source, String name) {
        super(source);
        this.name = name;
    }
}
