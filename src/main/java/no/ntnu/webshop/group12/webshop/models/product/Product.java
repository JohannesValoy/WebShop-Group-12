package no.ntnu.webshop.group12.webshop.models.product;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category = new LinkedHashSet<>();

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

    private String imageWebp256;

    private String imageWebp512;

    private String imageWebp1024;

    private String imageWebp2048;

    @Positive
    private int price;

    @PositiveOrZero
    private int stock;

    public Product() {
    }

    public Product(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String description, int price, int stock, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public void addCategory(Category... categories) {
        Collections.addAll(this.category, categories);
    }

    public void removeCategory(Category category) {
        this.category.remove(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageWebp256() {
        return imageWebp256;
    }

    public String getImageWebp512() {
        return imageWebp512;
    }

    public String getImageWebp1024() {
        return imageWebp1024;
    }

    public String getImageWebp2048() {
        return imageWebp2048;
    }

    public void setImageAll(String name) {
        this.image = name + ".webp";
        this.imageWebp256 = name + "-256w.webp";
        this.imageWebp512 = name + "-512w.webp";
        this.imageWebp1024 = name + "-1024w.webp";
        this.imageWebp2048 = name + "-2048w.webp";
    }
}
