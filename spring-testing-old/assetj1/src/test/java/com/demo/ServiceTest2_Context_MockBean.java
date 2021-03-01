package com.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.otherService.Service2;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceClass.class}) //for Service2 create mock
public class ServiceTest2_Context_MockBean {

	@Autowired
	private ServiceClass obj;
	
	@MockBean
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
		int input = 20;
		//intercept call to dummy 
		Mockito.when(ser.calculate(input)).thenReturn(input+10);
//		Mockito.when(ser.calculate(AdditionalMatchers.gt(100))).thenReturn(1000);
//		Mockito.when(ser.calculate(AdditionalMatchers.lt(100))).thenReturn(500);
		
		int calculate = ser.calculate(input);
		
		assertThat(calculate).isEqualTo(input+10);
	}
}
