package com.trip.exception;

public class OtpAlreadySentException extends RuntimeException {

    public OtpAlreadySentException(String message) {
        super(message);
    }

    public OtpAlreadySentException(String message, Throwable cause) {
        super(message, cause);
    }

}
