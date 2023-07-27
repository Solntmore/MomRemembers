package ru.mom.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RestControllerAdvice("ru.mom")
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        log.info(e.getMessage(), e);
        return new ApiError(HttpStatus.NOT_FOUND, e.getReason(), e.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        log.info(e.getMessage(), e);
        return new ApiError(HttpStatus.BAD_REQUEST, e.getReason(), e.getMessage());
    }

    @ExceptionHandler({RegistrationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleUserRegistrationException(final RegistrationException e) {
        log.info(e.getMessage(), e);
        return new ApiError(HttpStatus.BAD_REQUEST, e.getReason(), e.getMessage());
    }

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleUserRegistrationException(final AuthException e) {
        log.info(e.getMessage(), e);
        return new ApiError(HttpStatus.UNAUTHORIZED, e.getReason(), e.getMessage());
    }
}
