package config.config_factory;

import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Hariom Yadav
 * @create 28-05-2021
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyConfigFactory {
    @Min(2)
    @Max(10)
    private Integer threadPoolSize;


    public ExecutorService build(Environment environment) {
        final Integer threadPoolSize = getThreadPoolSize();
        System.out.println("threadPoolSize = " + threadPoolSize);
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() throws Exception {
                System.out.println("start manage override method inside factory");
            }

            @Override
            public void stop() throws Exception {
                System.out.println("shutdown executor service");
                executorService.shutdownNow();
            }
        });
        return executorService;
    }
}
