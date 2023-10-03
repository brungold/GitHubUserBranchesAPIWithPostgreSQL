package com.githubuserbranchesapi.domain.dto;

import com.githubuserbranchesapi.client.BranchInfoResponseDto;

import java.util.List;

public record RepositoryResponseDto(
        String repositoryName,
        String ownerLogin,
        List<BranchInfoResponseDto> branches
) {
}
