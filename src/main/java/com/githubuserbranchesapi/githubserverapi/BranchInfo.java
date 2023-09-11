package com.githubuserbranchesapi.githubserverapi;

public record BranchInfo(
        String branchName,
        String lastCommitSHA
) {
}
