package ru.mom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
