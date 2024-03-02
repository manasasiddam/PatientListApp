package com.Methods.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler (value=PatientException.class)
	public ResponseEntity<ExceptionBean>handlePatientException(PatientException pe){
		
		String message = pe.getMessage();
		ExceptionBean eb = new ExceptionBean();
		eb.setCode("MNST1009");
		eb.setMsg(message);

		return new ResponseEntity<>(eb,HttpStatus.BAD_REQUEST);
		
	}
	

}
