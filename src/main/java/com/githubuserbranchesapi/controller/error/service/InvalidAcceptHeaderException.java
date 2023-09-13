package com.githubuserbranchesapi.controller.error.service;

public class InvalidAcceptHeaderException extends RuntimeException {
    public InvalidAcceptHeaderException(String message) {
        super(message);
    }
}
