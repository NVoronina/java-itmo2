package com.example.javaitmo2.middleware;

import com.example.javaitmo2.beans.UserSessionBean;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    UserSessionBean userSessionBean;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(NotFoundException ex) {
        ErrorResponse message = new ErrorResponse("User with id: " + userSessionBean.getUserUuid() + " Error: " + ex.getMessage());

        return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> resourceValidationException(ValidationException ex) {
        ErrorResponse message = new ErrorResponse("User with id: " + userSessionBean.getUserUuid() + " Error: " + ex.getMessage());

        return new ResponseEntity<ErrorResponse>(message, HttpStatus.BAD_REQUEST);
    }
}