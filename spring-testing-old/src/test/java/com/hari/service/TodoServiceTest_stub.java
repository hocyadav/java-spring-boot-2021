package com.hari.service;

import java.util.Arrays;
import java.util.List;

public class TodoServiceTest_stub implements TodoService{

	public List<String> retrieveTodos(String user) {
		return Arrays.asList("hari","om");
	}

	public void deleteTodo(String todo) {
		
	}

}
