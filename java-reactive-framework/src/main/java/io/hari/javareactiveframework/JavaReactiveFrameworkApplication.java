package io.hari.javareactiveframework;

import io.hari.javareactiveframework.trace.TraceService;
import io.hari.javareactiveframework.webflux.GreetingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hariom Yadav
 * @since 12/06/21
 */
@SpringBootApplication
public class JavaReactiveFrameworkApplication implements CommandLineRunner {
	@Autowired
	TraceService traceService;

	public static void main(String[] args) {
		SpringApplication.run(JavaReactiveFrameworkApplication.class, args);

		//step 4: make application executable : https://spring.io/guides/gs/reactive-rest-service/
		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getResult());
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("JavaReactiveFrameworkApplication.run");

		//not working
//		traceService.printTraceIdAndSpanId_usingTracerInstance();
//		traceService.printTraceIdAndSpanId_usingMDC();
	}
}
