package com.example.mongodb_topic4.exception;

public class ExceedPageBoundException extends RuntimeException {

  public ExceedPageBoundException() {
    super("Exceeded page limit. The requested page is beyond the allowed range.");
  }
}
