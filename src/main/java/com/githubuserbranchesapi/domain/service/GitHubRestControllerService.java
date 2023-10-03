package com.githubuserbranchesapi.domain.service;

import com.githubuserbranchesapi.client.BranchInfoResponseDto;
import com.githubuserbranchesapi.domain.dto.GetBranchResponseDto;
import com.githubuserbranchesapi.domain.dto.RepositoryResponseDto;
import com.githubuserbranchesapi.client.GithubProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GitHubRestControllerService {
    private final GithubProxy githubClient;

    public GitHubRestControllerService(GithubProxy githubClient) {
        this.githubClient = githubClient;
    }

//    public List<RepositoryResponseDto> fetchAllRepositoryBranches(String username, List<String> repoNames) {
//        return repoNames.stream()
//                .map(repoName -> {
//                    List<GetBranchResponseDto> branches = githubClient.getBranches(username, repoName);
//                    List<BranchInfoResponseDto> branchInfoList = branches.stream()
//                            .map(branch -> new BranchInfoResponseDto(branch.name(), branch.commit().sha()))
//                            .collect(Collectors.toList());
//                    return new RepositoryResponseDto(repoName, username, branchInfoList);
//                })
//                .collect(Collectors.toList());
//    }
    //    public List<String> convertToRepoNames(List<RepoName> repos) {
//        List<String> repoNames = repos.stream()
//                .map(RepoName::login)
//                .collect(Collectors.toList());
//
//        return repoNames;
//    }
}
