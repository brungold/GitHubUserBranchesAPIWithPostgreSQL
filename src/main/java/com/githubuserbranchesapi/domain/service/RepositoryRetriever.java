package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.domain.model.RepositoryNotFoundException;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepositoryRetriever {

    private final GithubRepoRepository githubRepoRepository;

    public RepositoryRetriever(GithubRepoRepository githubRepoRepository) {
        this.githubRepoRepository = githubRepoRepository;
    }

    public List<Repo> findAll(Pageable pageable){
        log.info("Retrieving all song: ");
        return githubRepoRepository.findAll(pageable);
    }

    public Repo findSongById(Long id) {
        return githubRepoRepository.findById(id)
                .orElseThrow(() -> new RepositoryNotFoundException("Repository with id " + id + " not found"));
    }
}
