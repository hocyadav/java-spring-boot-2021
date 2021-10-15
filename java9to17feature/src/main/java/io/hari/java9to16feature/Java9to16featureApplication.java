package io.hari.java9to16feature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java9to16featureApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java9to16featureApplication.class, args);
		System.out.println("args = " + args);
	}

}
