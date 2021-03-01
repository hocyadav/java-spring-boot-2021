package com.service.annotationOrJavaBases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.ServiceClass;

//this class similar to xml
@Configuration //1
public class MyConfig {

	@Bean(name = "serviceBean")
	public ServiceClass getInstanceServiceClass() {
		return new ServiceClass();
	}
}
