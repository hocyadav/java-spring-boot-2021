package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Student;

//@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
	List<Student> findAllByLastName(String lastname,Pageable pageable);
}
