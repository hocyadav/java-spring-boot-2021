package com.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.otherService.Service2;

@Service
public class ServiceClass {
	
	@Autowired
	Service2 ser;
	
	public String getBracket(int income) {
		if(income < 1000) return "LOW";
		if(income < 5000) return "MEDIUM";
		return "HIGH";
	}
	
	public List<String> allBrackets(){
		return Arrays.asList("LOW", "MEDIUM", "HIGH");
	}
	
	public int callService2(int i) {
		return ser.calculate(i);
	}
	
}
