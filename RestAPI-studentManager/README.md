# SpringBoot-RestAPI-studentManager
A Restful API to add, view students , update and delete students .

Application- https://api-student-manager.herokuapp.com/home

Application that consumes the API -https://studentmanager.netlify.app/


**URL Mapping**

GET -> /students -> Get all student's data

GET -> /students/{id} -> Get student data with specific id 

POST -> /students -> Add new student 

PUT -> /students -> Update student

DELETE ->/students/{id} -> Delete student data wirh specific id


**Technologies used**

 Spring Boot
 
 Spring Data JPA (with hibernate)
 
 Postman (for testing the API)
 
 ReactJS & ReactStrap (for front end)


**Requirements**

Postman or any other API testing tool 

This application runs on java 11 , in case java version is lower(must be greater than 1.8) just change the java version in pom.xml as per need

**STEPS TO SETUP**

1. Download the project

2. Import the project to IDE

3. Create a MySql Database with any name 

4. open "/src/main/java/app.properties" and

   -> set spring.datasource.url=your database url
   
   -> set spring.datasource.username=your database username
   
   -> set spring.datasource.password=your database password
   
5. Run as Spring Boot Application 
