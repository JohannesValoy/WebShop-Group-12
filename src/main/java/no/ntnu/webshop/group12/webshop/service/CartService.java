package no.ntnu.webshop.group12.webshop.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.webshop.group12.webshop.models.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CartRepository;
import no.ntnu.webshop.group12.webshop.repository.QuantityRepository;

@Service
public class CartService {

    @Autowired
    private AccessUserService accessUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private QuantityRepository quantityRepository;

    public void addProductToCart(int productId) {
        Cart cart = getCart();
        // TODO: Find a better way to do this.
        if (cart == null) {
            return;
        }
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null == q) {
            q = new Quantity(product, 1);
            cart.addProduct(q);
            quantityRepository.save(q);
            cartRepository.save(cart);
        } else {
            q.addAmount(1);
            quantityRepository.save(q);
        }
    }

    public Product removeProductFromCart(int productId) {
        Product returnProduct = null;
        Cart cart = getCart();
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null != q) {
            cart.removeProduct(q);
            cartRepository.save(cart);
            returnProduct = product;
        }
        return returnProduct;
    }

    public void updateProductQuantity(int productId, int quantity) {

    }

    public void checkout() {

    }

    /**
     * Get the cart for the current user. If the user does not have a cart, create a
     * new one.
     * 
     * @return The cart for the current user.
     */
    private Cart getCart() {
        // TODO: Find a better way to do this.
        if (accessUserService.getSessionUser() == null) {
            return null;
        }
        Cart cart = cartRepository.findByUser(accessUserService.getSessionUser());
        if (cart == null) {
            cart = new Cart(accessUserService.getSessionUser());
            cartRepository.save(cart);
        }
        return cart;
    }

    public void confirmCart() {
        Cart cart = getCart();
        cartRepository.delete(cart);
    }

    public Set<Quantity> getCurrentUserProducts() {
        return getCart().getProducts();
    }
}
