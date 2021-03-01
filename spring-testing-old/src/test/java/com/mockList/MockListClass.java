package com.mockList;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class MockListClass {

	@Test
	public void mockListClass_andSize() {
		//mock List class
		List listObj = mock(List.class);
		//when-then (stub)
		when(listObj.size()).thenReturn(2);
		//test
		System.out.println(listObj.size());//2
		assertEquals(2, listObj.size());
	}

	@Test
	public void mockListClass_andGet() {
		//mock List class
		List list = mock(List.class);
		//when-then
		when(list.get(0)).thenReturn("hariom");//(stub)
		//test
		System.out.println(list.get(0));//hariom
		assertEquals("hariom", list.get(0));
	}

	@Test
	public void mockListClass_NiceMockConcept() {
		List list = mock(List.class);
		System.out.println(list.get(0));//null
		System.out.println(list.get(1));//null

		assertEquals(null, list.get(0));
		assertEquals(null, list.get(1));
	}

	@Test
	public void mock_argumentMatcher() {
		List list = mock(List.class);

		//when-then
		when(list.get(anyInt())).thenReturn("Hariom Yadav");//any int argument - output same
		when(list.get(any(Integer.class))).thenReturn("Hariom Yadav");//any int argument - output same

		System.out.println(list.get(0));//Hariom Yadav
		System.out.println(list.get(1));//Hariom Yadav

		assertEquals("Hariom Yadav", list.get(0));
		assertEquals("Hariom Yadav", list.get(1));
	}
	
	//*imp
	@Test(expected = RuntimeException.class)//4 : catching exception here 
	public void test_Exception() {
		List list = mock(List.class);//1.
		when(list.get(anyInt())).thenThrow(new RuntimeException("ex msg"));//2.
		list.get(0);//3.it will throw RT exception
		//list.get(1);//it will throw RT exception
	}
	
}
