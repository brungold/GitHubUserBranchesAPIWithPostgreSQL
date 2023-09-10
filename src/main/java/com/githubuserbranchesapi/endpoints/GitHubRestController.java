package com.githubuserbranchesapi.endpoints;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@Log4j2
public class GitHubRestController {

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDto>> getUserRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (!acceptHeader.equalsIgnoreCase("application/json")) {
            return ResponseEntity.badRequest().body("Invalid Accept header. Only 'application/json' is supported.");
        }
        return ;
    }
}
