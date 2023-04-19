package no.ntnu.webshop.group12.webshop.repository;

import java.util.Iterator;
import java.util.List;

import no.ntnu.webshop.group12.webshop.models.product.Category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.product.Product;

public interface ProductRepository
        extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByCategory(Category category);

    Product findById(int id);

    List<Product> findAllByNameAndCategory(String name, Category category,
            Pageable pageable);
}
