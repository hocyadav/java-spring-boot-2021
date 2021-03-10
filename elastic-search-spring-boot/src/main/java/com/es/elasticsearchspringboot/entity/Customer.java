package com.es.elasticsearchspringboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;

/**
 * @author HariomYadav
 * @since 26/09/20
 */
@Document(indexName = "customer_index2")
@Data
public class Customer {
    @Id
    int customerId;
    String firstName;
    String lastName;
    int age;
}
/* see all data : http://localhost:9200/customer_index2/_search */
