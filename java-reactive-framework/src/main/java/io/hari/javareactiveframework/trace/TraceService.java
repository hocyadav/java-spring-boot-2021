package io.hari.javareactiveframework.trace;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TraceService {

    Tracer tracer;//tracer obj -> span obj -> tracer context obj -> get trace id , span id

    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    public TraceService(@Lazy Tracer tracer) {
        this.tracer = tracer;
    }
    public void printTraceIdAndSpanId_usingTracerInstance() {
        Span span = tracer.currentSpan();//1. get span object
        TraceContext traceContext = span.context();//2. get trace context object

        String spanIdString = traceContext.spanIdString();
        String traceIdString = traceContext.traceIdString();

        System.out.println("spanIdString = " + spanIdString);
        System.out.println("traceIdString = " + traceIdString);
        System.out.println("applicationName = " + applicationName);
    }

    public void printTraceIdAndSpanId_usingMDC() {
        System.out.println("X-B3-TraceId = " + MDC.get("X-B3-TraceId"));
        System.out.println("X-B3-SpanId = " + MDC.get("X-B3-SpanId"));
    }

    public Tracer getTracer() {
        return tracer;
    }
}
