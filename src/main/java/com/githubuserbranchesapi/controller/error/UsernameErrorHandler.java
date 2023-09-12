package com.githubuserbranchesapi.controller.error;

import com.githubuserbranchesapi.controller.GitHubRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(assignableTypes = GitHubRestController.class)
@Log4j2
public class UsernameErrorHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUsernameResponseDto handleException(UsernameNotFoundException exception) {
        log.warn("UsernameNotFoundException while accessing username");
        return new ErrorUsernameResponseDto("User not found", HttpStatus.NOT_FOUND);
    }
}