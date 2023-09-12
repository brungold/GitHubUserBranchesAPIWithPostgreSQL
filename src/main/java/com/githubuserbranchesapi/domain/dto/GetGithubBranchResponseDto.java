package com.githubuserbranchesapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetGithubBranchResponseDto(String name, CommitInfoResponseDto commit) {
}
