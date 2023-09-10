package com.githubuserbranchesapi.communication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserNameReposeDto(String name) {
}
