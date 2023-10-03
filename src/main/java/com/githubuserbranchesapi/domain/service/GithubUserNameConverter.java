package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.client.RepoName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubUserNameConverter {

    private final GithubProxy githubClient;

    public GithubUserNameConverter(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<String> convertToRepoNames(List<RepoName> repos) {
        List<String> repoNames = repos.stream()
                .map(RepoName::login)
                .collect(Collectors.toList());

        return repoNames;
    }
}
