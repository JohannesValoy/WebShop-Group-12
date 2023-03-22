package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;
import no.ntnu.webshop.group12.webshop.service.ProductService;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;

/**
 * Controller for all HTML pages.
 */
@Controller
public class PageController {

    @Autowired
    ProductService productService;

    @Autowired
    AccessUserService userService;

    /**
     * The `Home` page.
     *
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("products", productService.getAllProducts());
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
        return "cart";
    }

    /**
     * The `Login` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "login";
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

    @PostMapping("/register")
    public String postRegister(@ModelAttribute LoginDTO signupDTO, Model model) {
        model.addAttribute("signupDTO", signupDTO);
        model.addAttribute("user", userService.getSessionUser());
        String page = "login";
        String error = userService.tryCreateNewUser(signupDTO.getUsername(), signupDTO.getPassword());
        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            page = "register";
        }
        return page;
    }

    /**
     * The `About` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    /**
     * The `Contact` page.
     * 
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("user", userService.getSessionUser());
        return "product";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "user";
    }
}
