package com.StudentManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.StudentManager.entities.Student;
import com.StudentManager.service.StudentService;

@RestController
@CrossOrigin(origins = "*")
public class MainController 
{
	@Autowired
	StudentService studentService; 
	
	@GetMapping(value = "/home")
	public ResponseEntity<String> home()
	{
		String msg="Welcome To Student Data Manager Application";
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Student manger Application");
		return new ResponseEntity<String>(msg, header,HttpStatus.OK);
	}
	
	
	/***Getting all Students*/
	@GetMapping(value = "/students")
	public ResponseEntity<List<Student>> getStudents()
	{
		List<Student>studentList=studentService.getStudents();
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Getting All student");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(studentList);
	}
	
	
	/**Getting Student by id*/
	
	@GetMapping(value = "/students/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable int studentId)
	{
		Student student =studentService.getStudent(studentId);
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Getting student by Id");
		return new ResponseEntity<Student>(student, header,HttpStatus.OK);
		
	}
	
	
	/**Adding Student*/
	
	@PostMapping(value = "/students")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student addedStudent=studentService.addStudent(student);
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Adding a Student");
		return new ResponseEntity<Student>(addedStudent,header,HttpStatus.CREATED);
	}
	
	
	/**Update Student*/
	
	@PutMapping(value="/students")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student)
	{
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Updating Student Data");
		Student updatedStudent=studentService.updateStudent(student);
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedStudent);
	}
	
	
	/**Delete Student*/
	
	@DeleteMapping(value = "/students/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int studentId)
	{
		HttpHeaders header= new HttpHeaders();
		header.add("desc", "Deleting Student Data");
		studentService.deleteStudent(studentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(header).build();
	}
}
