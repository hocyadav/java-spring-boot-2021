package io.hari.att.dao;

import io.hari.att.entity.AgeType;
import io.hari.att.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends BaseDao<Person> {
    List<Person> findAllByAgeType(AgeType ageType);
}
