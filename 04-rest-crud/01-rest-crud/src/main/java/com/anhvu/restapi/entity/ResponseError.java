package com.anhvu.restapi.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.anhvu.restapi.exception.NotFoundException;
import com.anhvu.restapi.exception.RestError;

@ControllerAdvice
public class ResponseError {
    @ExceptionHandler
    public ResponseEntity<RestError> handleException(NotFoundException exc) {
        RestError error = new RestError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessages(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<RestError>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RestError> handleException(Exception exc) {
        RestError error = new RestError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessages(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<RestError>(error, HttpStatus.BAD_REQUEST);
    }
}
