package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.controller.error.UsernameNotFoundException;
import com.githubuserbranchesapi.domain.dto.GithubRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.RequiredResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class Githubservice {
    private final GithubProxy githubClient;
    private final GithubRepoRepository githubRepoRepository;

    public Githubservice(GithubProxy githubClient, GithubRepoRepository githubRepoRepository) {
        this.githubClient = githubClient;
        this.githubRepoRepository = githubRepoRepository;
    }

    public List<RequiredResponseDto> getAllRepositoryNames(String username) {
        try {
            //Pobieram listę repozytoriów użytkownika z API GitHub
            List<GithubRepoResponseDto> allUserRepositories = githubClient.getUserRepos(username);

            //Przetwarzamdane i zapisz je do bazy danych oraz zwróć listę RequiredResponseDto
            List<RequiredResponseDto> requiredResponseList = allUserRepositories.stream()
                    .map(repoDto -> {
                        //Zapisuje  informacje o repozytorium do bazy danych
                        Repo repo = new Repo(repoDto.owner().login(), repoDto.login());
                        Repo savedRepo = githubRepoRepository.save(repo);

                        //Tworze RequiredResponseDto na podstawie danych zapisanych w bazie danych
                        return new RequiredResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
                    })
                    .collect(Collectors.toList());

            return requiredResponseList;
        }catch (RuntimeException ex) {
            throw new UsernameNotFoundException(username);
        }
    }
}
