package com.example.mongodb_topic4.advice;

import com.example.mongodb_topic4.exception.ExceedPageBoundException;
import com.example.mongodb_topic4.exception.MappingException;
import com.example.mongodb_topic4.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandleExceptions {

  @ResponseBody
  @ExceptionHandler(MappingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String mappingHandler(MappingException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)

  public String notFoundHandler(NotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(ExceedPageBoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String ExceedPageBoundHandler(ExceedPageBoundException ex) {
    return ex.getMessage();
  }

}
