package com.githubuserbranchesapi.client;

import com.githubuserbranchesapi.domain.dto.GetBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.GithubRepoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "github-username-server-client")
public interface GithubProxy {
    //https://api.github.com/users/USERNAME/repos
    @GetMapping("/users/{username}/repos")
    List<GithubRepoResponseDto> getUserRepos(@PathVariable String username);

    //https://api.github.com/repos/OWNER/REPO/branches
    @GetMapping(value = "/repos/{owner}/{repo}/branches")
    List<GetBranchResponseDto> getBranches(@PathVariable String owner, @PathVariable String repo);
}
