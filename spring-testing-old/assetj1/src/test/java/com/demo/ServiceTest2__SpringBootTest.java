package com.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.otherService.Service2;

@RunWith(SpringRunner.class)
@SpringBootTest //working with junit 5- change form java build path //create all beans
public class ServiceTest2__SpringBootTest {

	@Autowired
	private ServiceClass obj;
	
	@Autowired
	private Service2 ser;
	
	@Test
	public void brakets() {
		System.out.println("test started...");
		String bracket = obj.getBracket(500);
		assertThat(bracket).isEqualTo("LOW");
	}
	
	@Test
	public void service2test() {
		System.out.println("test 2 started");
		int calculate = ser.calculate(10);
		assertThat(calculate).isEqualTo(20);
	}
}
