package no.ntnu.webshop.group12.webshop.repository.purchase;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.webshop.group12.webshop.models.purchase.Item;

//MEMO: Needs to be created for every purchase
public interface PurchasedItemRespository extends CrudRepository<Item, Integer>{
    
}
