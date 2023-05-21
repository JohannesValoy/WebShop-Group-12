package no.ntnu.webshop.group12.webshop.repository.purchase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.models.order.purchase.QPurchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer>, QuerydslPredicateExecutor<Purchase>,
QuerydslBinderCustomizer<QPurchase> {

    List<Purchase> findByUser(User sessionUser);

    Page<Purchase> findAll(Predicate predicate, Pageable pageable);
    
    @Override
    default void customize(QuerydslBindings bindings, QPurchase root) {
        bindings.bind(root.date).as("dateAfter").first((path, value) -> path.after(value));
        bindings.bind(root.date).as("dateBefore").first((path, value) -> path.before(value));
        bindings.excludeUnlistedProperties(true);
    }
}
