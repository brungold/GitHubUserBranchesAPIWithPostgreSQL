package com.githubuserbranchesapi.communication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserNameReposSto(String name) {
}
