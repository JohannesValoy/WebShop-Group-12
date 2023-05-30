package no.ntnu.webshop.group12.webshop.models;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;

@Entity(name = "users")
@Schema(description = "A user in the webshop", name = "User")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String username;

    @NotBlank
    @JsonIgnore
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private Cart cart = new Cart();

    @OneToMany(mappedBy = "user")
    private Set<Purchase> purchases = new LinkedHashSet<>();
    
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
    }

    public boolean isAdmin() {
        return this.hasRole("ROLE_ADMIN");
    }

    public boolean hasRole(String roleName) {
        boolean found = false;
        Iterator<Role> it = roles.iterator();
        while (!found && it.hasNext()) {
            Role role = it.next();
            if (role.getName().equals(roleName)) {
                found = true;
            }
        }
        return found;
    }

}
