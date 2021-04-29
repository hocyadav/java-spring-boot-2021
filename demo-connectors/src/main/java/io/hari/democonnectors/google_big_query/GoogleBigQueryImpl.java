package io.hari.democonnectors.google_big_query;

import com.google.cloud.bigquery.connection.v1.Connection;
import com.google.cloud.bigquery.connection.v1.ConnectionName;
import com.google.cloud.bigquery.connection.v1.GetConnectionRequest;
import com.google.cloud.bigqueryconnection.v1.ConnectionServiceClient;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * @Author hayadav
 * @create 4/26/2021
 */
public class GoogleBigQueryImpl {//TODO : testing pending

    @SneakyThrows
    public void createGBQConnection() {
        String projectId = "MY_PROJECT_ID";
        String location = "MY_LOCATION";
        String connectionId = "MY_CONNECTION_ID";
        getConnection(projectId, location, connectionId);
    }

    public static void getConnection(String projectId, String location, String connectionId)
            throws IOException {
        try (ConnectionServiceClient client = ConnectionServiceClient.create()) {
            ConnectionName name = ConnectionName.of(projectId, location, connectionId);
            GetConnectionRequest request =
                    GetConnectionRequest.newBuilder().setName(name.toString()).build();
            Connection response = client.getConnection(request);
            System.out.println("Connection info retrieved successfully :" + response.getName());
        }
    }
    public void closeGBQConnection() {

    }

}
