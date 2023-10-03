package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepositoryUpdater {
    private final GithubRepoRepository githubRepoRepository;
    private final RepositoryRetriever repositoryRetriever;

    public RepositoryUpdater(GithubRepoRepository githubRepoRepository, RepositoryRetriever repositoryRetriever) {
        this.githubRepoRepository = githubRepoRepository;
        this.repositoryRetriever = repositoryRetriever;
    }

    public void updateById(Long id, Repo newRepo){
        githubRepoRepository.existsById(id);
        githubRepoRepository.updateById(id, newRepo);
    }

    public Repo updatePartiallyById(Long id, Repo repoFromRequest){
        Repo repoFromDatabase = repositoryRetriever.getById(id);
        Repo.RepoBuilder builder = Repo.builder();
        if (repoFromRequest.getName() != null){
            builder.name(repoFromRequest.getName());
        } else {
            builder.name(repoFromDatabase.getName());
        }
        if (repoFromRequest.getOwner() != null){
            builder.name(repoFromRequest.getOwner());
        } else {
            builder.name(repoFromDatabase.getOwner());
        }
        Repo toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }
}
