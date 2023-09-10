package com.githubuserbranchesapi.communication.service;

import com.githubuserbranchesapi.communication.dto.GithubUserNameReposeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "github-username-server-client")
public interface GithubProxy {
    //https://api.github.com/users/USERNAME/repos
    @GetMapping ("/users/{username}/repos")
    List<GithubUserNameReposeDto> getUserRepos(@PathVariable String username);
}
