package no.ntnu.webshop.group12.webshop.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.Role;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CategoryRepository;
import no.ntnu.webshop.group12.webshop.repository.UserRepository;
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;
import no.ntnu.webshop.group12.webshop.repository.RoleRepository;

@Component
@Profile("test")
public class TestInitializer implements ApplicationListener<ApplicationReadyEvent> {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private AccessUserService accessUserService;

        private final Logger logger = LoggerFactory.getLogger("DummyInit");

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
                logger.info("Initializing dummy data");

                Category category1 = new Category("Gaming");
                Category category2 = new Category("Office");
                Category category3 = new Category("Headset");
                Category category4 = new Category("Computer Mouse");
                Category category5 = new Category("Console");

                category1.setImage("category 1.png");
                category2.setImage("category 2.png");
                category3.setImage("category 3.png");
                category4.setImage("category 4.png");
                category5.setImage("category 5.png");

                categoryRepository.save(category1);
                categoryRepository.save(category2);
                categoryRepository.save(category3);
                categoryRepository.save(category4);
                categoryRepository.save(category5);

                Product headsetOffice = new Product("Headset for office and gaming",
                                "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                                100, 10);

                headsetOffice.addCategory(category1);
                headsetOffice.addCategory(category2);
                headsetOffice.addCategory(category3);
                headsetOffice.setImage("Product 1.png");

                productRepository.save(headsetOffice);

                Product mouse = new Product("Gaming Mouse",
                                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking. And the best part? The cat isn’t included!",
                                700, 10);

                mouse.addCategory(category1);
                mouse.addCategory(category4);
                mouse.setImage("Product 2.png");

                productRepository.save(mouse);

                Product gamingHeadset = new Product("Hot Gaming Headset",
                                "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                                1200, 0);

                gamingHeadset.addCategory(category1);
                gamingHeadset.addCategory(category3);
                gamingHeadset.setImage("Product 3.png");

                productRepository.save(gamingHeadset);

                Role role = new Role("ROLE_USER");
                Role role2 = new Role("ROLE_ADMIN");
                roleRepository.save(role);
                roleRepository.save(role2);
                accessUserService.tryCreateNewUser("Test", "Test1234");
                accessUserService.tryCreateNewUser("Admin", "Admin1234");
                userRepository.findByUsername("Admin").ifPresent(user -> {
                        user.addRole(role2);
                        userRepository.save(user);
                });
        }
}
