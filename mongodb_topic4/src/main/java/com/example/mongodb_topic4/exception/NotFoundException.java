package com.example.mongodb_topic4.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String type, String id) {
        super("Could not find " + type + " with id: " + id);
    }
}
