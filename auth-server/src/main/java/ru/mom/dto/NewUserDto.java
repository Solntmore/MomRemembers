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
    @Pattern(regexp = "^[a-zA-Z0-9]{3,16}$",
            message = "Логин может включать цифры, буквы. Длина – от 3 до 16 знаков.")
    @Schema(description = "Логин пользователя")
    @Size(min = 2, max = 20)
    private String login;

    @NotBlank
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_-])(?=\\S+$).{8,}$",
            message = "Пароль должен включать строчные и прописные латинские буквы, " +
                      "цифры, спецсимволы. Минимум 8 символов.")
    @Schema(description = "Пароль пользователя")
    @Size(min = 8)
    private String password;

    @NotBlank
    @Length(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\.]{2,20}$",
            message = "Имя должно включать буквы. Длина - от 2 до 20 символов.")
    @Schema(description = "Имя пользователя.")
    @Size(min = 2, max = 20)
    private String username;

    @Email(message = "Введите email.")
    @NotBlank(message = "Введите email.")
    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$",
            message = "Email должен содержать адрес электронной почты, где в наличии имя адреса, " +
                      "символа @, имя домена, точка после него и доменная зона.")
    @Schema(description = "Электронная почта пользователя")
    private String email;

}
