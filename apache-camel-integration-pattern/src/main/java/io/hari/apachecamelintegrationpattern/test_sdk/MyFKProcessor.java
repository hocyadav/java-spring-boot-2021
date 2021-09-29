package io.hari.apachecamelintegrationpattern.test_sdk;

import io.hari.apachecamelintegrationpattern.test_sdk.entity.ReplayResponse;
import io.hari.apachecamelintegrationpattern.test_sdk.entity.RequestEntity;
import io.hari.apachecamelintegrationpattern.test_sdk.entity.ResponseEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Component
@Slf4j
public class MyFKProcessor implements Processor {
    @Value("${hostname}")
    String HOSTNAME;
    @Value("${port}")
    Integer PORT;
    @Value("${scheme}")
    String SCHEME;
    @Value("${gor_index_name}")
    String INDEX_NAME;

    @Override
    public void process(Exchange exchange) throws Exception {
        Entity entity = printInputPipelineData(exchange);

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(HOSTNAME, PORT, SCHEME)));
        checkHealth(restHighLevelClient);
        if (!indexPresent(restHighLevelClient, INDEX_NAME)) {
            createNewESIndex(restHighLevelClient, INDEX_NAME);
        }

        entity.getStringBuilderList().forEach(stringBuilder -> {
            if (stringBuilder.toString().charAt(0) == '1') {
                System.out.println("--> 1");
                createRequestESDoc(restHighLevelClient, createRequest(stringBuilder));
            }
            if (stringBuilder.toString().charAt(0) == '2') {
                createResponseESDoc(restHighLevelClient, createResponse(stringBuilder));
            }
            if (stringBuilder.toString().charAt(0) == '3') {
                System.out.println("--> 3");
                createReplayResponseESDoc(restHighLevelClient, createReplayResponse(stringBuilder));
            }
        });
    }

    @SneakyThrows
    private void createRequestESDoc(RestHighLevelClient restHighLevelClient, RequestEntity requestEntity) {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id(UUID.randomUUID().toString())
                .source("postDate", new Date(),
                        "type", requestEntity.getType(),
                        "uniqueId", requestEntity.getUniqueId(),
                        "map",requestEntity.getMap(),
                        "message", "message 123");
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    private void createResponseESDoc(RestHighLevelClient restHighLevelClient, ResponseEntity requestEntity) {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id(UUID.randomUUID().toString())
                .source("postDate", new Date(),
                        "type", requestEntity.getType(),
                        "uniqueId", requestEntity.getUniqueId(),
                        "map",requestEntity.getMap(),
                        "message", "message 123");
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    private void createReplayResponseESDoc(RestHighLevelClient restHighLevelClient, ReplayResponse requestEntity) {
        Object[] objects = {"postDate", new Date(),
                "type", requestEntity.getType(),
                "uniqueId", requestEntity.getUniqueId(),
                "map", requestEntity.getMap(),
                "message", "message 123"};
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id(UUID.randomUUID().toString())
                .source(objects);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    private void createDoc(RestHighLevelClient restHighLevelClient, StringBuilder stringBuilder) {
        try {
            createNewDoc_working(restHighLevelClient, stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean indexPresent(RestHighLevelClient restHighLevelClient, String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("index exists = " + indexName + " -- " + exists);
        return exists;
    }

    private Entity printInputPipelineData(Exchange exchange) {
        log.info("MyProcessorToPrint log");
        Object body = exchange.getIn().getBody();
        Entity entity = Entity.class.cast(body);
        System.out.println("exchange.getIn().getBody() entity = " + entity);

        //print
//        entity.getStringBuilderList().forEach(stringBuilder -> {
//            System.out.println("stringBuilder = \n" + stringBuilder.toString());
//            if (stringBuilder.toString().charAt(0) == '1') {
//                System.out.println("--> 1");
//                RequestEntity requestEntity = createRequest(stringBuilder);
//            }
//            if (stringBuilder.toString().charAt(0) == '2') {
//                System.out.println("--> 2");
//            }
//            if (stringBuilder.toString().charAt(0) == '3') {
//                System.out.println("--> 3");
//            }
//        });
        return entity;
    }

    private RequestEntity createRequest(StringBuilder stringBuilder) {
        RequestEntity requestEntity = RequestEntity.builder()
                .uniqueId(stringBuilder.toString().substring(2, "5567acb1fee1c5854594fafba24a0f2ad57ce69d".length() + 2))
                .build();
//        System.out.println("stringBuilder -- = " + stringBuilder.toString());
        Map<String, Object> map = new HashMap<>();
        String[] split = stringBuilder.toString().split("\\r?\\n");
        Arrays.stream(split).forEach(s -> {
            String parts[] = s.split(" ", 2);
            map.put(parts[0], parts[1]);
        });
        requestEntity.setMap(map);
//        System.out.println("requestEntity = " + requestEntity);
        return requestEntity;
    }

    private ResponseEntity createResponse(StringBuilder stringBuilder) {
        ResponseEntity responseEntity = ResponseEntity.builder()
                .uniqueId(stringBuilder.toString().substring(2, "5567acb1fee1c5854594fafba24a0f2ad57ce69d".length() + 2))
                .build();
//        System.out.println("stringBuilder -- = " + stringBuilder.toString());
        //break into line
        //split by space and add to map
        Map<String, Object> map = new HashMap<>();
        String[] split = stringBuilder.toString().split("\\r?\\n");
        Arrays.stream(split).forEach(s -> {
            String parts[] = s.split(" ", 2);
            if (parts.length == 2) map.put(parts[0], parts[1]);
        });
        responseEntity.setMap(map);
//        System.out.println("responseEntity = " + responseEntity);
        return responseEntity;
    }

    private ReplayResponse createReplayResponse(StringBuilder stringBuilder) {
        ReplayResponse responseEntity = ReplayResponse.builder()
                .uniqueId(stringBuilder.toString().substring(2, "5567acb1fee1c5854594fafba24a0f2ad57ce69d".length() + 2))
                .build();
//        System.out.println("stringBuilder -- = " + stringBuilder.toString());
        //break into line
        //split by space and add to map
        Map<String, Object> map = new HashMap<>();
        String[] split = stringBuilder.toString().split("\\r?\\n");
        Arrays.stream(split).forEach(s -> {
            String parts[] = s.split(" ", 2);
            if (parts.length == 2) map.put(parts[0], parts[1]);
        });
        responseEntity.setMap(map);
//        System.out.println("responseEntity = " + responseEntity);
        return responseEntity;
    }

    private void createNewDoc_working(RestHighLevelClient restHighLevelClient, String message) throws IOException {
        //m1 : working
//        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
//                .id("2")
//                .source("user", "kimchy3",
//                        "postDate", new Date(),
//                        "message", "trying out Elasticsearch3");
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .id(UUID.randomUUID().toString())
                .source("postDate", new Date(),
                        "message", message);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        //m2 : working
//        XContentBuilder xContentBuilder = jsonBuilder()
//                .startObject()
//                .field("user", "kimchy4 m2 2")
//                .field("postDate", new Date())
//                .field("message", "trying out Elasticsearch4 using x content builder")
//                .endObject();
//        IndexRequest indexRequest1 = new IndexRequest(INDEX_NAME).source(xContentBuilder);
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest1, RequestOptions.DEFAULT);
//        System.out.println("indexResponse = " + indexResponse);


    }

    private void createNewESIndex(RestHighLevelClient restHighLevelClient, String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
        );
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("userId", message);
        properties.put("name", message);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);
        CreateIndexResponse indexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("clusterHealthResponse id: " + indexResponse.index());
    }

    private void checkHealth(RestHighLevelClient restHighLevelClient) throws IOException {
        ClusterHealthResponse clusterHealthResponse = restHighLevelClient.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
        System.out.println("clusterHealthResponse.getStatus() = " + clusterHealthResponse.getStatus());
        System.out.println("clusterHealthResponse = " + clusterHealthResponse);
    }
}
