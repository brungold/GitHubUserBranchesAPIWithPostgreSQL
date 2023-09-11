package com.githubuserbranchesapi.clientcommunication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetGithubBranchResponseDto(String name, Commit commit) {
}
