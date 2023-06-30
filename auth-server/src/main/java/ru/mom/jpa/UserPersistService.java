package ru.mom.jpa;

import ru.mom.model.User;

import java.util.Optional;

public interface UserPersistService {

    void register(User user);

    Optional<User> findUser(String user);
}
