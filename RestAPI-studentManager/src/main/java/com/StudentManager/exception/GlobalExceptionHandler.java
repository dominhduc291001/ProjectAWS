package com.StudentManager.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.StudentManager.entities.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

	/**Handling Generic Exceptions*/  //Note these methods are overridden and have been annotated with @ExceptionHandler internally
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String message=ex.getMessage();
		String details="Request Method not Supported";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String message=ex.getMessage();
		String details="Media Not Supported";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		String details="Path variable is missing";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);

	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		String details="Request Parameter is missing";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		String details="This is a type mismatch";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message=ex.getMessage();
		String details="Request body is not readable";
		ApiErrors errors= new ApiErrors(message,details,status,LocalDateTime.now());
		
		return ResponseEntity.status(status).body(errors);

	}
	
	/**Handling Custom Exceptions */  
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex)
	{
		String message=ex.getMessage();
		String details="Student not found";
		ApiErrors errors= new ApiErrors(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	/**Fallback handler(handles all exceptions)*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception ex)
	{
		String message=ex.getMessage();
		String details="Other Exception";
		ApiErrors errors= new ApiErrors(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
/**
    NOTE(imp):We are not adding @ExceptionHandler to generic Exception handler methods because,These 
    methods are already declared in "ResponseEntityExceptionHandler" and we are just Overriding them,
    they have been internally annotated with @ExceptionHandler to handle their respective Exceptions.
    They also come with their respective message and HttpStatus.BUT when we use custom Exceptions we
    NEED TO add @ExceptionHandler with each handler method, also(we need to pass the error message
    while throwing the Exception) 
    
    @ControllerAdvice-@ControllerAdvice is an annotation provided by Spring allowing you to write global
     code that can be applied to a wide range of controllers — varying from all controllers to a chosen
     package or even a specific annotation.It is a specialization of the @Component annotation here we are 
     using it to handle exceptions across the whole application in one global handling component. It can
     be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping.
     
	@ExceptionHandler-This Annotation is used with a method that is being used to handle an Exception
	(basically the body of these methods handles the exceptions by sending a Custom Error as Response)
	This annotation takes Exception class as argument that we want to handle in the defined handler
	method, These exception handler methods are just like other request handler methods,the only 
	difference is that these methods build and return custom or generic error responses.
	
	
*/
