package com.example.security_topic5.exception;

public class AccessingDataException extends RuntimeException {

  public AccessingDataException(String message) {
    super(message);
  }

  public AccessingDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
