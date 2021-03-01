package com.mock_n_spy;

import static org.mockito.Mockito.mock;

import org.junit.Test;

//1. in autowire - we are calling actual method of that class
//2. in mock - not calling actual body of method - we are creating dummy response here
//3. in spy - it both - 1 + 2,
		//we can run some method as actual, (like autowire)
		//we can mock some method , (like mock)
		//we can skip some method (spy concept)

//https://youtu.be/Ekr4jxOIf4c?t=799 -- stat from 13.18
class Node{
	public void fun1() {
		System.out.println("fun1");
		fun2();
	}
	
	public void fun2() {
		System.out.println("fun2");
	}
	
	public void fun3() {
		System.out.println("fun3");
	}
}

public class Mock_n_Spy {
	
	@Test
	public void test() {
	}
	
}
