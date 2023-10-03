package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.repository.GithubRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepositoryDeleter {
    private final GithubRepoRepository githubRepoRepository;
    private final RepositoryRetriever repositoryRetriever;

    public RepositoryDeleter(GithubRepoRepository githubRepoRepository, RepositoryRetriever repositoryRetriever) {
        this.githubRepoRepository = githubRepoRepository;
        this.repositoryRetriever = repositoryRetriever;
    }

    public void deleteById(Long id){
        githubRepoRepository.existsById(id);
        log.info("deleting repository by id " + id);
        githubRepoRepository.deleteById(id);
    }
}
