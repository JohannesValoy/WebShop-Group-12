package no.ntnu.webshop.group12.webshop.models;

import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;
    private User user;

    public Cart(Map<Product, Integer> products, User user) {
        this.products = products;
        this.user = user;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setUser(User user) {
        this.user = user;
    }
}