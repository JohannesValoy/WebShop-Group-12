package no.ntnu.webshop.group12.webshop.repository.purchase;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.order.purchase.Item;

public interface PurchasedItemRepository extends CrudRepository<Item, Integer>{
    
}
