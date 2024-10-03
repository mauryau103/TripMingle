package com.trip.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserVerificationFailedException extends RuntimeException {
    public UserVerificationFailedException(String message) {
        super(message);
    }
}
