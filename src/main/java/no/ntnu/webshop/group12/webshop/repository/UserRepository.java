package no.ntnu.webshop.group12.webshop.repository;

import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.dsl.StringPath;

import no.ntnu.webshop.group12.webshop.models.QUser;
import no.ntnu.webshop.group12.webshop.models.User;

public interface UserRepository
        extends CrudRepository<User, Integer>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    Optional<User> findByUsername(String username);

    User findById(int id);

    @Override
    default void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(root.username).first((StringPath path, String value) -> path.like(value));
        bindings.excluding(root.password);
    }
}
