package com.githubuserbranchesapi.controller.dto;

import org.springframework.http.HttpStatus;

public record ErrorUsernameResponseDto(HttpStatus httpStatus, String message) {
}
