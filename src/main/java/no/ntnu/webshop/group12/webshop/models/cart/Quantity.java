package no.ntnu.webshop.group12.webshop.models.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
public class Quantity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Positive
    private int amount;

    public Quantity() {
    }

    public Quantity(Cart cart, Product product, int amount) {
        this.cart = cart;
        this.product = product;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

}
