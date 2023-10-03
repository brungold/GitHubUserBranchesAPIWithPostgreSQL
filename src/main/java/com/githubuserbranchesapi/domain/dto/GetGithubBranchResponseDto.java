package com.githubuserbranchesapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.githubuserbranchesapi.client.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetGithubBranchResponseDto(String name, Commit commit) {
}
