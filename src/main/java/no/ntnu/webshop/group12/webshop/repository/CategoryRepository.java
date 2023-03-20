package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.product.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
}
