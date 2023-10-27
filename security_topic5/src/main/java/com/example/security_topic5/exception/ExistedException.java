package com.example.security_topic5.exception;

public class ExistedException extends RuntimeException {

  public ExistedException(String message) {
    super(message);
  }

  public ExistedException(String message, Throwable cause) {
    super(message, cause);
  }
}
