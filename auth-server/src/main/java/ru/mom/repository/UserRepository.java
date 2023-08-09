package ru.mom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mom.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "FROM User u WHERE email in (:email) or login in (:login)")
    Optional<User> findUserByLoginOrEmail(String login, String email);
}
