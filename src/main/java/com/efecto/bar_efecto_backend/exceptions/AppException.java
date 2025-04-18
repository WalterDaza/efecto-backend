package com.efecto.bar_efecto_backend.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus state;
    private  String message;

    public AppException(HttpStatus state, String message) {
        this.state = state;
        this.message = message;
    }

    public AppException(HttpStatus state, String message, String message1) {
        this.state = state;
        this.message = message;
        this.message = message1;
    }

    public HttpStatus getState() {
        return state;
    }

    public void setState(HttpStatus state) {
        this.state = state;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
