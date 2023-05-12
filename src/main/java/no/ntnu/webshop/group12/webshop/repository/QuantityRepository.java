package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;

public interface QuantityRepository extends CrudRepository<Quantity, Integer> {

}
