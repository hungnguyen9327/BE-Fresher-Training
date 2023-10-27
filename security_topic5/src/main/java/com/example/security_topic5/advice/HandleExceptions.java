package com.example.security_topic5.advice;

import com.example.security_topic5.exception.AccessingDataException;
import com.example.security_topic5.exception.ExistedException;
import com.example.security_topic5.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandleExceptions {

  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> notFoundHandler(NotFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("message", ex.getMessage());
    body.put("statusCode", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ExceptionHandler(ExistedException.class)
  public ResponseEntity<Object> existedHandler(ExistedException ex) {
    Map<String, Object> result = new HashMap<>();
    result.put("message", ex.getMessage());
    result.put("statusCode", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessingDataException.class)
  public ResponseEntity<Object> handleDataAccessException(AccessingDataException ex) {
    Map<String, Object> result = new HashMap<>();
    result.put("error", ex.getMessage());

    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> notValid(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    List<String> errors = new ArrayList<>();

    ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
    Map<String, List<String>> result = new HashMap<>();
    result.put("errors", errors);

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }
}
