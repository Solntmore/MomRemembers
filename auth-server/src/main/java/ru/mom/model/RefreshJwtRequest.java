package ru.mom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Refresh токен")
public class RefreshJwtRequest {

    @Schema(description = "Токен для обновления токена авторизации")
    public String refreshToken;

}
