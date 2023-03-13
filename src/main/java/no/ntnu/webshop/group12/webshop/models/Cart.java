package no.ntnu.webshop.group12.webshop.models;

import java.util.Map;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
    private Map<Product, Integer> products;
    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Cart() {
    }

    public Cart(Map<Product, Integer> products, User user) {
        this.products = products;
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void updateProductQuantity(Product product, int quantity) {
        products.replace(product, quantity);
    }
}