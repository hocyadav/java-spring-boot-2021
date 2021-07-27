package io.hari.javareactiveframework.core_concept.hooks.using_bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Test_EventPublisherSubscriber_usingBean_SpringBootMainClass2 {
    public static void main(String[] args) {
        //1. get app context
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Test_EventPublisherSubscriber_usingBean_SpringBootMainClass2.class, args);
        //2. from app context get bean object and call method
        MyServiceBean myServiceBean = applicationContext.getBean(MyServiceBean.class);
        String foo = myServiceBean.foo("wow");
        System.out.println("foo = " + foo);
    }
}
