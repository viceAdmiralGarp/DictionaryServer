package com.mmdev.dictionaryy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsControllerManager {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({EntityNotFoundException.class})
	public String handleEntityNotFound(EntityNotFoundException e) {
		return e.getMessage();
	}
}