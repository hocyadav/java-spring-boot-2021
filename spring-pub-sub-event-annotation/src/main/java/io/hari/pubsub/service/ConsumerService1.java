package io.hari.pubsub.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import io.hari.pubsub.entity.EventEntity;

/**
 * @author HariomYadav
 * @since 30/01/21
 */
@Component
public class ConsumerService1 implements ApplicationListener<EventEntity> {//old impl(req event class to extend appEvent) - working

    @Override
    public void onApplicationEvent(EventEntity eventEntity) {//working
        System.err.println("old ConsumerService.onApplicationEvent");
        System.out.println("old consumer : eventEntity = " + eventEntity);
    }
}
