package july16th.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Future_test2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				return "step1";
			}
		});
		
		String string = cf.get();
		System.out.println(string);
	}
}
