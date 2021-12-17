package io.hari.entitylld.dao;

import io.hari.entitylld.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDap extends JpaRepository<Long, Person> {
}
