package no.ntnu.webshop.group12.webshop.models.purchase;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import no.ntnu.webshop.group12.webshop.models.cart.Cart;

public class Purchase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, updatable = false, columnDefinition = "DATE", name = "date")
    LocalDate date;

    Set<Item> products;

    public Purchase() {
    }

    public Purchase(Cart cart) {
        date = LocalDate.now();
        products = new HashSet<>();
        cart.getProducts().forEach(quantity -> {
            addItem(new Item(quantity));
        });
    }

    public void addItem(Item item) {
        products.add(item);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Item> getProducts() {
        return products;
    }

    public void setProducts(Set<Item> products) {
        this.products = products;
    }
    
}
