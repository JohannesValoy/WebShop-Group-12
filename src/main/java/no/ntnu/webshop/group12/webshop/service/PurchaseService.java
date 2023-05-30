package no.ntnu.webshop.group12.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.ForbiddenException;
import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchaseRepository;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchasedItemRepository;
@Service
public class PurchaseService {

    @Autowired
    private AccessUserService accessUserService;
 
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchasedItemRepository purchasedItemRepository;

    public Purchase createPurchaseFromCart(Cart cart) {
        Purchase purchase = new Purchase(cart);
        purchase.getItems().forEach(purchasedItemRepository::save);
        purchaseRepository.save(purchase);
        return purchase;
    }

    public Purchase getPurchaseById(int id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> getCurrentUserPurchases() {
        return purchaseRepository.findByUser(accessUserService.getSessionUser());
    }

    public Purchase getPurchase(int id) throws ForbiddenException {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase == null) {
            throw new NotFoundException("Purchase not found");
        } 
        User user = accessUserService.getSessionUser();
        //TODO: Maybe use a postAuthorize tag instead on the api method?
        if(!purchase.getUser().equals(user) && !user.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("You are not allowed to view this purchase");
        }
        return purchase;
    }

    public Iterable<Purchase> getPurchases(Predicate predicate, Pageable pageable) {
        return purchaseRepository.findAll(predicate, pageable).getContent();
    }
    
}
