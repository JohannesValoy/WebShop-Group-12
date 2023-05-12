package no.ntnu.webshop.group12.webshop.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import no.ntnu.webshop.group12.webshop.service.AccessUserService;

@Order(1)
@Profile("!prod")
public class UserInitializer implements ApplicationListener<ApplicationReadyEvent>{
    
    @Autowired
    private AccessUserService accessUserService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        accessUserService.tryCreateNewUser("Admin", "Admin1234");
        accessUserService.tryCreateNewUser("Test", "Test1234");
    }
}
