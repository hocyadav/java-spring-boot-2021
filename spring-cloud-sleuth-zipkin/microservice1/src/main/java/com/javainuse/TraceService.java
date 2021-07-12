package com.javainuse;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TraceService {

    Tracer tracer;

    @Autowired
    public TraceService(@Lazy Tracer tracer) {
        this.tracer = tracer;
    }

    public void printTraceId() {
        Span span = tracer.currentSpan();//get span object
        TraceContext context = span.context();//get context object

        String spanIdString = context.spanIdString();
        String traceIdString = context.traceIdString();

        System.out.println("spanIdString = " + spanIdString);
        System.out.println("traceIdString = " + traceIdString);
    }

    public void printTraceId2() {
        System.out.println("X-B3-TraceId = " + MDC.get("X-B3-TraceId"));
        System.out.println("X-B3-SpanId = " + MDC.get("X-B3-SpanId"));
    }

    public Tracer getTracer() {
        return tracer;
    }
}
