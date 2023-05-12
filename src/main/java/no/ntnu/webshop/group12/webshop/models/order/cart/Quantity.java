package no.ntnu.webshop.group12.webshop.models.order.cart;

import jakarta.persistence.Entity;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
public class Quantity extends QuantityBase{

    public Quantity() {
        super();
    }

    public Quantity(Product product, int amount) {
        super(product, amount);
    }

    public Quantity(Quantity quantity) {
        super(quantity);
    }
}
