package com.example.mongodb_topic4.exception;

public class MappingException extends RuntimeException {

  public MappingException(String type) {
    super("Mapping an entity of " + type + " unsuccessfully.");
  }

}
