package io.hari.att.dao;

import io.hari.att.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PersonDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PersonDao personDao;

//    @SuppressWarnings("unused")
    public List<Person> createOwnMethodWithSQL() {//this method name same as in dao interface
        System.err.println("entityManager = " + entityManager);

//        String nativeSQL = "select * from person";
//        final Query nativeQuery = entityManager.createNativeQuery(nativeSQL, Person.class);
//        final List<Person> resultList = nativeQuery.getResultList();

//        List<Object[]> list = nativeQuery.getResultList();
//        System.err.println("nativeQuery resultList = " + list);
        return null;
    }
}
