package com.es.elasticsearchspringboot.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author HariomYadav
 * @since 26/09/20
 */
@Configuration //create instance / bean
@EnableElasticsearchRepositories (basePackages = "com.es.elasticsearchspringboot.dao")
@ComponentScan (basePackages = { "com.es.elasticsearchspringboot.service" })
public class EsConfig {
    @Bean
    public RestHighLevelClient client() {
        System.err.println("EsConfig.client");
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        System.err.println("EsConfig.elasticsearchTemplate");
        return new ElasticsearchRestTemplate(client());
    }
}
