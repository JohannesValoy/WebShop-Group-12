package no.ntnu.webshop.group12.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.dto.CartPurchase;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;
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

    @Autowired
    private PurchaseService purchaseService;

    public Cart addProductToCart(Cart cart, int productId) throws NotFoundException {
        if (cart == null) {
            return null;
        }
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null != q) {
            q.setAmount(q.getAmount() + 1);
            quantityRepository.save(q);
        } else {
            q = new Quantity(product, 1);
            cart.addProduct(q);
            quantityRepository.save(q);
            cartRepository.save(cart);
        }

        return cart;
    }

    public Product removeProductFromCart(Cart cart, int productId) throws NotFoundException {
        Product product = productService.getProduct(productId);
        Quantity q = cart.getQuantity(product);
        if (null != q) {
            cart.removeProduct(q);
            quantityRepository.delete(q);
            cartRepository.save(cart);
        } else {
            throw new NotFoundException("Product not found in cart");
        }
        return q.getProduct();
    }

    public Product removeProductFromCurrentUserCart(int id) {
        return removeProductFromCart(getCurrentUserCart(), id);
    }

    public Cart addProductToCurrentUserCart (int id) {
        return addProductToCart(getCurrentUserCart(), id);
    }

    public Quantity updateProductQuantity(int productId, int quantity) throws NotFoundException {
        Product product = productService.getProduct(productId);
        if (quantity <= 0) {
            return new Quantity(removeProductFromCart(getCurrentUserCart(),productId), 0);
        } else {
            return setProductQuantity(product, quantity);
        }
    }

    private Quantity setProductQuantity(Product product, int quantity) {
        Cart cart = getCurrentUserCart();
        Quantity q = cart.getQuantity(product);
        if (null != q) {
            q.setAmount(quantity);
            quantityRepository.save(q);
        } else {
            q = new Quantity(product, quantity);
            cart.addProduct(q);
            quantityRepository.save(q);
            cartRepository.save(cart);
        }
        return q;
    }

    /**
     * Get the cart for the current user. If the user does not have a cart, create a
     * new one.
     * 
     * @return The cart for the current user.
     */
    public Cart getCurrentUserCart() {
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

    public Purchase confirmCart(CartPurchase cartPurchase) {
        Cart cart = getCurrentUserCart();
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        Purchase purchase = purchaseService.createPurchaseFromCart(cart);
        cart.clear();      
        cartRepository.save(cart);
        return purchase;
    }

    public Cart getCart(int id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }
        return cart.get();
    }

    public List<Cart> getCarts(Predicate predicate, Pageable pageable) {
        return cartRepository.findAll(predicate, pageable).getContent();
    }

    public Cart clearCurrentUserCart() {
        Cart cart = getCurrentUserCart();
        cart.clear();
        cartRepository.save(cart);
        return cart;
    }

    public Cart clearCart(int id) {
        Cart cart = getCart(id);
        cart.clear();
        cartRepository.save(cart);
        return cart;
    }
}
