package com.hari.api;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.mockito.Mockito.when;

import com.hari.service.TodoService;

public class TodoBusinessImplTest_Mock {

	@Test
	public void test_NoImplOfServiceMethod() {
		//1.service instance
		TodoService todoService = mock(TodoService.class);
		//2. test
		List<String> retrieveTodos = todoService.retrieveTodos("dummy");
		System.out.println(retrieveTodos);
		assertTrue(retrieveTodos.size() == 0);//we have not mock size - nice mock
	}
	
	@Test
	public void test_ImplOfServiceMethod() {
		//1.service instance
		TodoService todoService = mock(TodoService.class);
		//2.when-then
		List<String> list = Arrays.asList("hari","om","yadav");
		when(todoService.retrieveTodos("dummy")).thenReturn(list );
		
		//3.test
		List<String> retrieveTodos = todoService.retrieveTodos("dummy");
		System.out.println(retrieveTodos);
		assertTrue(retrieveTodos.size() == 3);//we have not mock size - nice mock
	}
	

}
