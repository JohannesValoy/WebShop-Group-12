package no.ntnu.webshop.group12.webshop.repository;

import java.util.List;

import no.ntnu.webshop.group12.webshop.models.product.Category;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.models.product.QProduct;

public interface ProductRepository
                extends CrudRepository<Product, Integer>, QuerydslPredicateExecutor<Product>,
                QuerydslBinderCustomizer<QProduct> {

        Product findByName(String name);

        List<Product> findByCategory(Category category);

        Product findById(int id);

        List<Product> findAll(Predicate predicate);

        @Override
        default void customize(QuerydslBindings bindings, QProduct root) {
                bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        }
}
