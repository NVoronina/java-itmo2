package com.example.javaitmo2.middleware;

import com.example.javaitmo2.beans.UserSessionBean;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Value("${app_host}")
    private String appHost;

    @Value("${app_env}")
    private String appEnv;

    @Autowired
    UserSessionBean userSessionBean;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(NotFoundException ex) {
        return new ResponseEntity<ErrorResponse>(prepareResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> resourceValidationException(ValidationException ex) {
        return new ResponseEntity<ErrorResponse>(prepareResponse(ex), HttpStatus.BAD_REQUEST);
    }

    private String prepareMessage(RuntimeException ex) {
        return "Requested Host: " + appHost + " User with id: " + userSessionBean.getUserUuid() + " Error: " + ex.getMessage();
    }

    private ErrorResponse prepareResponse(RuntimeException ex) {
        ErrorResponse result = new ErrorResponse(prepareMessage(ex));
        if (appEnv.equals("dev")) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            result.setTrace(sw.toString());
        }
        return result;
    }
}