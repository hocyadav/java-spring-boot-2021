package io.hari.democonnectors.mysql;

/**
 * @Author hayadav
 * @create 4/19/2021
 */
public interface IConnector {
    void openDBConnection();
    void closeDBConnection();
}
