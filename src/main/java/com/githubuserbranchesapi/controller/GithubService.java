package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.controller.error.UsernameNotFoundException;
import com.githubuserbranchesapi.domain.dto.GithubRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.RepoResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.domain.service.RepositoryRetriever;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GithubService {
    private final GithubProxy githubClient;
    private final GithubRepoRepository githubRepoRepository;
    private final RepositoryRetriever repositoryRetriever;

    public GithubService(GithubProxy githubClient, GithubRepoRepository githubRepoRepository, RepositoryRetriever repositoryRetriever) {
        this.githubClient = githubClient;
        this.githubRepoRepository = githubRepoRepository;
        this.repositoryRetriever = repositoryRetriever;
    }


    public List<RepoResponseDto> getAllRepositoryNames(String username) {
        try {
            //Pobieram listę repozytoriów użytkownika z API GitHub
            List<GithubRepoResponseDto> allUserRepositories = githubClient.getUserRepos(username);

            //Przetwarzamdane i zapisz je do bazy danych oraz zwróć listę RequiredResponseDto
            List<RepoResponseDto> requiredResponseList = allUserRepositories.stream()
                    .map(repoDto -> {
                        //Zapisuje  informacje o repozytorium do bazy danych
                        Repo repo = new Repo(repoDto.owner().login(), repoDto.login());
                        Repo savedRepo = githubRepoRepository.save(repo);

                        //Tworze RequiredResponseDto na podstawie danych zapisanych w bazie danych
                        return new RepoResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
                    })
                    .collect(Collectors.toList());

            return requiredResponseList;
        } catch (RuntimeException ex) {
            throw new UsernameNotFoundException(username);
        }
    }
    public List<RepoResponseDto> getAll(Pageable pageable){
        List<Repo> allRepo = repositoryRetriever.findAll(pageable);
        List<RepoResponseDto> dto = new ArrayList<>();
        allRepo.forEach(rep -> {
            dto.add(new RepoResponseDto(rep.getId(), rep.getOwner(), rep.getName()));
        });
        return dto;
    }
}
