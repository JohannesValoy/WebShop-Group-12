package no.ntnu.webshop.group12.webshop.models.order.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Entity
@Table(name = "quantities")
@Schema(description = "A item within cart", name = "Quantity")
@Getter
@Setter
public class Quantity extends QuantityBase{

    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

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
