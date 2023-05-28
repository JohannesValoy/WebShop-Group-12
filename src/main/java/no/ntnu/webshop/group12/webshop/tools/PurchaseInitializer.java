package no.ntnu.webshop.group12.webshop.tools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.order.cart.Quantity;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Item;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchaseRepository;
import no.ntnu.webshop.group12.webshop.repository.purchase.PurchasedItemRespository;
import no.ntnu.webshop.group12.webshop.service.ProductService;
import no.ntnu.webshop.group12.webshop.service.PurchaseService;
import no.ntnu.webshop.group12.webshop.service.UserService;

@Component
@Profile("!prod")
@Order(3)
public class PurchaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchasedItemRespository itemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    private Random random = new Random();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(2021, 1, 1));
        dates.add(LocalDate.of(2021, 2, 1));
        dates.add(LocalDate.of(2021, 3, 1));
        dates.add(LocalDate.of(2021, 4, 1));
        dates.add(LocalDate.of(2021, 5, 1));
        dates.add(LocalDate.of(2021, 6, 1));
        User user = userService.getUser("Test");
        for (int i = 0; i < 5; i++) {
            Purchase purchase = generatePurchase();
            purchase.setUser(user);
            purchase.setDate(dates.get(i));
            purchaseRepository.save(purchase);
        }

    }

    public Purchase generatePurchase() {
        Purchase purchase = new Purchase();
        List<Product> products = new ArrayList<>();
        productService.getAllProducts().forEach(products::add);
        int numberOfProducts = random.nextInt(6) + 1;
        while (purchase.getItems().size() < numberOfProducts) {
            int index = random.nextInt(products.size());
            Item item = new Item(new Quantity(products.get(index), random.nextInt(5) + 1));
            itemRepository.save(item);
            purchase.addItem(item);
            products.remove(index);
        }
        return purchase;
    }

}
