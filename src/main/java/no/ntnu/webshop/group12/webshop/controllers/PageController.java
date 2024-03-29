package no.ntnu.webshop.group12.webshop.controllers;

import no.ntnu.webshop.group12.webshop.models.product.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import com.querydsl.core.types.Predicate;

import jakarta.servlet.http.HttpServletRequest;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.CartPurchase;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.ProductService;
import no.ntnu.webshop.group12.webshop.service.PurchaseService;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.CartService;
import no.ntnu.webshop.group12.webshop.service.CategoryService;

/**
 * Controller for all HTML pages.
 */
@Controller
public class PageController {

    @Autowired
    ProductService productService;

    @Autowired
    AccessUserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    CartService cartService;

    /**
     * The `Home` page.
     *
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("user", userService.getSessionUser());
        return "index";
    }

    /**
     * The `Category` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/categories")
    public String getCategory(Model model) {
        model.addAttribute("categoryName", "All products");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("user", userService.getSessionUser());
        return "category";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") int id, Model model) {
        if (id == 0) {
            return "redirect:/categories";
        }
        model.addAttribute("categoryName", categoryService.getCategory(id).getName());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getProductsByCategory(id));
        model.addAttribute("user", userService.getSessionUser());
        return "category";
    }

    /**
     * The `Cart` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        model.addAttribute("cart", cartService.getCurrentUserCart());
        return "cart";
    }

    @PostMapping("/cart/{id}")
    public String postCart(@PathVariable("id") int id, Model model) throws NotFoundException {
        cartService.addProductToCurrentUserCart(id);
        model.addAttribute("user", userService.getSessionUser());
        return "redirect:/cart";
    }

    @PostMapping("/cart/confirm")
    public String confirmCart(Model model, @ModelAttribute CartPurchase body) {
        model.addAttribute("user", userService.getSessionUser());
        try {
            model.addAttribute("purchase", cartService.confirmCart(body));
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "cart";
        }
        return "purchase-confirmed";
    }

    @GetMapping(value = { "/login", "/register" })
    public String getLogin(Model model, HttpServletRequest http) {
        if (userService.getSessionUser() != null) {
            return "redirect:/account";
        }
        model.addAttribute("user", userService.getSessionUser());
        model.addAttribute("url", http.getRequestURI());
        return "login";
    }

    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute LoginDTO register, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("user", userService.getSessionUser());
        try {
            userService.tryCreateNewUser(register.getUsername(), register.getPassword());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) throws NotFoundException {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("user", userService.getSessionUser());
        model.addAttribute("products", productService.getRandomProductsByCategory(4, id));
        return "product";
    }

    @GetMapping("/account")
    public String getUser(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        User authenticatedUser = userService.getSessionUser();
        model.addAttribute("user", authenticatedUser);
        model.addAttribute("purchases", purchaseService.getCurrentUserPurchases());
        return "account";
    }

    @GetMapping("/page-error")
    public String getError(Model model, HttpServletRequest http) {
        model.addAttribute("user", userService.getSessionUser());
        return "error";
    }

    @GetMapping("/search")
    public String getSearch(Model model, @QuerydslPredicate(root = Product.class) Predicate predicate) {
        model.addAttribute("user", userService.getSessionUser());
        model.addAttribute("products", productService.getProductsByFilter(predicate, null));
        return "search";
    }
}
