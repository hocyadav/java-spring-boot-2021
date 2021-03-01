package com.hari.api;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.hari.service.TodoService;
import com.hari.service.TodoServiceTest_stub;

public class TodoBusinessImplTest_Stub {

	@Test
	public void test() {//call stub service -> call its method
		TodoService service_stub = new TodoServiceTest_stub();
		List<String> result_stub = service_stub.retrieveTodos("dummy");
		System.out.println("result_stub "+result_stub);
		assertTrue(result_stub.size() == 2);
	}

}
