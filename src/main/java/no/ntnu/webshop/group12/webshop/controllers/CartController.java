package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.ntnu.webshop.group12.webshop.service.CartService;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @DeleteMapping("/product/{id}")
    public Product deleteProductFromCart(@PathVariable int id) {
        return cartService.removeProductFromCart(id);
    }

    @PatchMapping("product/{id}/quantity/{quantity}")
    public Product updateProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        return cartService.updateProductQuantity(id, quantity);
    }

}
