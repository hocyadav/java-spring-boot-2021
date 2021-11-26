package io.hari.annotationlombokothers.spring_config.method2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data//this is for access value, access yml value
@Configuration
@Component
@ConfigurationProperties(value = "myapp.config")
public class MyYamlConfig01 {
    String name;
    String email;

    //use class object as field, inner class or other class entity
    //explore : create inner static class as object
}
/**
 myapp.config.name="hariom"
 myapp.config.email="hariom@fk.com"
 */
