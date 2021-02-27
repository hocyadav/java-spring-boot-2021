package io.hari.demo.dao;

import io.hari.demo.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends BaseDao<Order> {
    List<Order> findAllByItems_id(Long id);
}
