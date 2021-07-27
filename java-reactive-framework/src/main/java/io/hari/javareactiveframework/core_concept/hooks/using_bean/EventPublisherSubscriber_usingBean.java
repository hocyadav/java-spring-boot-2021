package io.hari.javareactiveframework.core_concept.hooks.using_bean;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.Fuseable;
import reactor.core.publisher.Operators;
import reactor.util.context.Context;

import java.util.function.Function;

/**
 * Event publisher collaborator for reactive streams.
 */
public final class EventPublisherSubscriber_usingBean<T> implements CoreSubscriber<T>, Fuseable {

    private final Subscriber<? super T> subscriber;
    private final Context context;
    private final MyServiceBean myServiceBean;//this will be pass from Hooks, and Hooks will get bean from either M1 or m2 solution

    public EventPublisherSubscriber_usingBean(final Subscriber<? super T> subscriber, final Context context, MyServiceBean myServiceBean) {
        System.out.println("--EventPublisherSubscriber.EventPublisherSubscriber");
        this.subscriber = subscriber;
        this.context = context;
        this.myServiceBean = myServiceBean;
    }

    @Override
    public void onSubscribe(final Subscription subscription) {
        System.out.println("--EventPublisherSubscriber.onSubscribe");
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(final T item) {
        System.out.println("--EventPublisherSubscriber.onNext");
        System.out.println("test_inside_next = " + myServiceBean.foo("test inside next"));

        if (context.hasKey("user")) {
            String user1 = context.get("user").toString();
            System.out.println("user1 = " + user1);
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

    public static
    <T>
    Function<? super Publisher<T>, //type 1
            ? extends Publisher<T>> //type 2
    publisherOperator(final MyServiceBean myServiceBean) {//working bean
//        MyService myService = new MyService();//using bean, pass as argument

        System.out.println("--EventPublisherSubscriber.publisherOperator");
        return Operators.liftPublisher((publisher, coreSubscriber) -> {
            Publisher publisher1 = publisher;
            CoreSubscriber<? super T> coreSubscriber1 = coreSubscriber;
            Context context = coreSubscriber1.currentContext();

            if (publisher instanceof ScalarCallable) {
                return coreSubscriber;
            } else {
                return new EventPublisherSubscriber_usingBean<>(coreSubscriber, context, myServiceBean);
//                return coreSubscriber;
            }
        });
    }
}
