package io.hari.jmhbenchmarking;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmhBenchmarkingApplication {

	@SneakyThrows
	public static void main(String[] args) {
		org.openjdk.jmh.Main.main(args);//start JMH
		SpringApplication.run(JmhBenchmarkingApplication.class, args);
	}

}
