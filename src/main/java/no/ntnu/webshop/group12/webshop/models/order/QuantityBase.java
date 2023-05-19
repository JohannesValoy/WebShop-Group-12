package no.ntnu.webshop.group12.webshop.models.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@MappedSuperclass()
@Getter
@Setter
public class QuantityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive
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

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void removeAmount(int amount) {
        this.amount -= amount;
    }

    public long getTotalPrice() {
        return product.getPrice() * amount;
    }
}
