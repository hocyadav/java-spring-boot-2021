package io.hari.demo.dao;

import io.hari.demo.entity.Entity1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Entity1Dao extends BaseDao<Entity1>{
    @Query(value = "select * from entity1 order by name desc limit 0, 2", nativeQuery = true)
    List<Entity1> sqlMethod();

    @Query(value = "select * from entity1 order by ':sortBy' desc limit :pageNum, :perPage", nativeQuery = true)
    List<Entity1> sqlMethod2(String sortBy, Integer pageNum, Integer perPage);
}
