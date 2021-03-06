package com.hari.jpa1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Student {
	@Id
	@GeneratedValue
	int rollNumber;
	@Column
	String name;

	StudentType studentType;
	
	public Student(int rollNumber, String name) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
	}

	public Student() {}
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return rollNumber == student.rollNumber && name.equals(student.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rollNumber, name);
	}

	@Override
	public String toString() {
		return "Student{" +
				"rollNumber=" + rollNumber +
				", name='" + name + '\'' +
				'}';
	}
}
