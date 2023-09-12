package com.githubuserbranchesapi.domain.dto;

public record BranchInfoResponseDto(
        String branchName,
        String lastCommitSHA
) {
}
