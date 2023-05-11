package no.ntnu.webshop.group12.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
