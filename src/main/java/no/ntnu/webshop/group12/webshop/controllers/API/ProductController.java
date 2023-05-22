package no.ntnu.webshop.group12.webshop.controllers.API;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.service.ProductService;

/**
 * Rest controller for product
 * 
 * @version 1.0
 * @since 2023-04-25
 */
@RestController
@Tag(name = "Product", description = "Product API")
@RequestMapping("/api/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/count")
    @Operation(summary = "Get number of products")
    public long getProductCount() {
        return productService.getProductCount();
    }

    @GetMapping
    @Operation(summary = "Get products by filter")
    public List<Product> getProductsByFilter(
            @ParameterObject @PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable,
            @ParameterObject @QuerydslPredicate(root = Product.class) Predicate predicate) {
        return productService.getProductsByFilter(predicate, pageable);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("")
    @Operation(summary = "Create a new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

}
