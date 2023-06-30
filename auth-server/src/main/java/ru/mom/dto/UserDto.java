package ru.mom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Электронная почта пользователя")
    private String email;

}
