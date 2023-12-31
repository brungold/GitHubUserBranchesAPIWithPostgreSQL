package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.controller.error.NotFoundException;
import com.githubuserbranchesapi.client.dto.GithubRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.response.RepoResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class GithubService {
    private final GithubProxy githubClient;
    private final GithubRepoRepository githubRepoRepository;

    public List<RepoResponseDto> getAllRepositoryNames(String username) {
        try {
            List<GithubRepoResponseDto> allUserRepositories = githubClient.getUserRepos(username);

            List<RepoResponseDto> requiredResponseList = allUserRepositories.stream()
                    .map(repoDto -> {
                        Repo repo = new Repo(repoDto.owner().login(), repoDto.name());
                        Repo savedRepo = githubRepoRepository.save(repo);

                        return new RepoResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
                    })
                    .collect(Collectors.toList());

            return requiredResponseList;
        } catch (NotFoundException ex) {
            throw new NotFoundException(username);
        }
    }
}
