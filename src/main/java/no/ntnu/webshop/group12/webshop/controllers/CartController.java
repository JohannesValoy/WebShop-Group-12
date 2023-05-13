package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.webshop.group12.webshop.service.CartService;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart", description = "The cart API")
public class CartController {

    @Autowired
    private CartService cartService;

    @DeleteMapping("/product/{id}")
    @Operation(summary = "Delete product from cart")
    public Product deleteProductFromCart(@PathVariable int id) {
        return cartService.removeProductFromCart(id);
    }

    @PatchMapping("/product/{id}/quantity/{quantity}")
    @Operation(summary = "Update product quantity")
    public Quantity updateProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        return cartService.updateProductQuantity(id, quantity);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cart by id")
    public Cart getCart(@PathVariable int id) {
        return cartService.getCart(id);
    }

    @GetMapping("/me")  
    @Operation(summary = "Get current user's cart")
    public Cart getMyCart() {
        return cartService.getCurrentUserCart();
    }

    @GetMapping()
    @Operation(summary = "Get cars by filter")
    public Iterable<Cart> getCarts(@PageableDefault(size = 20, sort = "id") Pageable pageable, @QuerydslPredicate(root = Cart.class) String predicate) {
        return cartService.getCarts(predicate, pageable);
    }


}
