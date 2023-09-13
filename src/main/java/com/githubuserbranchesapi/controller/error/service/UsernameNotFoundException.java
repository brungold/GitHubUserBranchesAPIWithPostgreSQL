package com.githubuserbranchesapi.controller.error.service;


public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
