package no.ntnu.webshop.group12.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.ForbiddenException;
import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchaseRepository;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchasedItemRespository;
@Service
public class PurchaseService {

    @Autowired
    private AccessUserService accessUserService;
 
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchasedItemRespository purchasedItemRespository;

    public Purchase createPurchaseFromCart(Cart cart) {
        Purchase purchase = new Purchase(cart);
        purchase.getProducts().forEach(purchasedItemRespository::save);
        purchaseRepository.save(purchase);
        return purchase;
    }

    public Purchase getPurchaseById(int id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> getCurrentUserPurchases() {
        return purchaseRepository.findByUser(accessUserService.getSessionUser());
    }

    public Purchase getPurchase(int id) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase == null) {
            throw new NotFoundException("Purchase not found");
        } 
        User user = accessUserService.getSessionUser();
        if(!purchase.getUser().equals(user) || !user.hasRole("ADMIN")) {
            throw new ForbiddenException("You are not allowed to view this purchase");
        }
        return purchase;
    }

    public Iterable<Purchase> getPurchases(Predicate predicate, Pageable pageable) {
        return purchaseRepository.findAll(predicate, pageable);
    }
    
}
