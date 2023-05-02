package ru.mom.remembers.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    private List<String> errors;

    private String message;

    private String reason;

    private HttpStatus status;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(HttpStatus status, String reason, String message) {
        this.message = message;
        this.reason = reason;
        this.status = status;
    }

}
