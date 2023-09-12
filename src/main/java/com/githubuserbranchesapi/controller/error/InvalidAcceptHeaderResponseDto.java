package com.githubuserbranchesapi.controller.error;

import org.springframework.http.HttpStatus;

public record InvalidAcceptHeaderResponseDto(HttpStatus httpStatus, String message) {
}
