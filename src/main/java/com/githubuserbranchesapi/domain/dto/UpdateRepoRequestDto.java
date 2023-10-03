package com.githubuserbranchesapi.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDto(
        @NotNull(message = "Owner must not be null")
        @NotEmpty(message = "Owner must not be empty")
        String owner,
        @NotNull(message = "repositoryName must not be null")
        @NotEmpty(message = "repositoryName must not be empty")
        String repositoryName) {
}
