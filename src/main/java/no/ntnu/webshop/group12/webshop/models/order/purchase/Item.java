package no.ntnu.webshop.group12.webshop.models.order.purchase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;

@Entity
public class Item extends QuantityBase{

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int productPriceAtPurchase;

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int quantity;

    public Item() {
        super();
    }

    public Item(Quantity quantity) {
        super(quantity);
        this.quantity = quantity.getAmount();
        this.productPriceAtPurchase = quantity.getProduct().getPrice();
    }

    public int getProductPriceAtPurchase() {
        return productPriceAtPurchase;
    }

    public void setProductPriceAtPurchase(int productPriceAtPurchase) {
        this.productPriceAtPurchase = productPriceAtPurchase;
    }
}
