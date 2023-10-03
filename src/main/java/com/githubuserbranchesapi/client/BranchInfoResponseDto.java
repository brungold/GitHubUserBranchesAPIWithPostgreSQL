package com.githubuserbranchesapi.client;

public record BranchInfoResponseDto(
        String branchName,
        String lastCommitSHA
) {
}
