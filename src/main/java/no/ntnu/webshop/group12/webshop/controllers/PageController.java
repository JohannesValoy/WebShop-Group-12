package no.ntnu.webshop.group12.webshop.controllers;

import no.ntnu.webshop.group12.webshop.models.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.ProductService;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.service.CartService;
import no.ntnu.webshop.group12.webshop.service.CategoryService;

import java.util.Optional;

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
    @GetMapping("/category")
    public String getCategory(Model model) {
        model.addAttribute("categoryName", "All products");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("user", userService.getSessionUser());
        return "category";
    }

    @GetMapping("/category/{id}")
    public String getCategory(@PathVariable("id") int id, Model model) {
        Optional<Category> category = categoryService.getCategory(id);
        category.ifPresent(value -> model.addAttribute("categoryName", value.getName()));
        if(model.getAttribute("categoryName") == null) {
            return "redirect:/category";
        }
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
        if (userService.getSessionUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("quantities", cartService.getCart().getProducts());
        return "cart";
    }

    @PostMapping("/cart/{id}")
    public String postCart(@PathVariable("id") int id, Model model) {
        cartService.addProductToCart(id);
        model.addAttribute("user", userService.getSessionUser());
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/{id}")
    public String removeProductFromCart(@PathVariable("id") int id, Model model) {
        cartService.removeProductFromCart(id);
        model.addAttribute("user", userService.getSessionUser());
        return "redirect:/cart";
    }

    /**
     * The `Register` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "login";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute LoginDTO register, Model model) {
        model.addAttribute("user", userService.getSessionUser());
        String error = userService.tryCreateNewUser(register.getUsername(), register.getPassword());
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("user", userService.getSessionUser());
        model.addAttribute("products", productService.getRandomProductsByCategory(4, id));
        return "product";
    }



    @GetMapping("/account")
    public String getUser(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        User authenticatedUser = userService.getSessionUser();
        if (authenticatedUser != null) {
            model.addAttribute("user", authenticatedUser);
            return "account";
        } else {
            return "no-access";
        }
    }
}
