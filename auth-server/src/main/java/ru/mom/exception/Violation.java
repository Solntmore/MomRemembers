package ru.mom.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "Сведения об ошибке валидации")
public class Violation {

    private final String fieldName;
    private final String message;

}