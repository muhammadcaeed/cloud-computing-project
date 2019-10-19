package com.onlinekaufen.springframework.exceptions;

public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}