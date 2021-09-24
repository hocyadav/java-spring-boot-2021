package io.hari.apachecamelintegrationpattern;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Comment @Component to disable route
 */
@SpringBootApplication
public class ApacheCamelIntegrationPatternApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(ApacheCamelIntegrationPatternApplication.class, args);
    }
}
