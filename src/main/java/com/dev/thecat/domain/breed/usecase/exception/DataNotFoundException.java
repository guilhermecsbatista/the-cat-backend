package com.dev.thecat.domain.breed.usecase.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(final String message) {
        super(message);
    }
}
