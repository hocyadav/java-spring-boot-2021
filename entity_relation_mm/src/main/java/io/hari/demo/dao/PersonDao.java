package io.hari.demo.dao;

import io.hari.demo.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends BaseDao<Person> {
    List<Person> findAllByAddress_Id(Long id);
}
