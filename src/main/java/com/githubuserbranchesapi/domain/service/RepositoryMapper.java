package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.domain.dto.CreatedRepoResponseDto;
import com.githubuserbranchesapi.domain.dto.CreatedRequestRepoDto;
import com.githubuserbranchesapi.domain.dto.DeleteRepositoryResponseDto;
import com.githubuserbranchesapi.domain.dto.RepoResponseDto;
import com.githubuserbranchesapi.domain.model.Repo;
import org.springframework.http.HttpStatus;

public class RepositoryMapper {
    public static RepoResponseDto mapFromRepoToRepoResponseDto(Repo repo){
        return new RepoResponseDto(repo.getId(), repo.getOwner(), repo.getName());
    }
    public static Repo mapFromCreatedRequestRepoDtoToRepo(CreatedRequestRepoDto dto) {
        return new Repo(dto.owner(), dto.repositoryName());
    }

    public static CreatedRepoResponseDto mapFromRepoToCreatedRepoResponseDto(Repo repo) {
        RepoResponseDto repoDto = mapFromRepoToRepoResponseDto(repo);
        return new CreatedRepoResponseDto(repoDto);
    }

    public static DeleteRepositoryResponseDto mapFromRepoToDeleteRepositoryResponseDto(Long id){
        return new DeleteRepositoryResponseDto("You deleted repository with id: " + id, HttpStatus.OK);
    }
}
