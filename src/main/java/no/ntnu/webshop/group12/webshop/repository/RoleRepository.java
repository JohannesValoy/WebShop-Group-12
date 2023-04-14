package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
