package july16th.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future_test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		
		Future<String> future = fixedThreadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				return "order-details";
			}
		});
		String orderDetails = future.get();
		System.out.println(orderDetails);
		
		Future<String> future2 = fixedThreadPool.submit(new ClassA());
		String orderEn = future2.get();
		System.out.println(orderEn);
		
		
	}

	static class ClassA implements Callable<String>{
		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			return "enrich-order";
		}
		
	}
}
