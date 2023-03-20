package no.ntnu.webshop.group12.webshop.models.cart;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<Quantity> products;

    public Cart() {
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

}