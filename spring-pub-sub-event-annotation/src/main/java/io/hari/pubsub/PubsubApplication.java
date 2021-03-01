package io.hari.pubsub;

import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import io.hari.pubsub.entity.generic.Event1;
import io.hari.pubsub.entity.generic.Event2;

@EnableAsync
@SpringBootApplication
public class PubsubApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PubsubApplication.class, args);
    }

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async123-");
        return executor;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("publishing event = ");
        //        IntStream.range(1, 10).forEach(i -> {//old consumer will receive events
        //            eventPublisher.publishEvent(new EventEntity(this, "publish message " + i));
        //        });

        //working
        //        IntStream.range(0, 10).forEach(i -> {//new consumer will receive events
        //            try {
        //                eventPublisher.publishEvent(new MyEvent("name1", "type "+i));
        //                Thread.sleep(10);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //        });

        //testing async - wokring (add 2 places annotaion 1st top of consumer method 2nd top of spring app)
        //        IntStream.range(0,10).forEach(i -> {
        //            eventPublisher.publishEvent(new MyEvent("name1", "type - "+i));
        //        });

//        BaseEvent event1 = new Event1("event1");
//        BaseEvent event2 = new Event2("event2");
//        System.out.println("event1 = " + event1);
//        System.out.println("event1 = " + event2);

        //working - generic event class test
        IntStream.range(0, 10).forEach(i -> {
            Event1 event11 = new Event1("event1");
            eventPublisher.publishEvent(event11);

            Event2 event22 = new Event2("event2");
            eventPublisher.publishEvent(event22);
        });
    }
}
