package com.example.individual.garage.common.exceptions;

import com.example.individual.garage.enums.GeneralEnums;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VehicleTypeException.class)
    public  ResponseEntity<Object> handleAllExceptions(VehicleTypeException ex, WebRequest request) {
       Map<String,Object> body = new LinkedHashMap<>();
       body.put("message",ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(GeneralException.class)
    public  ResponseEntity<Object> handleAllExceptions(GeneralException ex, WebRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("message",ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(NoTicketIdException.class)
    public  ResponseEntity<Object> handleAllExceptions(NoTicketIdException ex, WebRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("message",ex.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    }
}
