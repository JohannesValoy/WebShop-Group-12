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
    private String imageWebp512;

    private String imageWebp1024;

    private String imageWebp2048;

    private String imageJpg512;

    private String imageJpg1024;

    private String imageJpg2048;

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

    public void setImageAll(String name) {
        this.image = name + ".jpg";
        this.imageWebp512 = name + "-512w.webp";
        this.imageWebp1024 = name + "-1024w.webp";
        this.imageWebp2048 = name + "-2048w.webp";
        this.imageJpg512 = name + "-512w.jpg";
        this.imageJpg1024 = name + "-1024w.jpg";
        this.imageJpg2048 = name + "-2048w.jpg";
    }
}
