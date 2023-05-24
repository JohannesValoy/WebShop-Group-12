package no.ntnu.webshop.group12.webshop.controllers.api;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;

/**
 * Controller for handling requests that do not have a handler
 * It will throw a NotFoundException that the advisor will handle
 */
@RestController("/api")
@Order(1)
public class NoHandlerController {

    @RequestMapping("/api/**")
    public void noHandler() {
        throw new NotFoundException("The requested resource was not found");
    }

}
