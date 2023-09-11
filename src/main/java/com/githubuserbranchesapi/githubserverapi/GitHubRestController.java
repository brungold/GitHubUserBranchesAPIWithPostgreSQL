package com.githubuserbranchesapi.githubserverapi;

import com.githubuserbranchesapi.clientcommunication.GithubProxy;
import com.githubuserbranchesapi.clientcommunication.dto.GetGithubBranchResponseDto;
import com.githubuserbranchesapi.clientcommunication.dto.GithubUserNameReposeDto;
import com.githubuserbranchesapi.clientcommunication.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
public class GitHubRestController {
    private final GithubService githubService;

    private final GithubProxy githubClient;
    public GitHubRestController(GithubProxy githubClient, GithubService githubService) {
        this.githubClient = githubClient;
        this.githubService = githubService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponseDto>> getUserRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (!acceptHeader.equalsIgnoreCase("application/json")) {
            throw new RuntimeException("Invalid Accept header");
        }
        List<GithubUserNameReposeDto> userRepos = githubClient.getUserRepos(username);
        List<String> repoNames = githubService.getRepoNames(userRepos);

        // Dla każdej nazwy repozytorium wywołaj getBranches
        List<RepositoryResponseDto> repositoryResponseList = new ArrayList<>();
        for (String repoName : repoNames) {
            List<GetGithubBranchResponseDto> branches = githubClient.getBranches(username, repoName);

            // Tworzenie BranchInfo dla każdej gałęzi
            List<BranchInfo> branchInfoList = branches.stream()
                    .map(branch -> new BranchInfo(branch.name(), branch.commit().sha()))
                    .collect(Collectors.toList());

            // Tworzenie RepositoryResponseDto dla repozytorium
            RepositoryResponseDto repositoryResponseDto = new RepositoryResponseDto(repoName, username, branchInfoList);
            repositoryResponseList.add(repositoryResponseDto);
        }

        return ResponseEntity.ok(repositoryResponseList);
    }
}
