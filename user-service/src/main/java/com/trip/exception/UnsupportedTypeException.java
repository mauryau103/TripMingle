package com.trip.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(String message, Exception cause) {
        super(message, cause);
    }

    public UnsupportedTypeException(String message) {
        super(message);
    }
}