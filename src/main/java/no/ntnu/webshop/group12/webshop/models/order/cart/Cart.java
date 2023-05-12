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
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(name = "cart_quantity", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "quantity_id"))
    private Set<Quantity> products;

    public Cart() {
        products = new LinkedHashSet<>();
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
        cart.getProducts().iterator().forEachRemaining(quantity -> this.addProduct(new Quantity(quantity)));
    }

    public int getId() {
        return id;
    }

    public Set<Quantity> getProducts() {
        return products;
    }

    public void addProduct(Quantity quantity) {
        products.add(quantity);
    }

    public void removeProduct(Quantity quantity) {
        products.remove(quantity);
    }

    public void setProducts(Set<Quantity> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quantity getQuantity(Product product) {
        Optional<Quantity> quantity = products.stream().filter(q -> (q.getProduct().getId() == product.getId()))
                .findFirst();
        return quantity.orElse(null);
    }

}