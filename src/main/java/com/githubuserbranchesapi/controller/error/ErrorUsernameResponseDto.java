package com.githubuserbranchesapi.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorUsernameResponseDto(String message, HttpStatus httpStatus) {
}
