package no.ntnu.webshop.group12.webshop.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    private Random random = new Random();

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException("Product with id " + id + " not found");
    }

    public List<Product> getProductsByCategory(int id) {
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isPresent()) {
            return productRepository.findByCategory(category.get());
        } else {
            return new ArrayList<>();
        }
    }

    public Set<Product> getRandomProducts(long i) {
        Set<Product> products = new HashSet<>();
        
        if (i > productRepository.count()) {
            i = productRepository.count();
        }

        for (int j = 0; i > j; j++) {
            products.add(productRepository.findById((int) (random.nextInt() * productRepository.count())).get());
        }
        return products;
    }

    public Set<Product> getRandomProductsByCategory(long i, int id) {
        Product baseProduct = productRepository.findById(id).get();
        Set<Category> categories = baseProduct.getCategory();
        return getRandomProductsByCategory(i, baseProduct.getCategory().toArray(new Category[categories.size()])[0]);
    }

    public Set<Product> getRandomProductsByCategory(long i, Category category) {
        Set<Product> products = new HashSet<>();
        List<Product> productsByCategory = productRepository.findByCategory(category);
        if (i > productsByCategory.size()) {
            i = productsByCategory.size();
        }

        for (int j = 0; i > j; j++) {
            products.add(productsByCategory.get((random.nextInt(productsByCategory.size()))));
        }
        return products;
    }

    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> getProductsByFilter(Predicate predicate, Pageable pageable) {
        if (pageable == null)
            return productRepository.findAll(predicate);
        else
            return productRepository.findAll(predicate, pageable).getContent();
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public long getProductCount() {
        return productRepository.count();
    }

}