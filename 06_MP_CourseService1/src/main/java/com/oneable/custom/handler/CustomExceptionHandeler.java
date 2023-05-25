package com.oneable.custom.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oneable.exception.CourseNotFoundException;
import com.oneable.model.ErrorMessage;

@RestControllerAdvice
public class CustomExceptionHandeler {

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorMessage> processCourseNotFoundException(
			CourseNotFoundException cnfe){
		
		ErrorMessage em=new ErrorMessage(
				cnfe.getMessage(),
				new Date().toString(), 
				"Course", "NOT EXIST");
		
		return new ResponseEntity<ErrorMessage>(em,HttpStatus.NOT_FOUND) ;
	}
}
