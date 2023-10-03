package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.domain.dto.*;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.domain.service.GithubService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.githubuserbranchesapi.domain.service.RepositoryMapper.*;


@RestController
@RequestMapping("/api/repo")
@Log4j2
@AllArgsConstructor
public class GitHubRestController {
    private final GithubProxy githubClient;
    private final GithubService githubService;

    @GetMapping("/username/{userName}")
    public ResponseEntity<List<RepoResponseDto>> getAllRepositoriesNamesByUserName(@PathVariable String userName) {
        List<RepoResponseDto> allRepositoriesByUserName = githubService.getAllRepositoryNames(userName);
        return ResponseEntity.ok(allRepositoriesByUserName);
    }

    @GetMapping
    public ResponseEntity<List<RepoResponseDto>> getAllRepositories(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<RepoResponseDto> allRepositories = githubService.getAll(pageable);
        return ResponseEntity.ok(allRepositories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepoResponseDto> getById(@PathVariable Long id) {
        RepoResponseDto responseDto = githubService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<CreatedRepoResponseDto> postRepo(@RequestBody CreatedRequestRepoDto requestRepoDto) {
        Repo repo = mapFromCreatedRequestRepoDtoToRepo(requestRepoDto);
        Repo savedRepo = githubService.addRepo(repo);
        CreatedRepoResponseDto responseDto = mapFromRepoToCreatedRepoResponseDto(savedRepo);
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRepositoryResponseDto> delete(@PathVariable Long id) {
        githubService.deleteById(id);
        DeleteRepositoryResponseDto deleteDto = mapFromRepoToDeleteRepositoryResponseDto(id);
        return ResponseEntity.ok(deleteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRepoResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateRepoRequestDto request){
        Repo newRepo = mapFromUpdateRepoRequestDtotoRepo(request);
        githubService.updateById(id, newRepo);
        UpdateRepoResponseDto updateRepoResponseDto = mapFromRepoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(updateRepoResponseDto);
    }




}
