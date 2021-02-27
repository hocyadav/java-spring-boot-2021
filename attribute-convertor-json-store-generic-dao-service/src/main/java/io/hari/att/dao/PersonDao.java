package io.hari.att.dao;

import io.hari.att.entity.AgeType;
import io.hari.att.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends BaseDao<Person> {
    List<Person> findAllByAgeType(AgeType ageType);

    @Query(value = "SELECT * FROM PERSON where name = ?1", nativeQuery = true)//by default native query is false
    Person myOwnQueryNative(String name);


    @Query("SELECT p FROM Person p")
    List<Person> finadAllByQuery();

    List<Person> createOwnMethodWithSQL();//create new class with Impl + then write impl body in that class and add @Compnent above that class

    @Query("select p from Person p")
    List<Person> findAll2();//working

    @Query(value = "select * from person", nativeQuery = true)
    List<Person> findAll2native();//working

    @Query(value = "select * from person p where p.name = :name", nativeQuery = true)
    List<Person> findPersonSQLByName(String name);//working

    @Query(value = "select * from person p where p.name like %:name% ",
            nativeQuery = true)//working
//    @Query(value = "select * from person p where p.name like :name% ", nativeQuery = true)//working
//    @Query(value = "select * from person p where p.name like :name ", nativeQuery = true)//working
    List<Person> findPersonSQLByName2(String name);//working



}
