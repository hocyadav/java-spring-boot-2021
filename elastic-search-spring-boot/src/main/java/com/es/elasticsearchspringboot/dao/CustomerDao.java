package com.es.elasticsearchspringboot.dao;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.es.elasticsearchspringboot.entity.Customer;

/**
 * @author HariomYadav
 * @since 26/09/20
 */
@Repository
public interface CustomerDao extends ElasticsearchRepository<Customer, Integer> {
    List<Customer> findByFirstName(String firstName);

    List<Customer> findByFirstName(String firstName, PageRequest pageRequest);
}
