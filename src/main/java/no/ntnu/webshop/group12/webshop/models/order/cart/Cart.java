package no.ntnu.webshop.group12.webshop.models.order.cart;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(name = "cart_quantity", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "quantity_id"))
    private Set<Quantity> items;

    public Cart() {
        items = new LinkedHashSet<>();
    }

    public Cart(User user) {
        this();
        this.user = user;
    }

    /**
     * Copy constructor
     * @param cart The cart to copy
     */
    public Cart(Cart cart) {
        this();
        this.user = cart.getUser();
        cart.getItems().iterator().forEachRemaining(quantity -> this.addProduct(new Quantity(quantity)));
    }

 
    public void addProduct(Quantity quantity) {
        items.add(quantity);
    }

    public void removeProduct(Quantity quantity) {
        items.remove(quantity);
    }

    public Quantity getQuantity(Product product) {
        Optional<Quantity> quantity = items.stream().filter(q -> (q.getProduct().getId() == product.getId()))
                .findFirst();
        return quantity.orElse(null);
    }

}