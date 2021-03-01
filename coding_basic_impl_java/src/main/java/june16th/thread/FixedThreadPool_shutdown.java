package june16th.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool_shutdown {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		System.out.println("service.isShutdown "+service.isShutdown());
		service.execute(new Task());
		service.shutdown();//initial shutdown + not take any new task
		System.out.println("service.isShutdown "+service.isShutdown());//return true , since shutdown has 
		//service.execute(new Task());//throw Rejection Execution Exception
		System.out.println("service.isTerminated "+service.isTerminated());//return true if all Task completed including queue
		
		List<Runnable> list = service.shutdownNow();//list of task that are in Queue + start shutdown
		 
	}
}
