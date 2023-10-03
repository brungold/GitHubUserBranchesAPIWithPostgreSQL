package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.controller.error.UsernameNotFoundException;
import com.githubuserbranchesapi.domain.dto.GithubRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.RepoResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.repository.GithubRepoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        log.info("Retrieving all song: ");
        List<Repo> allRepo = githubRepoRepository.findAll(pageable);
        List<RepoResponseDto> dto = new ArrayList<>();
        allRepo.forEach(rep -> {
            dto.add(new RepoResponseDto(rep.getId(), rep.getOwner(), rep.getName()));
        });
        return dto;
    }

    public RepoResponseDto getById(Long id){
        Repo repo = githubRepoRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Repository with id " + id + "not foud"));
        RepoResponseDto dto = new RepoResponseDto(repo.getId(), repo.getOwner(), repo.getName());
        return dto;
    }

    public Repo addRepo(Repo repo){
        log.info("Adding a new repository " + repo);
        return githubRepoRepository.save(repo);
    }
    public void existsById(Long id) {
        if(!githubRepoRepository.existsById(id)){
            throw new UsernameNotFoundException("Repository with id " + id + " not found");
        }
    }
    public void deleteById(Long id){
        githubRepoRepository.existsById(id);
        log.info("deleting repository by id " + id);
        githubRepoRepository.deleteById(id);
    }

    public void updateById(Long id, Repo newRepo){
        githubRepoRepository.updateById();
    }
}
