package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.ntnu.webshop.group12.webshop.models.purchase.Purchase;
import no.ntnu.webshop.group12.webshop.service.PurchaseService;

@RestController
@Tag(name = "Purchase", description = "Purchase API")
@RequestMapping("/api/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase by id")
    public Purchase getPurchase(@PathVariable int id) {
        return purchaseService.getPurchase(id);
    }

    @GetMapping()
    @Operation(summary = "Filter purchases")
    public Iterable<Purchase> getPurchases(
        @QuerydslPredicate(root = Purchase.class) Predicate predicate,
        @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return purchaseService.getPurchases(predicate, pageable);
    }


}
