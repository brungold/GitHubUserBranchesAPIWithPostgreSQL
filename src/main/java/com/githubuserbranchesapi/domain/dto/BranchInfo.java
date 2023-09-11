package com.githubuserbranchesapi.domain.dto;

public record BranchInfo(
        String branchName,
        String lastCommitSHA
) {
}
