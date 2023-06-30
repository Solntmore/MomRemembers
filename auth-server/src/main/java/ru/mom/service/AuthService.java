package ru.mom.service;

import ru.mom.dto.NewUserDto;
import ru.mom.model.JwtRequest;
import ru.mom.model.JwtResponse;

public interface AuthService {

    void register(NewUserDto newUserDto);

    JwtResponse login(JwtRequest authRequest);

    JwtResponse getAccessToken(String refreshToken);

    JwtResponse refresh(String refreshToken);

    JwtAuthentication getAuthInfo();
}
