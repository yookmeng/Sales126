package com.SpringMVC.handler;

import com.SpringMVC.exceptions.ServiceException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 
@ControllerAdvice
public class ExceptionHandlerAdvice {
    @SuppressWarnings("rawtypes")
	@ExceptionHandler(ServiceException.class)
    public ResponseEntity handleException(ServiceException e) {
        // log exception 
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }   
}
