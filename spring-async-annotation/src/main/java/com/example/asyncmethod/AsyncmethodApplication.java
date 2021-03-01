package com.example.asyncmethod;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync//2.
@SpringBootApplication
public class AsyncmethodApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncmethodApplication.class, args);
//        SpringApplication.run(AsyncmethodApplication.class, args).close();//after executing app it will close the app.
        //        not useful for pub sub and event type spring apps
    }

    @Bean//3. optional
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("github-Lookup-");
        executor.initialize();
        return executor;
    }
}
