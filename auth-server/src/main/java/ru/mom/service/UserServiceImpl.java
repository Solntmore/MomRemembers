package ru.mom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mom.dto.AuthUserDto;
import ru.mom.exception.NotFoundException;
import ru.mom.jpa.UserPersistService;
import ru.mom.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistService userPersistService;

    private final UserMapper userMapper;


    @Override
    public AuthUserDto getAuthUser(String userId) {

        var user = userPersistService.findUser(userId).orElseThrow(() ->
                new NotFoundException("Требуемый объект не был найден.",
                        String.format("Пользователь = %s не найден.", userId)));

        return userMapper.toAuthUserDto(user);

    }

}
