package com.githubuserbranchesapi.client.dto;

public record BranchInfoResponseDto(
        String branchName,
        String lastCommitSHA
) {
}
