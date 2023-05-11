package no.ntnu.webshop.group12.webshop.repository.purchase;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.purchase.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByUser(User sessionUser);
    
}
