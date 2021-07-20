package io.hari.javareactiveframework.core_concept.hooks;

import io.hari.javareactiveframework.core_concept.hooks.extra.AbstractAggregateRoot;
import io.hari.javareactiveframework.core_concept.hooks.extra.DomainEvent;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.Fuseable;
import reactor.core.publisher.Operators;
import reactor.util.context.Context;

import java.util.List;
import java.util.function.Function;

/**
 * Event publisher collaborator for reactive streams.
 */
public final class EventPublisherSubscriber<T> implements CoreSubscriber<T>, Fuseable {

    private final Subscriber<? super T> subscriber;
    private final Context context;
    private final MyService myService;

    /**
     * Constructor.
     *  @param subscriber a subscriber
     * @param context    reactor context
     * @param myService
     */
    public EventPublisherSubscriber(final Subscriber<? super T> subscriber, final Context context, MyService myService) {
        System.out.println("--EventPublisherSubscriber.EventPublisherSubscriber");
        this.subscriber = subscriber;
        this.context = context;
        this.myService = myService;
    }

    @Override
    public void onSubscribe(final Subscription subscription) {
        System.out.println("--EventPublisherSubscriber.onSubscribe");
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(final T item) {
        System.out.println("--EventPublisherSubscriber.onNext");

        if (context.hasKey("user")) {
            String user1 = context.get("user").toString();
            System.out.println("user1 = " + user1);
        }

        //todo my-service we get instance from constructor : working
        //get data from onNext and send to myservice
        String foo = myService.foo(item.toString());
        System.out.println("foo = " + foo);

        //todo my-service we get instance from context : working
        if (context.hasKey("my_service")) {
            Object my_service = context.get("my_service");
            MyService myService = MyService.class.cast(my_service);
            String foo1 = myService.foo(item.toString());
            System.out.println("foo1 = " + foo1);
        }


        // This approach requires streams to have a subscriber context with
        // a messageproducer specified.
        // Can be achieved for all streams with the help of a decorator
        // or manually specified with a .subscriberContext(ctx -> ctx.put()) call
        // at the end of each chain.
        if (context.hasKey("message-producer") && item instanceof AbstractAggregateRoot) {
            System.out.println("if else");
            final List<DomainEvent> events = ((AbstractAggregateRoot) item).domainEvents();
            // transform to message (say, with MessageBuilder)
            // produce messages via producer in context
            ((AbstractAggregateRoot) item).clearEvents();
        }
        subscriber.onNext(item);
    }

    @Override
    public void onError(final Throwable error) {
        System.out.println("--EventPublisherSubscriber.onError");
        subscriber.onError(error);
    }

    @Override
    public void onComplete() {
        System.out.println("--EventPublisherSubscriber.onComplete");
        subscriber.onComplete();
    }

    /**
     * Provides a publishing operator for reactive-stream chains.
     *
     * @param <T> the publisher data type
     * @return operator
     */
    public static
    <T>
    Function<? super Publisher<T>, //type 1
            ? extends Publisher<T>> //type 2
    publisherOperator() {
        MyService myService = new MyService();

        System.out.println("--EventPublisherSubscriber.publisherOperator");
        return Operators.liftPublisher((publisher, coreSubscriber) -> {
            Publisher publisher1 = publisher;
            CoreSubscriber<? super T> coreSubscriber1 = coreSubscriber;
            Context context = coreSubscriber1.currentContext();

            if (publisher instanceof ScalarCallable) {
                return coreSubscriber;
            } else {
                return new EventPublisherSubscriber<>(coreSubscriber, context, myService);
//                return coreSubscriber;
            }
        });
    }
}
