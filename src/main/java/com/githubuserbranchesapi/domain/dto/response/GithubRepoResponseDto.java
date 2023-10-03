package com.githubuserbranchesapi.domain.dto.response;

import com.githubuserbranchesapi.client.dto.Owner;

public record GithubRepoResponseDto(String login, Owner owner) {
}
