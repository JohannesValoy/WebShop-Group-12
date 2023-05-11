package no.ntnu.webshop.group12.webshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import no.ntnu.webshop.group12.webshop.models.User;

public interface UserRepository
        extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameIgnoreCase(String username);
}
