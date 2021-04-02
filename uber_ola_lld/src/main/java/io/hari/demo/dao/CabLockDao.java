package io.hari.demo.dao;

import io.hari.demo.entity.CabLock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
@Repository
public interface CabLockDao extends BaseDao<CabLock>{

    @Query(value = "select * from cab_lock where lock_status = 'available'", nativeQuery = true)
    List<CabLock> findAvailableCabs();

    CabLock findByCabId(Long cabId);
}
