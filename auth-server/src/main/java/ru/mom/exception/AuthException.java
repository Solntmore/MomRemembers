package ru.mom.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@Getter
public class AuthException extends RuntimeException {

    private String reason;

    public AuthException(final String message, final String reason) {
        super(message);
        this.reason = reason;
    }

}
