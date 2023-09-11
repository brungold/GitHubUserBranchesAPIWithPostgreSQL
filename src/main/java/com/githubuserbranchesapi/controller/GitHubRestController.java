package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.infrastructure.proxy.GithubProxy;
import com.githubuserbranchesapi.domain.dto.GetGithubBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.GithubUserNameReposeDto;
import com.githubuserbranchesapi.domain.service.GithubUserNameConverter;
import com.githubuserbranchesapi.domain.dto.BranchInfo;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class GitHubRestController {
    private final GithubUserNameConverter githubService;

    private final GithubProxy githubClient;
    public GitHubRestController(GithubProxy githubClient, GithubUserNameConverter githubService) {
        this.githubClient = githubClient;
        this.githubService = githubService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponseDto>> getUserRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (!acceptHeader.equalsIgnoreCase("application/json")) {
            throw new RuntimeException("Invalid Accept header");
        }

        List<GithubUserNameReposeDto> userRepos = githubClient.getUserRepos(username);
        List<String> repoNames = githubService.convertToRepoNames(userRepos);
        List<RepositoryResponseDto> repositoryResponseList = fetchRepositoryResponses(username, repoNames);

        return ResponseEntity.ok(repositoryResponseList);
    }

    private List<RepositoryResponseDto> fetchRepositoryResponses(String username, List<String> repoNames) {
        List<RepositoryResponseDto> repositoryResponseList = new ArrayList<>();

        for (String repoName : repoNames) {
            List<GetGithubBranchResponseDto> branches = fetchBranches(username, repoName);
            List<BranchInfo> branchInfoList = createBranchInfoList(branches);
            RepositoryResponseDto repositoryResponseDto = createRepositoryResponse(repoName, username, branchInfoList);
            repositoryResponseList.add(repositoryResponseDto);
        }

        return repositoryResponseList;
    }

    private List<GetGithubBranchResponseDto> fetchBranches(String username, String repoName) {
        return githubClient.getBranches(username, repoName);
    }

    private List<BranchInfo> createBranchInfoList(List<GetGithubBranchResponseDto> branches) {
        return branches.stream()
                .map(branch -> new BranchInfo(branch.name(), branch.commit().sha()))
                .collect(Collectors.toList());
    }

    private RepositoryResponseDto createRepositoryResponse(String repoName, String username, List<BranchInfo> branchInfoList) {
        return new RepositoryResponseDto(repoName, username, branchInfoList);
    }
}