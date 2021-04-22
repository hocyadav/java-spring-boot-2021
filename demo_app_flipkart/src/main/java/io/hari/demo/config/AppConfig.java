package io.hari.demo.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {
    String simpleKey;
    List<String> list;
    Map<String, String> map;
    Map<String, ClassA> object;

    @Getter
    @Setter
    @ToString
    public static class ClassA {
        String username;
        String password;
        List<String> list;
        Map<String, String> map;
    }
}
