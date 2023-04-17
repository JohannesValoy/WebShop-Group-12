package no.ntnu.webshop.group12.webshop.models.cart;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "cart_id")
    private List<Quantity> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public Cart(User user) {
        this();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public List<Quantity> getProducts() {
        return products;
    }

    public void addProduct(Quantity quantity) {
        products.add(quantity);
    }

    public void removeProduct(Quantity quantity) {
        products.remove(quantity);
    }

    public void setProducts(List<Quantity> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quantity getQuantity(Product product) {
        Quantity quantity = null;
        for (int i = 0; quantity == null && i < products.size(); i++) {
            if (products.get(i).getProduct().getId() == product.getId()) {
                quantity = products.get(i);
            }
        }
        return quantity;
    }

}