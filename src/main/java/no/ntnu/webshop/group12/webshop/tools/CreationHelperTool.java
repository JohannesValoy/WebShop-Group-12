package no.ntnu.webshop.group12.webshop.tools;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.WebpImage;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CategoryRepository;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;
import no.ntnu.webshop.group12.webshop.repository.WebpImageRepository;

@Component
public class CreationHelperTool {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WebpImageRepository webpImageRepository;

    private final String[] categories = {"Gaming", "Office", "Headset", "Computer Mouse", "Console", "Xbox", "Playstation", "Nintendo", "Keyboard", "Accessories", "Controllers", "Batteries"};

    private Map<String, Category> categoryMap = new HashMap<>();

    private void addPicture(Product product){
        WebpImage.Builder webpBuilder = new WebpImage.Builder();
        WebpImage image = webpBuilder.imageAll("/images/products/" + product.getName().replace(" ", "-") + ".webp").build();
        product.setImage(image);
        webpImageRepository.save(image);
    }

    private void addPicture(Category category){
        WebpImage.Builder webpBuilder = new WebpImage.Builder();
        WebpImage image = webpBuilder.imageAll("/images/categories/" + category.getName().replace(" ", "-") + ".webp").build();
        category.setImage(image);
        webpImageRepository.save(image);
}

    public void detailProductAndSave(Product product, Category ... categories){
        product.addCategory(categories);
        addPicture(product);
        productRepository.save(product);
}

    public void detailCategoryAndSave(Category category){
        addPicture(category);
        categoryMap.put(category.getName(), category);
        categoryRepository.save(category);
    }

    public String[] getCategories() {
        return categories;
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }
    
}
