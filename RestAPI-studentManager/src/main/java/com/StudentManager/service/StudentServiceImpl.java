package com.StudentManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentManager.dao.StudentDao;
import com.StudentManager.entities.Student;
import com.StudentManager.exception.DataNotFoundException;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> getStudents() 
	{
		List<Student>studentList=studentDao.findAll();
		return studentList;
	}

	@Override
	public Student getStudent(int studentId) 
	{
		boolean exists=studentDao.existsById(studentId);
		if(exists)
		{
			Student student=studentDao.findById(studentId);
			return student;
		}
		else
		{
			throw new DataNotFoundException("Student with id "+studentId+" not found");
		}
	}

	@Override
	public Student addStudent(Student student) 
	{
		Student addedStudent=studentDao.save(student);
		return addedStudent;
	}

	@Override
	public Student updateStudent(Student student) 
	{
		Student updatedStudent=studentDao.save(student);
		return updatedStudent;
	}

	@Override
	public void deleteStudent(int studentId) 
	{
		boolean exists=studentDao.existsById(studentId);
		if(exists)
		{
			studentDao.deleteById(studentId);
		}
		else
		{
			throw new DataNotFoundException("Student with id "+studentId+" not found");
		}
	}

}
