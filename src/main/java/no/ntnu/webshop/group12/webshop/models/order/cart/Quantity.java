package no.ntnu.webshop.group12.webshop.models.order.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
@Table(name = "quantities")
@Schema(description = "A item within cart", name = "Quantity")
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
