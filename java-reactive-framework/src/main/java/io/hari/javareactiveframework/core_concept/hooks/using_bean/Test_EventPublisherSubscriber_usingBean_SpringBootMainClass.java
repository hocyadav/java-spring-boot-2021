package io.hari.javareactiveframework.core_concept.hooks.using_bean;

import io.hari.javareactiveframework.core_concept.hooks.MyService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import javax.annotation.PostConstruct;
import java.util.function.Function;

@SpringBootApplication
public class Test_EventPublisherSubscriber_usingBean_SpringBootMainClass {
    public static void main(String[] args) {
        SpringApplication.run(Test_EventPublisherSubscriber_usingBean_SpringBootMainClass.class, args);
//        m1EventLogic.fluxCallTest();//2 working
    }
}

/**
 * Approach : https://stackoverflow.com/questions/27405713/running-code-after-spring-boot-starts
 * 1. Spring will create service class bean and set to Application context
 * 2. Spring emits different events, ApplicationReadyEvent event when all bean creation ready
 * 3. listen for that event and -> get bean using Autowire + call our logic and pass bean Hooks....(pass core subscriber instance with bean)
 * 4. inside core subscriber class we have service class bean, enjoy
 */
@Component
class m1EventLogic {
    @Autowired MyServiceBean myServiceBean;

    @EventListener(ApplicationReadyEvent.class)
    public void foo() {
        System.out.println("m1EventLogic.foo");

        String foo = myServiceBean.foo("hariom");//testing service bean is present here or not - working
        System.out.println("foo 1 = " + foo);

        //working
        Function<? super Publisher<Object>, ? extends Publisher<Object>> publisher_FunctionMapper =
                EventPublisherSubscriber_usingBean.publisherOperator(myServiceBean);
        Hooks.onEachOperator(publisher_FunctionMapper);//this will add on each publisher

        fluxCallTest();//1. working
    }

    public static void fluxCallTest() {
        Flux.range(1, 1)
                .contextWrite(context -> context.put("my_service", new MyService()))
                .contextWrite(context -> context.put("user", "hariom"))
                .subscribe(
                        data -> System.out.println("data " + data),
                        err -> System.out.println("err.getMessage() = " + err.getMessage()),
                        () -> System.out.println("DONE signal")
                );
    }
}

@Component
class m2PostConstructor {
    @Autowired MyServiceBean myServiceBean;

    @PostConstruct
    public void foo() {
        System.out.println("m2PostConstructor.foo");

        String omp = myServiceBean.foo("omp");
        System.out.println("foo 2 = " + omp);

        //comment above Hooks and test this init
        //working but order of subscription is different from above one
//        Function<? super Publisher<Object>, ? extends Publisher<Object>> publisher_FunctionMapper =
//                EventPublisherSubscriber_usingBean.publisherOperator(myServiceBean);
//        Hooks.onEachOperator(publisher_FunctionMapper);//this will add on each publisher
//
//        m1EventLogic.fluxCallTest();

    }
}
