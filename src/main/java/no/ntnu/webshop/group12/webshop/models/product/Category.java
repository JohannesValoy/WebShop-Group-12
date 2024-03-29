package no.ntnu.webshop.group12.webshop.models.product;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.webshop.group12.webshop.models.WebpImage;

@Entity
@Table(name = "categories")
@Schema(description = "A category in the webshop", name = "Category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    private WebpImage image;

    @ManyToMany(mappedBy = "category")
    @JsonBackReference
    private Set<Product> products = new LinkedHashSet<>();

    public Category() {
    }

    public Category(String name) {
        this(0, name, null);
    }

    public Category(String name, WebpImage image) {
        this(0, name, image);
    }

    public Category(int id, String name, WebpImage image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
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
        Category other = (Category) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        return true;
    }

}
