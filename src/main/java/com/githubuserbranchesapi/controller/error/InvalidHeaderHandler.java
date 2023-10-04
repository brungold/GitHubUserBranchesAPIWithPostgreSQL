package com.githubuserbranchesapi.controller.error;

import com.githubuserbranchesapi.controller.GitHubRestController;
import com.githubuserbranchesapi.controller.error.dto.InvalidAcceptHeaderResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = GitHubRestController.class)
@Log4j2
public class InvalidHeaderHandler {
    @ExceptionHandler(InvalidAcceptHeaderException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public InvalidAcceptHeaderResponseDto handleHeaderException(InvalidAcceptHeaderException exception) {
        log.warn("Invalid Accept header");
        return new InvalidAcceptHeaderResponseDto(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
    }
}
