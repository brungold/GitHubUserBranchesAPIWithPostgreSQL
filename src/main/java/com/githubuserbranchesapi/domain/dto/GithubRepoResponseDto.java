package com.githubuserbranchesapi.domain.dto;

import com.githubuserbranchesapi.client.Owner;

public record GithubRepoResponseDto(String login, Owner owner) {
}
