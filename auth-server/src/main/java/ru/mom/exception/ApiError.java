package ru.mom.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сведения об ошибке")
public class ApiError {

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
