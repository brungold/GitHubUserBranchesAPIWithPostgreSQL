package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.controller.error.InvalidAcceptHeaderException;
import com.githubuserbranchesapi.controller.error.UsernameNotFoundException;
import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.client.Owner;
import com.githubuserbranchesapi.domain.service.GitHubRestControllerService;
import com.githubuserbranchesapi.domain.service.GithubUserNameConverter;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new InvalidAcceptHeaderException("Invalid Accept header, only JSON acceptable.");
        }

        List<Owner> userRepos;
        try {
            userRepos = githubClient.getUserRepos(username);
        } catch (FeignException ex) {
            throw new UsernameNotFoundException(username);
        }

        List<String> repoNames = githubService.convertToRepoNames(userRepos);
        List<RepositoryResponseDto> repositoryResponseList = gitHubRestControllerService.fetchAllRepositoryBranches(username, repoNames);

        log.info(repositoryResponseList);
        return ResponseEntity.ok(repositoryResponseList);
    }
}
