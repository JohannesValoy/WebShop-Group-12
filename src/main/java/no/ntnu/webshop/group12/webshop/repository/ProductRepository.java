package no.ntnu.webshop.group12.webshop.repository;

import java.util.List;

import no.ntnu.webshop.group12.webshop.models.product.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.SimpleExpression;

import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.models.product.QProduct;

public interface ProductRepository
                extends CrudRepository<Product, Integer>, QuerydslPredicateExecutor<Product>,
                QuerydslBinderCustomizer<QProduct> {

        Product findByName(String name);

        List<Product> findByCategory(Category category);

        List<Product> findAll(Predicate predicate);

        Page<Product> findAll(Predicate predicate, Pageable pageable);

        @Override
        default void customize(QuerydslBindings bindings, QProduct root) {
                bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.category.any().id).as("category.id").first(SimpleExpression::eq);
                bindings.bind(root.category.any().name).as("category.name")
                                .first(StringExpression::containsIgnoreCase);
                bindings.excluding(root.category);
                bindings.excluding(root.description);
                bindings.excluding(root.stock);
                bindings.excluding(root.image);
        }

        
}
