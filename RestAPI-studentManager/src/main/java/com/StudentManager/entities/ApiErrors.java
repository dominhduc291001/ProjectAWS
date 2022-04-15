package com.StudentManager.entities;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiErrors 
{
	String message;
	String details;
	HttpStatus status;
	LocalDateTime timeStamp;
	
	
	public ApiErrors() {
		super();
	}
	
	public ApiErrors(String message, String details, HttpStatus status, LocalDateTime timeStamp) {
		super();
		this.message = message;
		this.details = details;
		this.status = status;
		this.timeStamp = timeStamp;
	}
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ApiErrors [message=" + message + ", details=" + details + ", status=" + status + ", timeStamp="
				+ timeStamp + "]";
	}
}
