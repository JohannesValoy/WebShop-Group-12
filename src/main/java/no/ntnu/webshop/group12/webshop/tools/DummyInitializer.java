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

                category1.setImageAll("category 1");
                category2.setImageAll("category 2");
                category3.setImageAll("category 3");
                category4.setImageAll("category 4");
                category5.setImageAll("category 5");
                category6.setImageAll("category 6");
                category7.setImageAll("category 7");
                category8.setImageAll("category 8");
                category9.setImageAll("category 9");
                category10.setImageAll("category 10");
                category11.setImageAll("category 11");
                category12.setImageAll("category 12");

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
                                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking!",
                                700, 10);

                mouse.addCategory(category1, category4);
                mouse.setImageAll("computer_mouse-3");

                Product wirelessMouse = new Product("Wireless Gaming Mouse",
                        "This wireless gaming mouse is perfect for gamers who want freedom of movement without sacrificing accuracy. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Wireless Gaming Mouse today and take your gaming to the next level!",
                        800, 10);

                wirelessMouse.addCategory(category1, category4);
                wirelessMouse.setImageAll("computer_mouse_wireless");


                Product wiredMouse = new Product("Wired Gaming Mouse",
                        "This wired gaming mouse is perfect for gamers who want a reliable, high-performance mouse without any lag or connectivity issues. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its durable construction and long cable, you won't have to worry about it breaking or running out of battery. Get the Wired Gaming Mouse today and take your gaming to the next level!",
                        600, 10);

                wiredMouse.addCategory(category1, category4);
                wiredMouse.setImageAll("computer_mouse-1");

                productRepository.save(mouse);
                productRepository.save(wirelessMouse);
                productRepository.save(wiredMouse);

                Product headsetOffice = new Product("Headset for office and gaming",
                        "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                        100, 10);

                headsetOffice.addCategory(category1, category2, category3);
                headsetOffice.setImageAll("headset-1");

                Product wiredHeadset = new Product("Wired Gaming Headset",
                        "If you prefer a wired connection for your gaming headset, the Wired Gaming Headset is the perfect choice. With its comfortable over-ear design, high-quality sound, and noise-cancelling microphone, you'll be able to hear and communicate clearly with your teammates. Its durable construction and long cable make it perfect for long gaming sessions, and its stylish design will make you the envy of all your friends. Get the Wired Gaming Headset today and take your gaming to the next level!",
                        1000, 10);

                wiredHeadset.addCategory(category1, category3);
                wiredHeadset.setImageAll("headset-2");

                Product gamingHeadset = new Product("Hot Gaming Headset",
                        "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                        1200, 0);

                gamingHeadset.addCategory(category1, category3);
                gamingHeadset.setImageAll("headset_with_mic-1");

                productRepository.save(headsetOffice);
                productRepository.save(wiredHeadset);
                productRepository.save(gamingHeadset);

                Product genericController = new Product("Generic Gaming Controller",
                        "The Generic Gaming Controller is the perfect accessory for your gaming needs! It features a sleek, modern design with easy to reach buttons and a comfortable grip. The controller is compatible with most gaming consoles and is a great choice for both casual and competitive gamers. The wired connection ensures a lag-free gaming experience and the adjustable sensitivity of the analog sticks allows you to customize your gaming experience. Get the Generic Gaming Controller today and take your gaming to the next level!",
                        300, 10);

                genericController.addCategory(category1, category11);
                genericController.setImageAll("universal_controller-2");

                productRepository.save(genericController);

                Product xbox = new Product("Xbox gaming console + controller",
                        "Experience the ultimate gaming experience with the Xbox Console! This sleek white Xbox console comes with the must-have Xbox wireless controller, so you can dive into the action right away. Enjoy stunning 4K visuals, immersive sound, and endless entertainment with the Xbox console. With access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. Plus, you can stream movies, shows, and music, and connect with friends online. Get ready for the ultimate gaming experience with the Xbox Console",
                        6800, 10);

                xbox.addCategory(category1, category5, category6);
                xbox.setImageAll("xbox_with_controller-1");

                Product xboxSeriesX = new Product("Xbox Series X",
                        "Experience the most powerful gaming console ever made with the Xbox Series X. With stunning 4K visuals, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the Xbox Series X looks great in any gaming setup. Get the Xbox Series X today and take your gaming to the next level!",
                        9000, 10);

                xboxSeriesX.addCategory(category1, category5, category6);
                xboxSeriesX.setImageAll("xbox");

                Product xboxSeriesS = new Product("Xbox Series S",
                        "Experience next-generation gaming with the Xbox Series S. With lightning-fast load times, high-quality visuals, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its compact design and customizable lighting, the Xbox Series S is perfect for any gaming setup. Get the Xbox Series S today and take your gaming to the next level!",
                        5000, 10);

                xboxSeriesS.addCategory(category1, category5, category6);
                xboxSeriesS.setImageAll("xbox_with_controller-2");

                productRepository.save(xbox);
                productRepository.save(xboxSeriesX);
                productRepository.save(xboxSeriesS);

                Product xboxWirelessController = new Product("Xbox Wireless Controller",
                        "Experience the ultimate in wireless gaming with the Xbox Wireless Controller. With its comfortable design, precise control, and wireless connectivity, you'll have everything you need to dominate the competition. Plus, with its customizable lighting and easy-to-reach buttons, you can personalize your controller to match your gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Xbox Wireless Controller today and take your gaming to the next level!",
                        700, 10);

                xboxWirelessController.addCategory(category1, category6, category11);
                xboxWirelessController.setImageAll("xbox_controller-1");

                productRepository.save(xboxWirelessController);

                Product gamingKeyboard = new Product("Gaming Keyboard",
                        "Take your gaming to the next level with the Gaming Keyboard. With its customizable lighting, programmable keys, and durable construction, you'll have everything you need to dominate the competition. Its ergonomic design ensures maximum comfort, even during long gaming sessions. And with its anti-ghosting technology, you can press multiple keys at once without any interference. Get the Gaming Keyboard today and take your gaming to the next level!",
                        1200, 10);

                gamingKeyboard.addCategory(category1, category9);
                gamingKeyboard.setImageAll("keyboard-1");

                productRepository.save(gamingKeyboard);

                Product nintendoSwitch = new Product("Nintendo Switch",
                        "Experience the ultimate in portable gaming with the Nintendo Switch. With its versatile design, you can play your favorite games on the go or at home. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its detachable Joy-Con controllers, you can play with a friend or on your own. Get the Nintendo Switch today and take your gaming to the next level!",
                        5000, 10);

                nintendoSwitch.addCategory(category1, category5, category8);
                nintendoSwitch.setImageAll("nintendo-1");

                productRepository.save(nintendoSwitch);

                Product playstationX = new Product("PlayStation X",
                        "Experience the ultimate gaming experience with the PlayStation 5. With stunning graphics, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the PlayStation 5 looksgreat in any gaming setup. Get the PlayStation 5 today and take your gaming to the next level!",
                        9000, 10);

                playstationX.addCategory(category1, category5, category7);
                playstationX.setImageAll("Playstation-1");

                productRepository.save(playstationX);

                Product cups = new Product("Cups",
                        "Enjoy your favorite beverage in style with the Cups. With its sleek design and durable construction, you can use these cups for years to come. Plus, with its large capacity, you can enjoy your favorite beverage without having to refill it. Get the Cups today and enjoy your favorite beverage in style!",
                        100, 10);

                cups.addCategory(category2, category10);
                cups.setImageAll("cups");

                Product batteriesAA = new Product("4x AA batteries",
                        "This 4x AA Battery Pack is the perfect solution for powering your electronic devices! With four high-capacity AA batteries, you can stay powered up for longer and get the most out of your gadgets. Our AA batteries are long-lasting and reliable, so you can trust that your device will stay powered for as long as you need. Plus, the lightweight and compact design of this battery pack makes it easy to carry and transport. Enjoy up to 10 hours of power with this 4x AA Battery Pack!",
                        80, 10);

                batteriesAA.addCategory(category10, category12);
                batteriesAA.setImageAll("batteriesAA");

                Product chargingBank1 = new Product("Charging Bank XS",
                        "Never run out of battery on your gaming devices with Charging Bank XS. With its high capacity battery, you can charge multiple devices at once. Plus, with its compact size, you can take it with you on the go. Get Charging Bank XS today and stay powered up!",
                        1000, 20);

                chargingBank1.addCategory(category2, category10, category12);
                chargingBank1.setImageAll("chargingBank-1");


                Product chargingBank2 = new Product("Charging Bank S",
                        "Keep your gaming devices charged and ready to go with Charging Bank S. With its sleek design and multiple charging ports, you can charge multiple devices at once. Plus, with its fast charging technology, you can get back to gaming in no time. Get Charging Bank S today and stay powered up!",
                        1500, 15);

                chargingBank2.addCategory(category2, category10, category12);
                chargingBank2.setImageAll("chargingBank-2");


                Product chargingBank3 = new Product("ChargingBank X",
                        "Take your charging game to the next level with Charging Bank X. With its advanced features, including wireless charging capabilities and a built-in LED display, you can charge your devices with ease and style. Plus, with its high capacity battery, you can charge multiple devices multiple times. Get Charging Bank X today and stay powered up like a pro!",
                        2500, 10);

                chargingBank3.addCategory(category2, category10, category12);
                chargingBank3.setImageAll("chargingBank-3");

                productRepository.save(batteriesAA);
                productRepository.save(chargingBank1);
                productRepository.save(chargingBank2);
                productRepository.save(chargingBank3);

                Role role = new Role("ROLE_USER");
                Role role2 = new Role("ROLE_ADMIN");
                roleRepository.save(role);
                roleRepository.save(role2);

                accessUserService.tryCreateNewUser("Test", "Test1234");

        }
}
