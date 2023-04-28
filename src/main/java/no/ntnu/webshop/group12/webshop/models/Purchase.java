package no.ntnu.webshop.group12.webshop.models;

import java.time.LocalDate;

import no.ntnu.webshop.group12.webshop.models.cart.Cart;

public class Purchase extends Cart {

    LocalDate date;

    public Purchase() {
        super();
        date = LocalDate.now();
    }

    public Purchase(Cart cart) {
        super(cart);
        date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
