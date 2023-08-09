package ru.mom.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Getter
public class RegistrationException extends RuntimeException {

    private String reason;

    public RegistrationException(final String message, final String reason) {
        super(message);
        this.reason = reason;
    }
}
