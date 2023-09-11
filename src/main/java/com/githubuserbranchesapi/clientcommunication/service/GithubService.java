package com.githubuserbranchesapi.clientcommunication.service;

import com.githubuserbranchesapi.clientcommunication.GithubProxy;
import com.githubuserbranchesapi.clientcommunication.dto.GithubUserNameReposeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private final GithubProxy githubClient;

    public GithubService(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<String> getRepoNames(String username) {
        List<GithubUserNameReposeDto> repos = githubClient.getUserRepos(username);

        List<String> repoNames = repos.stream()
                .map(GithubUserNameReposeDto::name)
                .collect(Collectors.toList());

        return repoNames;
    }
}
