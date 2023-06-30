package ru.mom.service;

import ru.mom.dto.AuthUserDto;

public interface UserService {

    AuthUserDto getAuthUser(String userId);

}
