package no.ntnu.webshop.group12.webshop.models.cart;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Quantity> products;

    public Cart() {
    }

    public Cart(int id, List<Quantity> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public List<Quantity> getProducts() {
        return products;
    }

}