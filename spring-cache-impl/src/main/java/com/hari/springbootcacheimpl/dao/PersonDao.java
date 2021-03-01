package com.hari.springbootcacheimpl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hari.springbootcacheimpl.entity.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {}
