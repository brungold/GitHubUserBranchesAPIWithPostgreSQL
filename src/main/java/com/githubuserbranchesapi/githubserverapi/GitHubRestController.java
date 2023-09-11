package com.githubuserbranchesapi.githubserverapi;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class GitHubRestController {

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryResponseDto>> getUserRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (!acceptHeader.equalsIgnoreCase("application/json")) {
            throw new RuntimeException;

        }
        return List<RepositoryResponseDto>;
    }
}
