package com.githubuserbranchesapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.githubuserbranchesapi.client.dto.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetBranchResponseDto(String name, Commit commit) {
}
