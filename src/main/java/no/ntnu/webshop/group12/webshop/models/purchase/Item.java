package no.ntnu.webshop.group12.webshop.models.purchase;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import no.ntnu.webshop.group12.webshop.models.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.product.Product;

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @PositiveOrZero
    private int productPriceAtPurchase;

    @Positive
    private int quantity;

    @NotNull
    private Product product;

    public Item() {
    }

    public Item(Quantity quantity) {
        this.product = quantity.getProduct();
        this.quantity = quantity.getAmount();
        this.productPriceAtPurchase = quantity.getProduct().getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductPriceAtPurchase() {
        return productPriceAtPurchase;
    }

    public void setProductPriceAtPurchase(int productPriceAtPurchase) {
        this.productPriceAtPurchase = productPriceAtPurchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
