package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByUsername(String username);

    Customer findById(int id);
}
