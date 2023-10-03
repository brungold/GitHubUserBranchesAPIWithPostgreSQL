package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.domain.dto.request.CreatedRequestRepoDto;
import com.githubuserbranchesapi.domain.dto.request.PartiallyUpdateRepoRequestDto;
import com.githubuserbranchesapi.domain.dto.request.UpdateRepoRequestDto;
import com.githubuserbranchesapi.domain.dto.response.*;
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

    public static Repo mapFromUpdateRepoRequestDtotoRepo(UpdateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.repositoryName());
    }

    public static UpdateRepoResponseDto mapFromRepoToUpdateRepoResponseDto(Repo newRepo) {
        return new UpdateRepoResponseDto(newRepo.getOwner(), newRepo.getName());
    }

    public static Repo mapFromartiallyUpdateRepoRequestDtoToRepo(PartiallyUpdateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static PartiallyUpdateRepoResponseDto mapFromRepoToPartiallyUpdateRepoResponseDto(Repo repo) {
        RepoResponseDto responseDto = mapFromRepoToRepoResponseDto(repo);
        return new PartiallyUpdateRepoResponseDto(responseDto);
    }
}
