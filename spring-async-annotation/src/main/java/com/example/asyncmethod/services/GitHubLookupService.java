package com.example.asyncmethod.services;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.asyncmethod.entity.User;

/**
 * @author HariomYadav
 * @since 31/01/21
 */
@Service
public class GitHubLookupService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;

    private final MyService myService;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder, MyService myService) {
        this.restTemplate = restTemplateBuilder.build();
        this.myService = myService;
    }

    @Async//1. async with return type - working
    public CompletableFuture<User> asyncWithReturn(String user) throws InterruptedException {
        logger.info("Looking up {}", user);
        String url = String.format("https://api.github.com/users/%s", user);
//        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        final User results = User.builder().name(user).blog(user).build();
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

    @Async//async with no return type - working
    public void aysncWithNoReturn(String user) throws InterruptedException {
        logger.info("async with no return type {}", user);
        myService.foo();
    }

    public User simpleSyncMethod(String user) throws InterruptedException {
        logger.info("Looking up {}", user);
        final User results = User.builder().name(user).blog(user).build();
        Thread.sleep(1000L);
        return results;
    }


    //this method is same as async with computable future only difference is we remove annotation to method sync
    public CompletableFuture<User> simpleSyncMethodWithComputableFuturReturn(String user) throws InterruptedException {
        logger.info("Looking up {}", user);
        final User results = User.builder().name(user).blog(user).build();
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

}
