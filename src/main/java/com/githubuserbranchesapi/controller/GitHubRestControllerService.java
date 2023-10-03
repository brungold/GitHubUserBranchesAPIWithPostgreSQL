package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.domain.dto.BranchInfoResponseDto;
import com.githubuserbranchesapi.domain.dto.GetGithubBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.infrastructure.proxy.GithubProxy;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GitHubRestControllerService {
    private final GithubProxy githubClient;

    public GitHubRestControllerService(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepositoryResponseDto> fetchRepositoryResponses(String username, List<String> repoNames) {
        return repoNames.stream()
                .map(repoName -> {
                    List<GetGithubBranchResponseDto> branches = githubClient.getBranches(username, repoName);
                    List<BranchInfoResponseDto> branchInfoList = branches.stream()
                            .map(branch -> new BranchInfoResponseDto(branch.name(), branch.commit().sha()))
                            .collect(Collectors.toList());
                    return new RepositoryResponseDto(repoName, username, branchInfoList);
                })
                .collect(Collectors.toList());
    }
}
