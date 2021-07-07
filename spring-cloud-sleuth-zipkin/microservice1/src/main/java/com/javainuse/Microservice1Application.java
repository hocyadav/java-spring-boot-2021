package com.javainuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("Inside method1");
//		String baseUrl = "http://localhost:8081/microservice2";
		String baseUrl = "http://localhost:8091/microservice2";
		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		LOG.info("The response recieved by method1 is " + response);
		return response;
	}
}
