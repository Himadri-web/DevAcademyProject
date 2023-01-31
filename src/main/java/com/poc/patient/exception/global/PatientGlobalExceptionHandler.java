package com.poc.patient.exception.global;

import com.poc.patient.model.APIErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * @author HS106406
 * This PatientGlobalExceptionHandler class represents as a global exception handling mechanism
 */

@RestControllerAdvice
public class PatientGlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<APIErrorResponse> genericExceptionHandler(Exception exception, HttpServletRequest req){
        APIErrorResponse response = APIErrorResponse.of(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), req.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
