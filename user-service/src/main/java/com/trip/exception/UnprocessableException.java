package com.trip.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnprocessableException extends RuntimeException {

    public UnprocessableException(String message, Exception cause) {
        super(message, cause);
    }

    public UnprocessableException(String message) {
        super(message);
    }
}
