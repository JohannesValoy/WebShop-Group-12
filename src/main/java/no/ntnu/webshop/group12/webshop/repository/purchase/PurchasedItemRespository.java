package no.ntnu.webshop.group12.webshop.repository.purchase;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.order.purchase.Item;

public interface PurchasedItemRespository extends CrudRepository<Item, Integer>{
    
}
