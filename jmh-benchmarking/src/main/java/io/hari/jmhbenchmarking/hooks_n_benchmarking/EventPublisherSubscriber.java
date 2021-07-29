package io.hari.jmhbenchmarking.hooks_n_benchmarking;

import io.hari.jmhbenchmarking.hooks_n_benchmarking.extra.AbstractAggregateRoot;
import io.hari.jmhbenchmarking.hooks_n_benchmarking.extra.DomainEvent;
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
        this.subscriber = subscriber;
        this.context = context;
        this.myService = myService;
    }

    @Override
    public void onSubscribe(final Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(final T item) {
        if (context.hasKey("message-producer") && item instanceof AbstractAggregateRoot) {
            final List<DomainEvent> events = ((AbstractAggregateRoot) item).domainEvents();
            ((AbstractAggregateRoot) item).clearEvents();
        }
        subscriber.onNext(item);
    }

    @Override
    public void onError(final Throwable error) {
        subscriber.onError(error);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    /**
     * Provides a publishing operator for reactive-stream chains.
     *
     * @param <T> the publisher data type
     * @return operator
     */
    public static <T> Function<? super Publisher<T>, ? extends Publisher<T>>
    publisherOperator(MyService myService) {


        return Operators.liftPublisher((publisher, coreSubscriber) -> {
//            Publisher publisher1 = publisher;
//            CoreSubscriber<? super T> coreSubscriber1 = coreSubscriber;
//            Context context = coreSubscriber1.currentContext();

//            if (publisher instanceof ScalarCallable) {
//                return coreSubscriber;
//            } else {
//                return new EventPublisherSubscriber<>(coreSubscriber, context, myService);
//            }
            return coreSubscriber;
        }

//        Operators.



        );
    }
}
