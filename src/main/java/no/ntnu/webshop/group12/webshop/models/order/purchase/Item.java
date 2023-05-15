package no.ntnu.webshop.group12.webshop.models.order.purchase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;

@Entity
@Getter
@Setter
public class Item extends QuantityBase{

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int productPriceAtPurchase;

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int amount;

    public Item() {
        super();
    }

    public Item(Quantity quantity) {
        super(quantity);
        this.amount = quantity.getAmount();
        this.productPriceAtPurchase = quantity.getProduct().getPrice();
    }

    @Override
    public long getTotalPrice() {
        return productPriceAtPurchase * amount;
    }
}
