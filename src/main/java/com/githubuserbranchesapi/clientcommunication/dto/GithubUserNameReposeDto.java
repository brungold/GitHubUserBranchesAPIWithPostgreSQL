package com.githubuserbranchesapi.clientcommunication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserNameReposeDto(String name) {
}
