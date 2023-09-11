package com.githubuserbranchesapi.domain.dto;

import com.githubuserbranchesapi.domain.dto.BranchInfo;

import java.util.List;

public record RepositoryResponseDto(
        String repositoryName,
        String ownerLogin,
        List<BranchInfo> branches
) {
}
