package io.hari.apachecamelintegrationpattern;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Hariom Yadav
 * @since 18/06/21
 */
//@Component // comment to deactivate below routes, this is helpful when we have multiple route in other class
public class MyRouter extends RouteBuilder { // router or path : contains from and to
    @Autowired
    MyProcessorClass myProcessorClass;

    /**
     * Approach
     * 1 from : queue
     * 2 transformation
     * 3 to : database
     */
    @Override
    public void configure() throws Exception {
//        firstPath();
//        secondPath_Transform();
//        thirdPath_Transform_M1();
//        thirdPath_Transform_M2();
//        fourth_Processor_M1();
//        fourth_Processor_M2();
        fileInputAndOutputConnectors();
    }

    //todo : timer and log connector
    //my path/route : timer -> transformation -> log
    //add camel.springboot.main-run-controller=true
    private void firstPath() {
        from("timer:first-timer1")//timer connector + DSL(domain specific language)
                .log("${body}")
                .to("log:first-timer1");//log connector
    }

    private void secondPath_Transform() {
        from("timer:first-timer2") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .log("${body}")
                .transform().constant("my constant 2 ")
                .to("log:first-timer2");
    }

    private void thirdPath_Transform_M1() {
        from("timer:first-timer3") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .log("${body}")
                .transform().constant("my constant 3 ")
                .transform().constant("my constant 3 " + LocalDateTime.now())//override the above transform logic
                .to("log:first-timer3");

    }

    private void thirdPath_Transform_M2() {
        from("timer:first-timer4") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .log("${body}")
//                .bean("getMyTransformBeanClass")//working + if any transformation above then it will override
                .bean("getMyTransformBeanClass", "getCurrentTimeMethodName")//working: class name with method name
//                .bean("getMyTransformBeanClass", "getCurrentTimeMethodName2")//not working coz no method name: class name with method name
                .log("${body}")
                .to("log:first-timer4");
    }


    public void fourth_Processor_M1() {
        from("timer:first-timer4") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .bean(myProcessorClass)//working: calling everytime and getting new data
                .log("${body}")
                .to("log:first-timer4");
    }

    public void fourth_Processor_M2() {
        from("timer:first-timer4") //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
                .transform().constant("constant string send to processor bean : ")
                .bean(new MyProcessorImpl())//working
                .log("${body}")
                .to("log:first-timer4");
    }

    //todo : file connector
    //using file connectors , these connectors are already present in apache camel dependency
    //so no need to add extra dependency for these connectors
    public void fileInputAndOutputConnectors() {//working : run -> then create a new file and add some content -> see on log
        from("file:files/input")
                .log("${body}")
                .to("file:files/output")
                .log("${body}");
    }
}

@Component
class GetMyTransformBeanClass {
    public String getCurrentTimeMethodName() {
        return "Time now from BEAN is " + LocalDateTime.now();
    }
}

@Component
class MyProcessorClass {
    Logger logger = LoggerFactory.getLogger(MyProcessorClass.class);

    public void process(String msg) {
//        System.out.println("msg");//not printing
        logger.info("MSG " + LocalDateTime.now());//printing
    }
}

class MyProcessorImpl implements Processor {
    Logger logger = LoggerFactory.getLogger(MyProcessorImpl.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Process : " + exchange.getMessage().getBody());
    }
}
