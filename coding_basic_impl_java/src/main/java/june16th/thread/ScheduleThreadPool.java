package june16th.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);//Delay Queue , priority queue
		
		service.schedule(new Task(), 7, TimeUnit.SECONDS);//execure one time and after 7 sec
		
		service.scheduleAtFixedRate(new Task(), 3, 5, TimeUnit.SECONDS);//execute 1st at 3sec and then repeat after 5 sec
		
		service.scheduleWithFixedDelay(new Task(), 2, 4, TimeUnit.SECONDS);//after successful execute of previous task 
	}
}
