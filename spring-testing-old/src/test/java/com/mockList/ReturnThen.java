package com.mockList;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReturnThen {
	@Test
	public void test() {
		List list = mock(List.class);
		when(list.get(anyInt()))
			.thenReturn(1)
			.thenReturn(2)
			.thenReturn(3)
			.thenReturn(4);
			
		assertEquals(1, list.get(0));
		
		assertEquals(2, list.get(0));
		
		assertEquals(3, list.get(0));
		
		assertEquals(4, list.get(0));//after this return will be last one
		
		//--last return type
		assertEquals(4, list.get(0));
		assertEquals(4, list.get(0));
		assertEquals(4, list.get(0));
		
		System.out.println(list.get(0));
		System.out.println(list.get(0));
		System.out.println(list.get(0));
		
		//verify list obj method get called 10 times
		verify(list, times(10)).get(0);//get(0) is method
		
		verify(list, times(0)).get(1);//get(1) is called 0 times 
	}
}
