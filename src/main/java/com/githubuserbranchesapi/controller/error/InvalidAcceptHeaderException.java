package com.githubuserbranchesapi.controller.error;

public class InvalidAcceptHeaderException extends RuntimeException {
    public InvalidAcceptHeaderException(String message) {
        super(message);
    }
}
