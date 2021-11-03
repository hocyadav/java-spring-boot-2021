package com.javainuse;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MyService {

    @Autowired
    private Tracer tracer;

    public String breadcrumbId() {
        Span currentSpan = tracer.currentSpan();
        System.out.println("currentSpan = " + currentSpan.toString());
        return currentSpan.toString();
    }
}
