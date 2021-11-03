package com.javainuse;

import brave.SpanCustomizer;
import brave.Tracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyService2 {
//    @Autowired
//    SpanAccessor spanAccessor;//old not available in new version : https://stackoverflow.com/questions/56478224/spring-cloud-slueth-spanaccessor-interface

//    @Autowired
//    SpanCustomizer spanCustomizer;

    @Autowired
    Tracing tracing;

}
