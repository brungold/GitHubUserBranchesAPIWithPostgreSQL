package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.domain.dto.CreatedRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.RepoResponseDto;
import com.githubuserbranchesapi.domain.dto.RequestRepoDto;
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
    public ResponseEntity<List<RepoResponseDto>> getAllRepositoriesNamesByUserName(@PathVariable String userName) {
        List<RepoResponseDto> allRepositoriesByUserName = githubService.getAllRepositoryNames(userName);
        return ResponseEntity.ok(allRepositoriesByUserName);
    }

    @GetMapping
    public ResponseEntity<List<RepoResponseDto>> getAllRepositories(@PageableDefault(page = 0, size = 10)Pageable pageable){
        List<RepoResponseDto> allRepositories = githubService.getAll(pageable);
        return ResponseEntity.ok(allRepositories);
    }

    @PostMapping
    public ResponseEntity<CreatedRepoResponseDto> postRepo(@RequestBody RequestRepoDto requestRepoDto){

    }
}
