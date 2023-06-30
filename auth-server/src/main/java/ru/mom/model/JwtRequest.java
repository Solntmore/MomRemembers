package ru.mom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Schema(description = "Класс, который пользователь присылает, чтобы получить JWT токен")
public class JwtRequest {

    @NotBlank
    @Schema(description = "Логин пользователя")
    private String login;

    @NotBlank
    @Schema(description = "Пароль пользователя")
    private String password;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Электронная почта пользователя")
    private String email;
}