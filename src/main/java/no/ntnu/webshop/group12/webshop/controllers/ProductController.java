package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("")
    @Operation(summary = "Create a new product")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

}
