package com.StudentManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentManager.entities.Student;

public interface StudentDao extends JpaRepository<Student,Integer>
{
	public Student findById(int id);
}
