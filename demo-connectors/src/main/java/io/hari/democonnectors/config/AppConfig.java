package io.hari.democonnectors.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author hayadav
 * @create 4/20/2021
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {
    String simpleKey;
    Database source;
    Database target;
    RemoteMySQL remotemySQL;

    @Getter
    @Setter
    @ToString
    public static class Database {
        String datasource;
        String url;
        String username;
        String password;
        Schema schema;
    }

    @Getter
    @Setter
    @ToString
    public static class Schema {
        String schemaName;
        String tableName;
        List<String> tableColumns;
    }

    @Getter
    @Setter
    @ToString
    public static class RemoteMySQL {
        String username;
        String password;
        String server;
        Integer port;
        String databaseSchemaName;
    }
}

