package io.hari.javareactiveframework.core_concept.hooks;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;

@Slf4j
public class MySubscriber<T> implements CoreSubscriber<T> {
    private Subscriber<? super T> subscriber;

    public MySubscriber(Subscriber<? super T> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Subscription s) {
        log.info("--onSubscribe");
        subscriber.onSubscribe(s);
    }

    @Override
    public void onNext(T t) {
        log.info("--on next data " + t.toString());
        subscriber.onNext(t);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("--on error");
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        log.info("--on complete");
        subscriber.onComplete();
    }
}
