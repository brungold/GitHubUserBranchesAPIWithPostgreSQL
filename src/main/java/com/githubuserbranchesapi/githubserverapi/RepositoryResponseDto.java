package com.githubuserbranchesapi.githubserverapi;

import java.util.List;

public record RepositoryResponseDto(
        String repositoryName,
        String ownerLogin,
        List<BranchInfo> branches
) {
}
