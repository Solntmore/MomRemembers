package ru.mom.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mom.model.User;
import ru.mom.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPersistServiceImpl implements UserPersistService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String user) {
        return userRepository.findById(user);
    }

    @Override
    public Optional<User> findUserByLoginOrEmail(String login, String email) {
        return userRepository.findUserByLoginOrEmail(login, email);
    }
}
