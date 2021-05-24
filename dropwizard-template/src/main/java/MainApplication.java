import commands.MyCommand;
import config.AppConfig;
import health.MyHealthCheck;
import health.MyHealthCheck2;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.SneakyThrows;
import managed.MyClientStartAndStop;
import resource.MyResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 * start application -> go to intellij config
 * server src/main/resources/my-config.yml
 */
public class MainApplication extends Application<AppConfig> {

    @SneakyThrows
    public static void main(String[] args) {
        new MainApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {//a.2. add override initialization method since it contain bootstrap instance
        super.initialize(bootstrap);
        bootstrap.addCommand(new MyCommand());//a.3 add command to bootstrap
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        final MyResource myResource = new MyResource(appConfig);
        final MyHealthCheck myHealthCheck = new MyHealthCheck();

        environment.healthChecks().register("my-health-check-class-1", myHealthCheck);//app will start but we can see status of this health check http://localhost:8081/healthcheck
        environment.healthChecks().register("health-check-2", new MyHealthCheck2());
        environment.jersey().register(myResource);

        //our resource lifecycle is managed by HTTP server
        final MyClientStartAndStop clientStartAndStop = new MyClientStartAndStop();
        environment.lifecycle().manage(clientStartAndStop);

        //https://www.dropwizard.io/en/latest/manual/core.html#managed-objects
        //Environment has inbuilt factory for ExecutorService and ScheduledExecutorService
        //TODO:(Hariom 5/24/2021): How it is working ?
        final ExecutorService executorService = environment.lifecycle()
                .executorService("my-executor-service")
                .maxThreads(10)
                .build();
        final ScheduledExecutorService scheduledExecutorService = environment.lifecycle()
                .scheduledExecutorService("my-scheduled-thread-pool")
                .build();
    }
}

/** Output:   http://localhost:8081/healthcheck
 {
 "deadlocks": {
 "healthy": true,
 "duration": 0,
 "timestamp": "2021-05-22T13:04:30.100+05:30"
 },
 "health-check-2": {
 "healthy": true,
 "message": "app is healthy - hariom",
 "duration": 0,
 "timestamp": "2021-05-22T13:04:30.100+05:30"
 },
 "my-health-check-class-1": {
 "healthy": false,
 "message": "not healthy - hariom",
 "duration": 0,
 "timestamp": "2021-05-22T13:04:30.100+05:30"
 }
 }
 */
