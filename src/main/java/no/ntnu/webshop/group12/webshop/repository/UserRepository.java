package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);
}
