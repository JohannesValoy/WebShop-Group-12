package no.ntnu.webshop.group12.webshop.models.order.purchase;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;

@Entity
@Getter
@Setter
@Schema(description = "A purchase from the webstore")
public class Purchase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "DATE", name = "date", updatable = false)
    LocalDate date;

    @OneToMany
    @Column(updatable = false)
    Set<Item> items = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "user_id")
    @JsonBackReference
    User user;

    @Column(nullable = false, updatable = false, name = "total_price")
    double totalPrice = 0;

    public Purchase() {
    }

    public Purchase(Cart cart) {
        date = LocalDate.now();
        items = new HashSet<>();
        user = cart.getUser();
        cart.getItems().forEach(quantity -> {
            Item item = new Item(quantity);
            addItem(item);
        });
    }

    public void addItem(Item item) {
        items.add(item);
        totalPrice += item.getTotalPrice();
    }
    
}
