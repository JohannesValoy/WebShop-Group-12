package no.ntnu.webshop.group12.webshop.repository;

import org.codehaus.groovy.classgen.asm.BinaryIntExpressionHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.order.cart.QCart;
public interface CartRepository extends CrudRepository<Cart, Integer>, QuerydslPredicateExecutor<Cart>,
QuerydslBinderCustomizer<QCart> {

    Cart findByUser(User user);

    Page<Cart> findAll(Predicate predicate, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QCart root) {
        bindings.bind(root.user.username).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.bind(root.items.any().product.name).as("product.name").first(StringExpression::containsIgnoreCase);
        bindings.bind(root.items.any().product.id).as("product.id").first(NumberPath::eq);
        bindings.excluding(root.items);
        bindings.excluding(root.user);
    }
}