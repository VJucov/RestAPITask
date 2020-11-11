package com.restApiTask.exceptionHandler;

public class ErrorResponseObject {

    private String message;

    ErrorResponseObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
