package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.infrastructure.proxy.GithubProxy;
import com.githubuserbranchesapi.domain.dto.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubUserNameConverter {

    private final GithubProxy githubClient;

    public GithubUserNameConverter(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<String> convertToRepoNames(List<Owner> repos) {
        List<String> repoNames = repos.stream()
                .map(Owner::login)
                .collect(Collectors.toList());

        return repoNames;
    }
}
