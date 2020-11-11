package com.restApiTask.exceptionHandler;

import org.springframework.http.HttpStatus;

public class CustomEmployeeServiceException extends Exception{
    private final HttpStatus responseCode;

    public CustomEmployeeServiceException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}
