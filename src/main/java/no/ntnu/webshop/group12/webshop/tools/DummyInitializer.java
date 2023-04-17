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
import no.ntnu.webshop.group12.webshop.service.AccessUserService;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;
import no.ntnu.webshop.group12.webshop.repository.RoleRepository;

@Component
@Profile("!prod")
public class DummyInitializer implements ApplicationListener<ApplicationReadyEvent> {

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
                Category category6 = new Category("Xbox");
                Category category7 = new Category("Playstation");
                Category category8 = new Category("Nintendo");
                Category category9 = new Category("Keyboard");
                Category category10 = new Category("Accessories");
                Category category11 = new Category("Controllers");
                Category category12 = new Category("Batteries");

                category1.setImage("category 1.png");
                category2.setImage("category 2.png");
                category3.setImage("category 3.png");
                category4.setImage("category 4.png");
                category5.setImage("category 5.png");
                category6.setImage("category 6.png");
                category7.setImage("category 7.png");
                category8.setImage("category 8.png");
                category9.setImage("category 9.png");
                category10.setImage("category 10.png");
                category11.setImage("category 11.png");
                category12.setImage("category 12.png");

                categoryRepository.save(category1);
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



                Product mouse = new Product("Gaming Mouse",
                                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking. And the best part? The cat isn’t included!",
                                700, 10);

                mouse.addCategory(category1);
                mouse.addCategory(category4);
                mouse.setImage("computer_mouse#3.png");

                Product wirelessMouse = new Product("Wireless Gaming Mouse",
                        "This wireless gaming mouse is perfect for gamers who want freedom of movement without sacrificing accuracy. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its long battery life, you won't have to worryabout running out of juice in the middle of a game. Get the Wireless Gaming Mouse today and take your gaming to the next level!",
                        800, 10);

                wirelessMouse.addCategory(category1);
                wirelessMouse.addCategory(category4);
                wirelessMouse.setImage("computer_mouse_wireless.png");

                Product wiredMouse = new Product("Wired Gaming Mouse",
                        "This wired gaming mouse is perfect for gamers who want a reliable, high-performance mouse without any lag or connectivity issues. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its durable construction and long cable, you won't have to worry about it breaking or running out of battery. Get the Wired Gaming Mouse today and take your gaming to the next level!",
                        600, 10);

                wiredMouse.addCategory(category1);
                wiredMouse.addCategory(category4);
                wiredMouse.setImage("computer_mouse#1.png");

                productRepository.save(mouse);
                productRepository.save(wirelessMouse);
                productRepository.save(wiredMouse);

                Product headsetOffice = new Product("Headset for office and gaming",
                        "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                        100, 10);

                headsetOffice.addCategory(category1);
                headsetOffice.addCategory(category2);
                headsetOffice.addCategory(category3);
                headsetOffice.setImage("headset#1.png");

                Product wiredHeadset = new Product("Wired Gaming Headset",
                        "If you prefer a wired connection for your gaming headset, the Wired Gaming Headset is the perfect choice. With its comfortable over-ear design, high-quality sound, and noise-cancelling microphone, you'll be able to hear and communicate clearly with your teammates. Its durable construction and long cable make it perfect for long gaming sessions, and its stylish design will make you the envy of all your friends. Get the Wired Gaming Headset today and take your gaming to the next level!",
                        1000, 10);

                wiredHeadset.addCategory(category1);
                wiredHeadset.addCategory(category3);
                wiredHeadset.setImage("headset#2.png");

                Product gamingHeadset = new Product("Hot Gaming Headset",
                        "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                        1200, 0);

                gamingHeadset.addCategory(category1);
                gamingHeadset.addCategory(category3);
                gamingHeadset.setImage("headset_with_mic#1.png");

                productRepository.save(headsetOffice);
                productRepository.save(wiredHeadset);
                productRepository.save(gamingHeadset);

                Product genericController = new Product("Generic Gaming Controller",
                        "The Generic Gaming Controller is the perfect accessory for your gaming needs! It features a sleek, modern design with easy to reach buttons and a comfortable grip. The controller is compatible with most gaming consoles and is a great choice for both casual and competitive gamers. The wired connection ensures a lag-free gaming experience and the adjustable sensitivity of the analog sticks allows you to customize your gaming experience. Get the Generic Gaming Controller today and take your gaming to the next level!",
                        300, 10);

                genericController.addCategory(category1);
                genericController.addCategory(category11);
                genericController.setImage("universal_controller#2.png");

                productRepository.save(genericController);

                Product xbox = new Product("Xbox gaming console + controller",
                        "Experience the ultimate gaming experience with the Xbox Console! This sleek white Xbox console comes with the must-have Xbox wireless controller, so you can dive into the action right away. Enjoy stunning 4K visuals, immersive sound, and endless entertainment with the Xbox console. With access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. Plus, you can stream movies, shows, and music, and connect with friends online. Get ready for the ultimate gaming experience with the Xbox Console",
                        6800, 10);

                xbox.addCategory(category1);
                xbox.addCategory(category5);
                xbox.addCategory(category6);
                xbox.setImage("xbox_with_controller#1.png");

                Product xboxSeriesX = new Product("Xbox Series X",
                        "Experience the most powerful gaming console ever made with the Xbox Series X. With stunning 4K visuals, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the Xbox Series X looks great in any gaming setup. Get the Xbox Series X today and take your gaming to the next level!",
                        9000, 10);

                xboxSeriesX.addCategory(category1);
                xboxSeriesX.addCategory(category5);
                xboxSeriesX.addCategory(category6);
                xboxSeriesX.setImage("xbox.png");

                Product xboxSeriesS = new Product("Xbox Series S",
                        "Experience next-generation gaming with the Xbox Series S. With lightning-fast load times, high-quality visuals, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its compact design and customizable lighting, the Xbox Series S is perfect for any gaming setup. Get the Xbox Series S today and take your gaming to the next level!",
                        5000, 10);

                xboxSeriesS.addCategory(category1);
                xboxSeriesS.addCategory(category5);
                xboxSeriesS.addCategory(category6);
                xboxSeriesS.setImage("xbox_with_controller#2.png");

                productRepository.save(xbox);
                productRepository.save(xboxSeriesX);
                productRepository.save(xboxSeriesS);

                Product xboxWirelessController = new Product("Xbox Wireless Controller",
                        "Experience the ultimate in wireless gaming with the Xbox Wireless Controller. With its comfortable design, precise control, and wireless connectivity, you'll have everything you need to dominate the competition. Plus, with its customizable lighting and easy-to-reach buttons, you can personalize your controller to match your gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Xbox Wireless Controller today and take your gaming to the next level!",
                        700, 10);

                xboxWirelessController.addCategory(category1);
                xboxWirelessController.addCategory(category6);
                xboxWirelessController.addCategory(category11);
                xboxWirelessController.setImage("xbox_controller#1.png");

                productRepository.save(xboxWirelessController);

                Product gamingKeyboard = new Product("Gaming Keyboard",
                        "Take your gaming to the next level with the Gaming Keyboard. With its customizable lighting, programmable keys, and durable construction, you'll have everything you need to dominate the competition. Its ergonomic design ensures maximum comfort, even during long gaming sessions. And with its anti-ghosting technology, you can press multiple keys at once without any interference. Get the Gaming Keyboard today and take your gaming to the next level!",
                        1200, 10);

                gamingKeyboard.addCategory(category1);
                gamingKeyboard.addCategory(category9);
                gamingKeyboard.setImage("keyboard#1.png");

                productRepository.save(gamingKeyboard);

                Product nintendoSwitch = new Product("Nintendo Switch",
                        "Experience the ultimate in portable gaming with the Nintendo Switch. With its versatile design, you can play your favorite games on the go or at home. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its detachable Joy-Con controllers, you can play with a friend or on your own. Get the Nintendo Switch today and take your gaming to the next level!",
                        5000, 10);

                nintendoSwitch.addCategory(category1);
                nintendoSwitch.addCategory(category5);
                nintendoSwitch.addCategory(category8);
                nintendoSwitch.setImage("nintendo#1.png");

                productRepository.save(nintendoSwitch);

                Product playstationX = new Product("PlayStation X",
                        "Experience the ultimate gaming experience with the PlayStation 5. With stunning graphics, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the PlayStation 5 looksgreat in any gaming setup. Get the PlayStation 5 today and take your gaming to the next level!",
                        9000, 10);

                playstationX.addCategory(category1);
                playstationX.addCategory(category5);
                playstationX.addCategory(category7);
                playstationX.setImage("Playstation#1.png");

                productRepository.save(playstationX);



                Role role = new Role("ROLE_USER");
                Role role2 = new Role("ROLE_ADMIN");
                roleRepository.save(role);
                roleRepository.save(role2);

                accessUserService.tryCreateNewUser("Test", "Test1234");

        }
}
