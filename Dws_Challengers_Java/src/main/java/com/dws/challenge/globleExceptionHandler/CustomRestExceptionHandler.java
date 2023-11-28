package com.dws.challenge.globleExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ InvalidTransactionException.class })
	public ResponseEntity<Object> handleAll(InvalidTransactionException ex, WebRequest request) {
	    return new ResponseEntity<Object>(
	      ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ DuplicateAccountIdException.class })
	public ResponseEntity<Object> handleDuplicateAccountException(DuplicateAccountIdException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
