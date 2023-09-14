package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.domain.dto.BranchInfoResponseDto;
import com.githubuserbranchesapi.domain.dto.GetGithubBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import com.githubuserbranchesapi.infrastructure.proxy.GithubProxy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class GitHubRestControllerService {
    private final GithubProxy githubClient;

    public GitHubRestControllerService(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryResponseDto> fetchRepositoryResponses(String username, List<String> repoNames) {
        List<RepositoryResponseDto> repositoryResponseList = new ArrayList<>();

        for (String repoName : repoNames) {
            List<GetGithubBranchResponseDto> branches = fetchBranches(username, repoName);
            List<BranchInfoResponseDto> branchInfoList = createBranchInfoList(branches);
            RepositoryResponseDto repositoryResponseDto = createRepositoryResponse(repoName, username, branchInfoList);
            repositoryResponseList.add(repositoryResponseDto);
        }

        return repositoryResponseList;
    }

    private List<GetGithubBranchResponseDto> fetchBranches(String username, String repoName) {
        return githubClient.getBranches(username, repoName);
    }

    private List<BranchInfoResponseDto> createBranchInfoList(List<GetGithubBranchResponseDto> branches) {
        return branches.stream()
                .map(branch -> new BranchInfoResponseDto(branch.name(), branch.commit().sha()))
                .collect(Collectors.toList());
    }

    private RepositoryResponseDto createRepositoryResponse(String repoName, String username, List<BranchInfoResponseDto> branchInfoList) {
        return new RepositoryResponseDto(repoName, username, branchInfoList);
    }
}
