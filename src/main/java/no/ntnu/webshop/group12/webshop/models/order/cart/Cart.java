package no.ntnu.webshop.group12.webshop.models.order.cart;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
@Table(name = "cart")
@Schema(description = "A Cart used to buy products", name = "Cart")
@Getter
@Setter
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
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

    /**
     * Add a Quantity of a product to the cart
     * @param quantity The quantity of the product to add
     */
    public void addProduct(Quantity quantity) {
        items.add(quantity);
    }

    /**
     * Remove a Quantity of a product from the cart
     * @param quantity The quantity of the product to remove
     */
    public void removeProduct(Quantity quantity) {
        items.remove(quantity);
    }

    /**
     * Get the quantity of a product in the cart 
     * @param product The product to get the quantity of
     * @return The quantity of the product in the cart or null if the product is not in the cart
     */
    public Quantity getQuantity(Product product) {
        Optional<Quantity> quantity = items.stream().filter(q -> (q.getProduct().getId() == product.getId()))
                .findFirst();
        return quantity.orElse(null);
    }

    /**
     * Clears the cart
     */
    public void clear() {
        items.clear();
    }

}