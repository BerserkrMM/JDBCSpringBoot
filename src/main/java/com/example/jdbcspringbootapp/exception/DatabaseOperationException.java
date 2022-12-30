package com.example.jdbcspringbootapp.exception;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException() {
        super();
    }

    public DatabaseOperationException(String message) {
        super(message);
    }
}
