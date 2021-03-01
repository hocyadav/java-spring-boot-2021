package com.hari1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ClassB {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int poolSize = 10;
		ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
		
		List<Future> allTaskOutput = new ArrayList<>();
		//start
		for(int i = 0; i < 1000; i++) {
			DbTask nn = new DbTask();
			Future<String> f = threadPool.submit(nn);
			allTaskOutput.add(f);
		}
		//end
		
		//file operation start
		for(Future f : allTaskOutput) {
			String result = (String) f.get();
			//Session data = 200000;//40+
			//add to csv file // 
		}
		//file operation end
		
	}
	
	public static class DbTask implements Callable<String> {
		
		public String call() {
			//db operation --
			return "sql output";
		}
	}
	
	
}
