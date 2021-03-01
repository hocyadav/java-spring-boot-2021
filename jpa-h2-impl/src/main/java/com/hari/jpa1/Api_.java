package com.hari.jpa1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class Api_ {

	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Student> getAllStudents() {
		return studentDAO.findAll();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Student> getById(@PathVariable("id") int id) {
		Optional<Student> findById = studentDAO.findById(id);
		return findById;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveStudent(@RequestBody Student student) {
		studentDAO.save(student);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable("id") int id) {
		//check if present then only delete
		Optional<Student> findById = studentDAO.findById(id);
		if(findById.isPresent()) {
			studentDAO.deleteById(id);
		} else {
			System.err.println("Student not present with id "+id);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateStudent(@RequestBody Student student) {
		Optional<Student> findById = studentDAO.findById(student.getRollNumber());
		if(findById.isPresent()) {
			Student student2 = findById.get();
			student2.setName(student.getName());
			studentDAO.save(student2);
		}else {
			System.err.println("student not present..");
		}
	}
	
	
}
