package no.ntnu.webshop.group12.webshop.models.product;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.WebpImage;

@Entity
@Table(name = "products")
@Schema(description = "A product in the webshop", name = "Product")
@Getter
@Setter
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category = new LinkedHashSet<>();

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private WebpImage image;

    @Positive
    private int price;

    public Product() {
    }

    public Product(String name, String description, int price) {
        this(name, description, price, null);
    }

    public Product(String name, String description, int price, WebpImage image) {
        this.name = name;
        this.description = description;
        if (description != null) {
            createShortDescriptive();
        }
        this.price = price;
        this.image = image;
    }
  
    public Product(Product product) {
        this.id = product.getId();
        this.category = product.getCategory();
        this.name = product.getName();
        this.description = product.getDescription();
        this.shortDescription = product.getShortDescription();
        this.price = product.getPrice();
        this.image = product.getImage();
    }

    private void createShortDescriptive() {
        char[] chars = {'.', '!', '?'};
        for (char c : chars) {
            int index = description.indexOf(c);
            if (index != -1 && (shortDescription == null || index < shortDescription.length())) {
                shortDescription = description.substring(0, index + 1);
            }
        }
        if (shortDescription == null) {
            shortDescription = description;
        }
    }

    public void addCategory(Category... categories) {
        Collections.addAll(this.category, categories);
    }

    public void removeCategory(Category category) {
        this.category.remove(category);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + price;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (shortDescription == null) {
            if (other.shortDescription != null)
                return false;
        } else if (!shortDescription.equals(other.shortDescription))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (price != other.price)
            return false;
        return true;
    }
    

}
