package com.githubuserbranchesapi.domain.dto;

import org.springframework.http.HttpStatus;

public record DeleteRepositoryResponseDto(String message, HttpStatus status) {
}
