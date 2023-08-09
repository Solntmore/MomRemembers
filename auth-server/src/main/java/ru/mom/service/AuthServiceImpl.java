package ru.mom.service;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mom.dto.AuthUserDto;
import ru.mom.dto.NewUserDto;
import ru.mom.exception.AuthException;
import ru.mom.exception.RegistrationException;
import ru.mom.jpa.UserPersistService;
import ru.mom.mapper.UserMapper;
import ru.mom.model.JwtRequest;
import ru.mom.model.JwtResponse;
import ru.mom.model.User;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final UserPersistService userPersistService;

    private final JwtProvider jwtProvider;

    private final UserMapper userMapper;

    private final Map<String, String> refreshStorage = new HashMap<>();

    @Override
    public void register(@NonNull NewUserDto newUserDto) {

        var user = userPersistService.findUserByLoginOrEmail(newUserDto.getLogin(), newUserDto.getEmail());

        if (!user.isEmpty() && user.get().getLogin().equals(newUserDto.getLogin())) {
            throw new RegistrationException("Ошибка регистрации", "Пользователь с таким логином уже существует.");

        } else if (!user.isEmpty() && user.get().getEmail().equals(newUserDto.getEmail())) {
            throw new RegistrationException("Ошибка регистрации", "Пользователь с таким email уже существует.");

        } else {
            String hash = BCrypt.hashpw(newUserDto.getPassword(), BCrypt.gensalt());

            userPersistService.register(
                    new User(newUserDto.getLogin(), hash, newUserDto.getUsername(), newUserDto.getEmail()));
        }

    }

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) {

        var user = userPersistService.findUser(authRequest.getLogin());

        if (user.isEmpty()) {
            throw new AuthException("Ошибка авторизации",
                    String.format("Пользователь с таким логином не существует", authRequest.getLogin()));
        }

        var userAuth = userMapper.toAuthUserDto(user.get());

        if (BCrypt.checkpw(authRequest.getPassword(), userAuth.getHash())) {
            String accessToken = jwtProvider.generateAccessToken(userAuth);
            String refreshToken = jwtProvider.generateRefreshToken(userAuth);
            refreshStorage.put(userAuth.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Ошибка авторизации", "Неправильный пароль");
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AuthUserDto user = userService.getAuthUser(login);
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                AuthUserDto user = userService.getAuthUser(login);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Ошибка авторизации", "Невалидный JWT токен");
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
