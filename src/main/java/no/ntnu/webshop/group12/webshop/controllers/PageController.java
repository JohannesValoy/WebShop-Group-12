package no.ntnu.webshop.group12.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for all HTML pages.
 */
@Controller
public class PageController {

    /**
     * The `Home` page.
     *
     * @return Name of the ThymeLeaf template to render
     */
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/category")
    public String getCategory() {
        return "category";
    }

    @GetMapping("/cart")
    public String getCart() {
        return "cart";
    }

    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

}
