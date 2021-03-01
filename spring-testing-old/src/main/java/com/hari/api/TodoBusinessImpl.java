package com.hari.api;

import java.util.ArrayList;
import java.util.List;

import com.hari.service.TodoService;

public class TodoBusinessImpl {
	
	private TodoService todoService;

	TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> getAll(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		//IMP : when service is mock , and no impl for method retrieveTodo then it will 
		//return default here it will return empty list
		
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}

	public void deleteUser(String user) {
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}

}
