package com.example.asyncmethod;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.asyncmethod.entity.User;
import com.example.asyncmethod.services.GitHubLookupService;

/**
 * @author HariomYadav
 * @since 31/01/21
 */
@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        testAsyncWithReturnType();//try with both with annotation(2018) and without annotation(3018)
//        testSimpleSyncWithReturn();
//        testSimpleSyncWithComputableFutureReturn();

    }

    private void testSimpleSyncWithComputableFutureReturn() throws InterruptedException, java.util.concurrent.ExecutionException {
        final long start = System.currentTimeMillis();
        final CompletableFuture<User> nasamind = gitHubLookupService.simpleSyncMethodWithComputableFuturReturn("nasamind");
        final CompletableFuture<User> hocyadav = gitHubLookupService.simpleSyncMethodWithComputableFuturReturn("hocyadav");
        final CompletableFuture<User> userCompletableFuture = gitHubLookupService.simpleSyncMethodWithComputableFuturReturn("Spring-Projects");
        CompletableFuture.allOf(nasamind, hocyadav, userCompletableFuture).join();

        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));

        System.out.println("nasamind1 = " + nasamind.get());
        System.out.println("hocyadav = " + hocyadav.get());
        System.out.println("spring = " + userCompletableFuture.get());
    }

    private void testSimpleSyncWithReturn() throws InterruptedException {
        final long start = System.currentTimeMillis();
        System.out.println("nasamind1 = " + gitHubLookupService.simpleSyncMethod("nasamind"));
        System.out.println("hocyadav2 = " + gitHubLookupService.simpleSyncMethod("hocyadav"));
        System.out.println("springProject2 = " + gitHubLookupService.simpleSyncMethod("Spring-Projects"));
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));
    }

    private void testAsyncWithReturnType() throws InterruptedException, java.util.concurrent.ExecutionException {
        final long start = System.currentTimeMillis();//start the clock

        //kick multiple asycs jobs
        final CompletableFuture<User> nasamind = gitHubLookupService.asyncWithReturn("nasamind");
        final CompletableFuture<User> hocyadav = gitHubLookupService.asyncWithReturn("hocyadav");
        CompletableFuture<User> springProject = gitHubLookupService.asyncWithReturn("Spring-Projects");

        //wait until all are done
        CompletableFuture.allOf(nasamind, hocyadav, springProject).join();

        //print result with elapsed time
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));
        System.out.println("nasamind = " + nasamind.get());
        System.out.println("hocyadav = " + hocyadav.get());
        System.out.println("springProject = " + springProject.get());
    }
}
