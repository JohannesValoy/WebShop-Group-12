package no.ntnu.webshop.group12.webshop.controllers;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.service.ProductService;

@RestController
@RequestMapping("/api/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public Product getProduct(int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/filter")
    @Operation(summary = "Get products by filter")
    public Iterable<Product> getProductsByFilter(
            @QuerydslPredicate(root = Product.class) Predicate predicate) {
        return productService.getProductsByFilter(predicate);
    }

    @PostMapping("")
    @Operation(summary = "Create a new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

}
