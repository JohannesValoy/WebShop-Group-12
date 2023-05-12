package no.ntnu.webshop.group12.webshop.tools;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.service.AccessUserService;

@Component
@Order(1)
@Profile("!prod")
public class UserInitializer implements ApplicationListener<ApplicationReadyEvent>{

    public static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserInitializer.class);
    
    @Autowired
    private AccessUserService accessUserService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOG.info("Creating test users");
        accessUserService.tryCreateNewUser("Admin", "Admin1234");
        accessUserService.tryCreateNewUser("Test", "Test1234");
    }
}
