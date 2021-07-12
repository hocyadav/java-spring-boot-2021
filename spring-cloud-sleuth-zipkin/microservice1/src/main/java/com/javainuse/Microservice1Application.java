package com.javainuse;

import brave.Tracer;
import brave.Tracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;
import org.springframework.web.servlet.support.RequestContext;

import java.util.Map;

@SpringBootApplication
public class Microservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice1Application.class, args);
	}
}

@RestController
class Microservice1Controller {

	@Autowired
	RestTemplate restTemplate;

//	@Autowired
//	Tracer tracer;

	@Autowired
	TraceService traceService;

//	@Autowired
//	Tracing tracing;

//	@Autowired
//	MyService2 myService2;
//
//	@Autowired
//	MyService myService;

//	@Autowired
//	Tracer tracer;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = "/microservice1")
	public String method1() {
		Tracer tracer = traceService.getTracer();

		System.out.println("tracer = " + tracer);
		System.out.println("tracer.currentSpan = " + tracer.currentSpan());
		System.out.println("tracer.nextSpan = " + tracer.nextSpan());

		System.out.println("X-B3-TraceId = " + MDC.get("X-B3-TraceId"));
		System.out.println("X-B3-SpanId = " + MDC.get("X-B3-SpanId"));
		Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
		System.out.println("copyOfContextMap = " + copyOfContextMap);

		traceService.printTraceId();
		traceService.printTraceId2();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("Inside method1");
//		String baseUrl = "http://localhost:8081/microservice2";
		String baseUrl = "http://localhost:8091/microservice2";
//		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
//		LOG.info("The response recieved by method1 is " + response);
		return "response";
	}
}
