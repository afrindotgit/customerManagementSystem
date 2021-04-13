package com.customermanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public final ResponseEntity<ErrorDetails> userRegistrationException(CustomerException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), 404L);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

}
