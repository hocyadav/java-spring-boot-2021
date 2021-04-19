package io.hari.democonnectors.mysql;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author hayadav
 * @create 4/19/2021
 */
@Slf4j
public class MySqlConnector {

    @Test
    public void testConnection() {
        final String schemaName = "testdb";
        final String url = "jdbc:mysql://localhost:3306/" + schemaName + "?useSSL=false";
        final String username = "root";
        final String password = "hariom";
        final Connection connection = createConnection(url, username, password);
        closeConnection(connection);
    }

    @SneakyThrows
    public Connection createConnection(String url, String username, String password) {
        log.info("MySqlConnector.createConnection");
        final Connection connection = DriverManager.getConnection(url, username, password);
        final Statement connectionStatement = connection.createStatement();
        final ResultSet resultSet = connectionStatement.executeQuery("SELECT VERSION()");
        if (resultSet.next()) {
            System.out.println("resultSet.getString(1) = " + resultSet.getString(1));
        }
        resultSet.close();
        connectionStatement.close();
        return connection;
    }

    @SneakyThrows
    public void closeConnection(Connection connection) {
        log.info("MySqlConnector.closeConnection");
        connection.close();
    }

}
