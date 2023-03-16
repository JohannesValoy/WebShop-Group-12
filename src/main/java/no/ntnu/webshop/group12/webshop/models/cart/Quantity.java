package no.ntnu.webshop.group12.webshop.models.cart;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import no.ntnu.webshop.group12.webshop.models.product.Product;

public class Quantity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

    private int amount;

}
