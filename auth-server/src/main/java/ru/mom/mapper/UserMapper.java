package ru.mom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.mom.dto.AuthUserDto;
import ru.mom.dto.UserDto;
import ru.mom.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "username", source = "user.username")
    UserDto toUserDto(User user);

    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "hash", source = "user.password")
    AuthUserDto toAuthUserDto(User user);
}
