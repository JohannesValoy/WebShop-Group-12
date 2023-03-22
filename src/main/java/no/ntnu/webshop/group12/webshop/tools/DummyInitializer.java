package no.ntnu.webshop.group12.webshop.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CategoryRepository;
import no.ntnu.webshop.group12.webshop.repository.CustomerRepository;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;

@Component
public class DummyInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Initializing dummy data");

        Category category = new Category("Gaming");
        Category category2 = new Category("Office");
        Category category3 = new Category("Headset");
        Category category4 = new Category("Mouse");
        Category category5 = new Category("Console");
        Category category6 = new Category("Xbox");
        Category category7 = new Category("Playstation");
        Category category8 = new Category("Nintendo");
        Category category9 = new Category("Keyboard");
        Category category10 = new Category("Accessories");
        Category category11 = new Category("Controllers");
        Category category12 = new Category("Batteries");

        categoryRepository.save(category);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);
        categoryRepository.save(category7);
        categoryRepository.save(category8);
        categoryRepository.save(category9);
        categoryRepository.save(category10);
        categoryRepository.save(category11);
        categoryRepository.save(category12);

        Product headsetOffice = new Product("Headset for office and gaming",
                "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                100, 10);

        headsetOffice.addCategory(category);
        headsetOffice.addCategory(category2);
        headsetOffice.addCategory(category3);
        headsetOffice.setImage("Product 1.png");

        productRepository.save(headsetOffice);

        Product mouse = new Product("Gaming Mouse",
                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking. And the best part? The cat isn’t included!",
                700, 10);

        mouse.addCategory(category);
        mouse.addCategory(category4);
        mouse.setImage("Product 2.png");

        productRepository.save(mouse);

        Product gamingHeadset = new Product("Hot Gaming Headset",
                "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                1200, 0);

        gamingHeadset.addCategory(category);
        gamingHeadset.addCategory(category3);
        gamingHeadset.setImage("Product 3.png");

        productRepository.save(gamingHeadset);

        Product xbox = new Product("Xbox console + controller",
                "Experience the ultimate gaming experience with the Xbox Console! This sleek white Xbox console comes with the must-have Xbox wireless controller, so you can dive into the action right away. Enjoy stunning 4K visuals, immersive sound, and endless entertainment with the Xbox console. With access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. Plus, you can stream movies, shows, and music, and connect with friends online. Get ready for the ultimate gaming experience with the Xbox Console",
                6800, 10);

        xbox.addCategory(category5);
        xbox.addCategory(category6);
        xbox.setImage("Product 4.jpg");

        productRepository.save(xbox);

        Product xboxController = new Product("Xbox Controller",
                "This Wireless Xbox Controller in White is the perfect accessory for your gaming needs! It features a sleek, modern design with easy to reach buttons and a comfortable grip. The controller is compatible with Xbox One and Xbox Series X|S and is a great choice for both casual and competitive gamers. The wireless connection ensures a lag-free gaming experience and the adjustable sensitivity of the analog sticks allows you too customize your gaming experience. Batteries not included",
                499, 10);

        xboxController.addCategory(category6);
        xboxController.addCategory(category11);
        xboxController.setImage("Product 5.jpg");

        productRepository.save(xboxController);

        Product batteries = new Product("4x AA batteries",
                "This 4x AA Battery Pack is the perfect solution for powering your electronic devices! With four high-capacity AA batteries, you can stay powered up for longer and get the most out of your gadgets. Our AA batteries are long-lasting and reliable, so you can trust that your device will stay powered for as long as you need. Plus, the lightweight and compact design of this battery pack makes it easy to carry and transport. Enjoy up to 10 hours of power with this 4x AA Battery Pack!",
                80, 0);

        batteries.addCategory(category10);
        batteries.addCategory(category12);
        batteries.setImage("Product 6.jpg");

        productRepository.save(batteries);

    }
}
