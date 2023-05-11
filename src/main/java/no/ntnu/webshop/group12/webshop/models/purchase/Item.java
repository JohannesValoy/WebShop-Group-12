package no.ntnu.webshop.group12.webshop.models.purchase;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import no.ntnu.webshop.group12.webshop.models.cart.Quantity;

@Entity
public class Item extends Quantity{

    @PositiveOrZero
    private int productPriceAtPurchase;

    public Item() {
        super();
    }

    public Item(Quantity quantity) {
        super(quantity);
        this.productPriceAtPurchase = quantity.getProduct().getPrice();
    }

    public int getProductPriceAtPurchase() {
        return productPriceAtPurchase;
    }

    public void setProductPriceAtPurchase(int productPriceAtPurchase) {
        this.productPriceAtPurchase = productPriceAtPurchase;
    }
    
}
