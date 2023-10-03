package com.githubuserbranchesapi.controller;

import com.githubuserbranchesapi.client.GithubProxy;
import com.githubuserbranchesapi.domain.dto.request.CreatedRequestRepoDto;
import com.githubuserbranchesapi.domain.dto.request.PartiallyUpdateRepoRequestDto;
import com.githubuserbranchesapi.domain.dto.request.UpdateRepoRequestDto;
import com.githubuserbranchesapi.domain.dto.response.*;
import com.githubuserbranchesapi.domain.model.Repo;
import com.githubuserbranchesapi.domain.service.*;
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
    private final RepositoryAdder repositoryAdder;
    private final RepositoryRetriever repositoryRetriever;
    private final RepositoryDeleter repositoryDeleter;
    private final RepositoryUpdater repositoryUpdater;

    @GetMapping("/username/{userName}")
    public ResponseEntity<List<RepoResponseDto>> getAllRepositoriesNamesByUserName(@PathVariable String userName) {
        List<RepoResponseDto> allRepositoriesByUserName = githubService.getAllRepositoryNames(userName);
        return ResponseEntity.ok(allRepositoriesByUserName);
    }

    @GetMapping
    public ResponseEntity<List<RepoResponseDto>> getAllRepositories(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<RepoResponseDto> allRepositories = repositoryRetriever.getAll(pageable);
        return ResponseEntity.ok(allRepositories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepoResponseDto> getById(@PathVariable Long id) {
        Repo repo = repositoryRetriever.getById(id);
        RepoResponseDto responseDto = mapFromRepoToRepoResponseDto(repo);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<CreatedRepoResponseDto> postRepo(@RequestBody CreatedRequestRepoDto requestRepoDto) {
        Repo repo = mapFromCreatedRequestRepoDtoToRepo(requestRepoDto);
        Repo savedRepo = repositoryAdder.addRepo(repo);
        CreatedRepoResponseDto responseDto = mapFromRepoToCreatedRepoResponseDto(savedRepo);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRepositoryResponseDto> delete(@PathVariable Long id) {
        repositoryDeleter.deleteById(id);
        DeleteRepositoryResponseDto deleteDto = mapFromRepoToDeleteRepositoryResponseDto(id);
        return ResponseEntity.ok(deleteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRepoResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateRepoRequestDto request) {
        Repo newRepo = mapFromUpdateRepoRequestDtotoRepo(request);
        repositoryUpdater.updateById(id, newRepo);
        UpdateRepoResponseDto updateRepoResponseDto = mapFromRepoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(updateRepoResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateRepoResponseDto> partiallyUpdateRepo(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateRepoRequestDto request) {
        Repo updatedRepo = mapFromartiallyUpdateRepoRequestDtoToRepo(request);
        Repo repoSaved = repositoryUpdater.updatePartiallyById(id, updatedRepo);
        PartiallyUpdateRepoResponseDto partiallyUpdateRepoResponseDto = mapFromRepoToPartiallyUpdateRepoResponseDto(repoSaved);
        return ResponseEntity.ok(partiallyUpdateRepoResponseDto);
    }




}
