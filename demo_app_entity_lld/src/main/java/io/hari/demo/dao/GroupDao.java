package io.hari.demo.dao;

import io.hari.demo.entities.Group;
import io.hari.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<Long, Group> {
}
