package ru.mom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mom.dto.ErrorResponse;
import ru.mom.dto.TokenResponse;
import ru.mom.dto.User;
import ru.mom.exception.LoginException;
import ru.mom.exception.RegistrationException;
import ru.mom.service.ClientService;
import ru.mom.service.TokenGenerateService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final TokenGenerateService tokenGenerateService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        clientService.register(user.getClientId(), user.getClientSecret());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) {
        clientService.checkCredentials(user.getClientId(), user.getClientSecret());
        return new TokenResponse(tokenGenerateService.generateToken(user.getClientId()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
