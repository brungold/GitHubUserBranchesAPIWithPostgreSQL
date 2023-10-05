package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.controller.error.NotFoundException;
import com.githubuserbranchesapi.domain.dto.response.RepoResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class RepositoryRetriever {
    private final GithubRepoRepository githubRepoRepository;

    public RepositoryRetriever(GithubRepoRepository githubRepoRepository) {
        this.githubRepoRepository = githubRepoRepository;
    }

    public List<RepoResponseDto> getAll(Pageable pageable) {
        log.info("Retrieving all song: ");
        List<Repo> allRepo = githubRepoRepository.findAll(pageable);
        List<RepoResponseDto> dto = new ArrayList<>();
        allRepo.forEach(rep -> {
            dto.add(new RepoResponseDto(rep.getId(), rep.getOwner(), rep.getName()));
        });
        return dto;
    }

    public Repo getById(Long id) {
        return githubRepoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Repository with id " + id + "not foud"));
    }


    public void existsById(Long id) {
        if (!githubRepoRepository.existsById(id)) {
            throw new NotFoundException("Repository with id " + id + " not found");
        }
    }
}
