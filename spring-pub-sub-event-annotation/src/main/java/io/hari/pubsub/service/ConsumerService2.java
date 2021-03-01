package io.hari.pubsub.service;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import io.hari.pubsub.entity.EventEntity;
import io.hari.pubsub.entity.MyEvent;
import io.hari.pubsub.entity.generic.BaseEvent;
import io.hari.pubsub.entity.generic.Event1;
import io.hari.pubsub.entity.generic.Event2;
import io.hari.pubsub.entity.generic.EventType;

/**
 * @author HariomYadav
 * @since 30/01/21
 */
@Component
public class ConsumerService2 {

    //    @EventListener({ MyEvent.class})//working
    //    @EventListener(classes = { MyEvent.class})//working - clean entity this array is used for genric type event receiver
    //    public void consumer1(MyEvent myEventEntity) {
    //        System.err.println("new ConsumerService2.consumer1");
    //        System.out.println("new consumer2 : eventEntity2 = " + myEventEntity);
    //    }

    @EventListener (condition = "#event.name eq 'name1'")//working
    public void consumer2(final MyEvent event) {
        System.err.println("new2 ConsumerService2.consumer1");
        System.out.println("new2 consumer2 : eventEntity2 = " + event);
    }

    @Async//working
    @EventListener (condition = "#event.name eq 'name1'")//working
    public void consumer3(final MyEvent event) {
        System.err.println("new3 ConsumerService3.consumer3");
        System.out.println("new3 consumer3 : eventEntity3 = " + event);
    }

    //    @EventListener({EventEntity.class})//working - entity impl ApplnEvent
    //    public void consumer2(EventEntity eventEntity) {
    //        System.err.println("new ConsumerService2.consumer2");
    //        System.out.println("new eventEntity = " + eventEntity);
    //    }

    @EventListener (classes = { MyEvent.class })//working - clean entity this array is used for genric type event receiver
    public void consumer1(final MyEvent myEventEntity) {
        System.err.println("new ConsumerService2.consumer1");
        System.out.println("new consumer2 : eventEntity2 = " + myEventEntity);
    }

    @Async("threadPoolTaskExecutor")//consume in async manner
    @EventListener
    public void foo(final BaseEvent event) {
        if (event.getEventType().equals(EventType.cancelled)) {//send sms - call sms service
            final Event1 event1 = (Event1) event;
            System.out.println("cancelled logic = " + event1);
        }
        if (event.getEventType().equals(EventType.delivered)) {//send email - call email service
            final Event2 event2 = (Event2) event;
            System.out.println("delivered logic = " + event2);
        }
    }
}
