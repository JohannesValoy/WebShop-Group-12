package no.ntnu.webshop.group12.webshop.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.Role;
import no.ntnu.webshop.group12.webshop.repository.RoleRepository;

/**
 * This class is used to initialize the roles in the database.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoleInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RoleRepository roleRepository;
    

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Role role = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        roleRepository.save(role);
        roleRepository.save(role2);
    }
    
}
