package com.example.demo;

import java.util.List;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;

@SpringBootApplication
public class PaginationApplication implements CommandLineRunner{

	@Autowired
	StudentDAO studentDB;

	@Override
	public void run(String... args) throws Exception {
		studentDB.save(new Student("hariom", "yadav"));
		studentDB.save(new Student("om", "yadav"));
		studentDB.save(new Student("omprakash", "yadav"));
		studentDB.save(new Student("chandan", "yadav"));
		studentDB.save(new Student("swarn", "lata"));
		studentDB.save(new Student("rajat", "raj"));
		studentDB.save(new Student("sudhanshu", "raj"));
		List<Student> all = studentDB.findAll();
		System.out.println(all);

		/** Pagination **/
		Pageable page0 = PageRequest.of(0, 20);//0th page, per page size 20 
		List<Student> findAllByLastName = studentDB.findAllByLastName("yadav", page0);
		System.out.println(findAllByLastName);

		Pageable page1 = PageRequest.of(1, 2);//i want 1st page, each page size 2
		List<Student> findAllByLastName1 = studentDB.findAllByLastName("yadav", page1);
		System.out.println(findAllByLastName1);

		/** Sorting **/
		Pageable page00 = PageRequest.of(0, 3, Sort.by("firstName"));
		List<Student> list = studentDB.findAllByLastName("raj", page00);
		System.out.println("\n"+list);
		
		Pageable page01 = PageRequest.of(0, 3, Sort.by("firstName").descending());
		List<Student> list1 = studentDB.findAllByLastName("raj", page01);
		System.out.println("\n"+list1);
		
		Pageable page02 = PageRequest.of(0, 10, Sort.by("firstName").and(Sort.by("lastName")));
		Page<Student> list2 = studentDB.findAll(page02);
		System.out.println("\n"+list2);
	}

	public static void main(String[] args) {
		SpringApplication.run(PaginationApplication.class, args);
	}
}
