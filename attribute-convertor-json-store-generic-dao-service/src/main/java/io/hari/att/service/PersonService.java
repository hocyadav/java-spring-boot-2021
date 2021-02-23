package io.hari.att.service;

import io.hari.att.dao.BaseDao;
import io.hari.att.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService<Person> {
    public PersonService(BaseDao<Person> dao) {
        super(dao);
    }
}
