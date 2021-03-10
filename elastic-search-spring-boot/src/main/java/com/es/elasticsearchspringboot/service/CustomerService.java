package com.es.elasticsearchspringboot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import com.es.elasticsearchspringboot.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author HariomYadav
 * @since 26/09/20
 */
@Service
public class CustomerService {
    @Autowired
    private ObjectMapper objectMapper;

    /* call client in every time - for command line runner */

    //    RestHighLevelClient client =
    //            new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    /* working */
    public List<Customer> getAllDataFromES_UsingDSL_matchAllQuery() throws IOException {
        System.err.println("CustomerService.getAllDataFromES_UsingDSL_matchAllQuery");

        final HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        final List<Customer> customerListResult;
        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost))) {

            /* 1. search req - start */
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("customer_index2");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            QueryBuilder query = QueryBuilders.matchAllQuery();
            searchSourceBuilder.query(query);

            searchRequest.source(searchSourceBuilder);
            /* search req - end */

            customerListResult = new ArrayList<>();

            /* 2. search response - start */
            SearchResponse searchResponse;
            try {
                searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
                if (searchResponse.getHits().getTotalHits().value > 0) {
                    final SearchHit[] hits = searchResponse.getHits().getHits();
                    for (SearchHit hit : hits) {
                        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                        final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                        customerListResult.add(customer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customerListResult;
    }

    /* working */
    public List<Customer> filterDataFromES_usingDSL_matchQuery() throws IOException {
        System.err.println("CustomerService.filterDataFromES_usingDSL_matchQuery");

        final HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));

        /* 1. search req - start */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer_index2");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder qBuilder = QueryBuilders.matchQuery("firstName", "hari");
        QueryBuilder qBuilder2 = QueryBuilders.matchQuery("age", "31");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(qBuilder).must(qBuilder2);

        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);
        /* search req - end */

        final List<Customer> customerListResult = new ArrayList<>();

        /* 2. search response - start */
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                final SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //TODO :
                    final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                    customerListResult.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return customerListResult;
    }

    public List<Customer> filterDataFromES_usingDSL_matchQuery_chandan() throws IOException {
        System.err.println("CustomerService.filterDataFromES_usingDSL_matchQuery");
        System.err.println("CustomerService.filterDataFromES_usingDSL_matchQuery");

        HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));

        /* 1. search req - start */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer_index2");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder qBuilder = QueryBuilders.matchQuery("firstName", "chandan");
        QueryBuilder queryBuilder1 = QueryBuilders.matchQuery("lastName", "yadav");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(qBuilder).must(queryBuilder1);

        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);
        /* search req - end */

        final List<Customer> customerListResult = new ArrayList<>();

        /* 2. search response - start */
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                final SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //TODO :
                    final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                    customerListResult.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return customerListResult;
    }

    /* working */
    public List<Customer> filterAGE_DataFromES_usingDSL_matchQuery() throws IOException {
        System.err.println("CustomerService.filterAGE_DataFromES_usingDSL_matchQuery");

        HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));

        /* 1. search req - start */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer_index2");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder queryBuilder1 = QueryBuilders.matchQuery("age", "31");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(queryBuilder1);

        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);
        /* search req - end */

        final List<Customer> customerListResult = new ArrayList<>();

        /* 2. search response - start */
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                final SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //TODO :
                    final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                    customerListResult.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return customerListResult;
    }

    /* working */
    public List<Customer> filterAllDataFromES_usingDSL_regexpQuery(String name) throws IOException {
        System.err.println("CustomerService.filterAllDataFromES_usingDSL_regexpQuery");

        final HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));

        /* 1. search req - start */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer_index2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        String inputStr = ".*" + name + "*.";
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("firstName", inputStr);

        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);
        /* search req - end */

        final List<Customer> customerListResult = new ArrayList<>();

        /* 2. search response - start */
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                final SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //TODO :
                    final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                    customerListResult.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return customerListResult;
    }

    /* working */
    public List<Customer> filterAllDataFromES_usingDSL_regexpQuery_multiMatch(String name) {
        System.err.println("CustomerService.filterAllDataFromES_usingDSL_regexpQuery_multiMatch");

        final HttpHost httpHost = new HttpHost("localhost", 9200, "http");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));

        /* 1. search req - start */
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer_index2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(name).field("firstName").field("lastName")
                                                                     .type(MultiMatchQueryBuilder.DEFAULT_TYPE);

        searchSourceBuilder.query(multiMatchQueryBuilder);

        searchRequest.source(searchSourceBuilder);
        /* search req - end */

        final List<Customer> customerListResult = new ArrayList<>();//TODO : new feature - implementation pending

        /* 2. search response - start */
        SearchResponse searchResponse;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                final SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    //TODO :
                    final Customer customer = objectMapper.convertValue(sourceAsMap, Customer.class);
                    customerListResult.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerListResult;
    }

    /* pending  */
    public void queryBuilder1() {
        /* 1. create QUERY */
        QueryBuilder qBuilder = QueryBuilders.matchQuery("firstName", "hari");
        QueryBuilder qBuilder2 = QueryBuilders.matchQuery("age", "30");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(qBuilder).must(qBuilder2);

        /* 2. create QUERY request */
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        /* 3. execute QUERY - get response as list */
    }

}
