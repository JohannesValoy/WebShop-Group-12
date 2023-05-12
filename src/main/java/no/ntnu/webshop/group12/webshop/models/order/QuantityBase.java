package no.ntnu.webshop.group12.webshop.models.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@MappedSuperclass()
public class QuantityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public QuantityBase() {
    }

    public QuantityBase(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
    
    public QuantityBase(QuantityBase quantity) {
        this.product = quantity.getProduct();
        this.amount = quantity.getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
