package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
public class RepositoryAdder {
    private final GithubRepoRepository githubRepoRepository;

    public RepositoryAdder(GithubRepoRepository githubRepoRepository) {
        this.githubRepoRepository = githubRepoRepository;
    }

    public Repo addRepo(Repo repo){
        log.info("Adding a new repository " + repo);
        return githubRepoRepository.save(repo);
    }
}
