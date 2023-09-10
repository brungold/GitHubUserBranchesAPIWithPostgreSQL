package com.githubuserbranchesapi.communication;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "github-username-server-client")
public class GithubProxy {
    //https://api.github.com/users/USERNAME/repos
}
