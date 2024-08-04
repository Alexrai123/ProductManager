package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotValidException extends CustomBaseException{
    public ProductNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
