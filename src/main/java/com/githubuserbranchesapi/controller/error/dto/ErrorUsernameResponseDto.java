package com.githubuserbranchesapi.controller.error.dto;

import org.springframework.http.HttpStatus;

public record ErrorUsernameResponseDto(HttpStatus httpStatus, String message) {
}
