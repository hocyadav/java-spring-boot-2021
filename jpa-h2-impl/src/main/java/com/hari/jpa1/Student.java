package com.hari.jpa1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue
	int rollNumber;
	@Column
	String name;
	
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
	
}
