package no.ntnu.webshop.group12.webshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.product.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByCategory(String category);

    Product findById(int id);
}
