package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.dao.EmployeeDao;
import io.hari.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	AppConfig config;

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("config = " + config);
		employeeDao.saveAll(Arrays.asList(
				Employee.builder().name("hariom yadav").email("email1@gma.com").build(),
				Employee.builder().name("neha").email("email2@ca.cm").build(),
				Employee.builder().name("chandan yadav").email("email3@asa.com").build(),
				Employee.builder().name("omprakash yadav").email("email4@asa.com").build()
		));
	}
}
