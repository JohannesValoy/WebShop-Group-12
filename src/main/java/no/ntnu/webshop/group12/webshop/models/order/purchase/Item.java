package no.ntnu.webshop.group12.webshop.models.order.purchase;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.order.QuantityBase;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;

@Entity
@Table(name = "bought_items")
@Schema(description = "A item bought", name = "Item")
@Getter
@Setter
public class Item extends QuantityBase{

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int productPriceAtPurchase;

    @PositiveOrZero
    @Column(nullable = false, columnDefinition = "INT", updatable = false)
    private int amount;

    @ManyToOne
    @JsonBackReference
    private Purchase purchase;

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
