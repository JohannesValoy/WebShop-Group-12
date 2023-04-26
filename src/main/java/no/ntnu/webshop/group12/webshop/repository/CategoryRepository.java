package no.ntnu.webshop.group12.webshop.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.dsl.StringPath;

import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.QCategory;

public interface CategoryRepository extends CrudRepository<Category, Integer>, QuerydslPredicateExecutor<Category>,
        QuerydslBinderCustomizer<QCategory> {

    Category findFirstByName(String name);

    @Override
    default void customize(QuerydslBindings bindings, QCategory root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.image);
    }
}
