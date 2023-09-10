package com.githubuserbranchesapi.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "github-username-server-client")
public interface GithubProxy {
    //https://api.github.com/users/USERNAME/repos
    @GetMapping ("/users/{username}/repos")
    GithubUserNameReposSto getUserRepos(@PathVariable String username);
}
