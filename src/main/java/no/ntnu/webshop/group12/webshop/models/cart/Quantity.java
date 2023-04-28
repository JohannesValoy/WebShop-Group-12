package no.ntnu.webshop.group12.webshop.models.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Positive
    private int amount;

    public Quantity() {
    }

    public Quantity(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Quantity(Quantity quantity) {
        this.product = quantity.getProduct();
        this.amount = quantity.getAmount();
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void removeAmount(int amount) {
        this.amount -= amount;
    }
}
