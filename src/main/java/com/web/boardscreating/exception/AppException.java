package com.web.boardscreating.exception;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class AppException extends Exception {

    private final HttpStatus code;

    public AppException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
