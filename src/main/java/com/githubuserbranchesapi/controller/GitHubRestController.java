package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.domain.dto.RequiredResponseDto;
import com.githubuserbranchesapi.domain.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repo")
@Log4j2
public class GitHubRestController {
    private final GithubProxy githubClient;
    private final GithubService githubService;

    public GitHubRestController(GithubProxy githubClient, GithubService githubservice) {
        this.githubClient = githubClient;
        this.githubService = githubservice;
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<List<RequiredResponseDto>> getAllRepositoriesNamesByUserName(@PathVariable String userName) {
        List<RequiredResponseDto> allRepositoriesByUserName = githubService.getAllRepositoryNames(userName);
        return ResponseEntity.ok(allRepositoriesByUserName);
    }

    @GetMapping
    public ResponseEntity<List<RequiredResponseDto>> getAllRepositories(@PageableDefault(page = 0, size = 10)Pageable pageable){
        List<RequiredResponseDto> allRepositories = githubService.getAll(pageable);
        return ResponseEntity.ok(allRepositories);
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<List<RepositoryResponseDto>> getAllUserRepositoryBranches(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
//        if (acceptHeader.equals("application/xml")) {
//            throw new InvalidAcceptHeaderException("Invalid Accept header, only JSON acceptable.");
//        }
//
//        List<Owner> userRepos;
//        try {
//            userRepos = githubClient.getUserRepos(username);
//        } catch (FeignException ex) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        List<String> repoNames = githubService.convertToRepoNames(userRepos);
//        List<RepositoryResponseDto> repositoryResponseList = gitHubRestControllerService.fetchAllRepositoryBranches(username, repoNames);
//
//        log.info(repositoryResponseList);
//        return ResponseEntity.ok(repositoryResponseList);
//    }
}
