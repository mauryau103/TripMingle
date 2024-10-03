package com.trip.exception;

import lombok.Getter;

import java.util.Set;

@Getter
public class ModelValidationException extends RuntimeException{
    private final Set<String> errors;

    public ModelValidationException(Set<String> errors) {
        this.errors = errors;
    }
}
