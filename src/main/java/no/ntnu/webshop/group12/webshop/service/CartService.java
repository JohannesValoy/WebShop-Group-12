package no.ntnu.webshop.group12.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.webshop.group12.webshop.models.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private AccessUserService accessUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    public void addProductToCart(int productId) {
        Cart cart = getCart();
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null == q) {
            cart.addProduct(new Quantity(product, 1));
        } else {
            q.addAmount(1);
        }
        cartRepository.save(cart);
    }

    public void removeProductFromCart(int productId) {
        Cart cart = getCart();
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null != q) {
            cart.removeProduct(q);
            cartRepository.save(cart);
        }
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
    public Cart getCart() {
        Cart cart = cartRepository.findByUser(accessUserService.getSessionUser());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(accessUserService.getSessionUser());
            cartRepository.save(cart);
        }
        return cart;
    }

}
