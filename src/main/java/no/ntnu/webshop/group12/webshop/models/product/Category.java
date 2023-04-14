package no.ntnu.webshop.group12.webshop.models.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String name;

    private String image;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
