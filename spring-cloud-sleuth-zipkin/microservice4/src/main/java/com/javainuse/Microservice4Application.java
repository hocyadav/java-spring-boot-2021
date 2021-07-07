package com.javainuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
public class Microservice4Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice4Application.class, args);
	}
}

@RestController
class Microservice4Controller {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
	private  final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@GetMapping(value = "/microservice4")
	public String method4() {
		LOG.info("Inside method4");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello World JavaInUse";
	}
}