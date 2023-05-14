package ru.mom.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mom.model.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {
}
