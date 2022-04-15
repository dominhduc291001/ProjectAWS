package com.StudentManager.service;

import java.util.List;

import com.StudentManager.entities.Student;
import com.StudentManager.exception.DataNotFoundException;

public interface StudentService 
{
	public List<Student> getStudents();
	
	public Student getStudent(int studentId) throws DataNotFoundException;
	
	public Student addStudent(Student student) ;
	
	public Student updateStudent(Student student) ;
	
	public void deleteStudent(int studentId) throws DataNotFoundException;
}
