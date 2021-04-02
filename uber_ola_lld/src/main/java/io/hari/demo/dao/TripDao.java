package io.hari.demo.dao;

import io.hari.demo.entity.Trip;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Repository
public interface TripDao extends BaseDao<Trip>{
    List<Trip> findAllByUserId(Long userId);
    List<Trip> findAllByCabId(Long cabId);
}
