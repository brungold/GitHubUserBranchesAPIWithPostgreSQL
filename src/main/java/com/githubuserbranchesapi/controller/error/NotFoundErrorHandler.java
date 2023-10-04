package com.githubuserbranchesapi.controller.error;

import com.githubuserbranchesapi.controller.GitHubRestController;
import com.githubuserbranchesapi.controller.error.dto.ErrorUsernameResponseDto;
import com.githubuserbranchesapi.domain.model.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(assignableTypes = GitHubRestController.class)
@Log4j2
public class NotFoundErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorUsernameResponseDto handleException(NotFoundException exception) {
        log.warn("Resource not found");
        return new ErrorUsernameResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
