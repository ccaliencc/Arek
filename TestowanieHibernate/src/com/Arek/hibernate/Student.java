package com.Arek.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "Student")
public class Student
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	private String Student;

	public int getID()
	{
		return ID;
	}

	public void setID(int iD)
	{
		ID = iD;
	}

	public String getStudent()
	{
		return Student;
	}

	public void setStudent(String student)
	{
		Student = student;
	}
	
	
	


}
