package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.controller.error.service.InvalidAcceptHeaderException;
import com.githubuserbranchesapi.controller.error.service.UsernameNotFoundException;
import com.githubuserbranchesapi.infrastructure.proxy.GithubProxy;
import com.githubuserbranchesapi.domain.dto.GetGithubBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.GithubUserNameReposeDto;
import com.githubuserbranchesapi.domain.service.GithubUserNameConverter;
import com.githubuserbranchesapi.domain.dto.BranchInfoResponseDto;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import feign.FeignException;
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
    private final GitHubRestControllerService gitHubRestControllerService;

    public GitHubRestController(GithubUserNameConverter githubService, GithubProxy githubClient, GitHubRestControllerService gitHubRestControllerService) {
        this.githubService = githubService;
        this.githubClient = githubClient;
        this.gitHubRestControllerService = gitHubRestControllerService;
    }




    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponseDto>> getUserRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.equals("application/xml")) {
            throw new InvalidAcceptHeaderException("Invalid Accept header");
        }

        List<GithubUserNameReposeDto> userRepos;
        try {
            userRepos = githubClient.getUserRepos(username);
        } catch (FeignException ex) {
            throw new UsernameNotFoundException(username);
        }

        List<String> repoNames = githubService.convertToRepoNames(userRepos);
        List<RepositoryResponseDto> repositoryResponseList = gitHubRestControllerService.fetchRepositoryResponses(username, repoNames);

        return ResponseEntity.ok(repositoryResponseList);
    }


}
