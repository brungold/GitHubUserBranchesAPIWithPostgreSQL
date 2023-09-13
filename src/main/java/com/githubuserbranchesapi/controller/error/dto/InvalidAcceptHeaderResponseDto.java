package com.githubuserbranchesapi.controller.error.dto;

import org.springframework.http.HttpStatus;

public record InvalidAcceptHeaderResponseDto(HttpStatus httpStatus, String message) {
}
