package no.ntnu.webshop.group12.webshop.repository.purchase;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.models.purchase.QPurchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer>, QuerydslPredicateExecutor<Purchase>,
QuerydslBinderCustomizer<QPurchase> {

    List<Purchase> findByUser(User sessionUser);

    @Override
    default void customize(QuerydslBindings bindings, QPurchase root) {
        bindings.excluding(root.products);

    }
}
