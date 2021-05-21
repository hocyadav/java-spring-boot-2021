package io.hari.redis.service;

import io.hari.redis.entity.Customer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author Hariom Yadav
 * @create 5/21/2021
 */
@Service
@CacheConfig(cacheNames = "MyServiceCache")//step 5
public class MyService {

    @Cacheable(key = "#id")//step 6 : use spring boot expression language
    public Customer findById(Long id) {
        return Customer.builder().name("customer-"+id).address("bangalore").build();
    }
}
