package ru.mom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Новый пользователь")
public class NewUserDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$",
            message = "Логин должен включать буквы, ограничение 2-20 символов.")
    @Schema(description = "Логин пользователя")
    private String login;

    @NotBlank
    @Length(min = 8, max = 8)
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$",
            message = "Пароль должен включать буквы, цифры или символы без пробелов.")
    @Schema(description = "Пароль пользователя")
    private String password;

    @NotBlank
    @Length(min = 1, max = 20)
    @Schema(description = "Имя пользователя")
    @Size(min = 1, max = 20)
    private String username;

    @Email(message = "Введите email.")
    @NotBlank(message = "Введите email.")
    @Schema(description = "Электронная почта пользователя")
    private String email;

}
