package com.es.elasticsearchspringboot;

import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import com.es.elasticsearchspringboot.entity.Customer;
import com.es.elasticsearchspringboot.service.CustomerService;

@SpringBootApplication
public class ElasticSearchSpringBootApplication implements CommandLineRunner {
    RestHighLevelClient client = null;

    @Autowired
    CustomerService customerService;

    public static void main(String[] args) {
        System.err.println("ElasticSearchSpringBootApplication.main");
        SpringApplication.run(ElasticSearchSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("ElasticSearchSpringBootApplication.run");
        //createClientObject();
        //deleteFun();

        List<Customer> customerList = customerService.getAllDataFromES_UsingDSL_matchAllQuery();
        customerList.forEach(System.out::println);

        List<Customer> customerList1 = customerService.filterDataFromES_usingDSL_matchQuery();
        customerList1.forEach(System.out::println);

        List<Customer> customerList2 = customerService.filterAGE_DataFromES_usingDSL_matchQuery();
        customerList2.forEach(System.out::println);

        List<Customer> customerList3 = customerService.filterAllDataFromES_usingDSL_regexpQuery("ha");
        customerList3.forEach(System.out::println);

        List<Customer> customerList4 = customerService.filterAllDataFromES_usingDSL_regexpQuery_multiMatch("yadav");
        customerList4.forEach(System.out::println);

        customerService.filterDataFromES_usingDSL_matchQuery_chandan().forEach(System.out::println);
    }

    /* working */
    public void createClientObject() throws IOException {
        System.err.println("ElasticSearchSpringBootApplication.createClientObject");

        /* create clinet object to call ES database : working - check http://localhost:9200/people/_search */
        Query searchQuery = new NativeSearchQueryBuilder().withFilter(regexpQuery("title", ".*data.*")).build();

        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
        client = RestClients.create(clientConfiguration).rest();

        /* create new index if not present & add 1 data : working - check ES url for new data */
        String jsonObject = "{\"age\":20,\"dateOfBirth\":1471466076564,\"fullName\":\"John Doe\"}";
        IndexRequest request = new IndexRequest("people");
        request.source(jsonObject, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        String index = response.getIndex();
        long version = response.getVersion();
        System.out.println("index = " + index);
        System.out.println("version = " + version);
        System.out.println("response = " + response.getResult().toString());

    }

    /* working ?? - need to explore more  */
    private void deleteFun() throws IOException {
        String jsonObject = "{\"age\":10,\"dateOfBirth\":1471455886564,\"fullName\":\"Johan Doe\"}";
        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(jsonObject, XContentType.JSON);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        String id = response.getId();
	    System.out.println("id = " + id);

        GetRequest getRequest = new GetRequest("people");
        getRequest.id(id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());

        DeleteRequest deleteRequest = new DeleteRequest("people");
        deleteRequest.id(id);

        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
	    System.out.println("deleteResponse = " + deleteResponse.toString());
    }
}
