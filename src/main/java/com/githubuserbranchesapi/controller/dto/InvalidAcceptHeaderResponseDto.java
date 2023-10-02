package com.githubuserbranchesapi.controller.dto;

import org.springframework.http.HttpStatus;

public record InvalidAcceptHeaderResponseDto(HttpStatus httpStatus, String message) {
}
