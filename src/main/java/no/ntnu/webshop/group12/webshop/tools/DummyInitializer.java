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

                category1.setImage("category 1.jpg");
                category1.setImageJpg512("category 1-512w.jpg");
                category1.setImageJpg1024("category 1-1024w.jpg");
                category1.setImageJpg2048("category 1-2048w.jpg");
                category1.setImageWebp512("category 1-512w.webp");
                category1.setImageWebp1024("category 1-1024w.webp");
                category1.setImageWebp2048("category 1-2048w.webp");
                category2.setImage("category 2.jpg");
                category2.setImageJpg512("category 2-512w.jpg");
                category2.setImageJpg1024("category 2-1024w.jpg");
                category2.setImageJpg2048("category 2-2048w.jpg");
                category2.setImageWebp512("category 2-512w.webp");
                category2.setImageWebp1024("category 2-1024w.webp");
                category2.setImageWebp2048("category 2-2048w.webp");
                category3.setImage("category 3.jpg");
                category3.setImageJpg512("category 3-512w.jpg");
                category3.setImageJpg1024("category 3-1024w.jpg");
                category3.setImageJpg2048("category 3-2048w.jpg");
                category3.setImageWebp512("category 3-512w.webp");
                category3.setImageWebp1024("category 3-1024w.webp");
                category3.setImageWebp2048("category 3-2048w.webp");
                category4.setImage("category 4.jpg");
                category4.setImageJpg512("category 4-512w.jpg");
                category4.setImageJpg1024("category 4-1024w.jpg");
                category4.setImageJpg2048("category 4-2048w.jpg");
                category4.setImageWebp512("category 4-512w.webp");
                category4.setImageWebp1024("category 4-1024w.webp");
                category4.setImageWebp2048("category 4-2048w.webp");
                category5.setImage("category 5.jpg");
                category5.setImageJpg512("category 5-512w.jpg");
                category5.setImageJpg1024("category 5-1024w.jpg");
                category5.setImageJpg2048("category 5-2048w.jpg");
                category5.setImageWebp512("category 5-512w.webp");
                category5.setImageWebp1024("category 5-1024w.webp");
                category5.setImageWebp2048("category 5-2048w.webp");
                category6.setImage("category 6.jpg");
                category6.setImageJpg512("category 6-512w.jpg");
                category6.setImageJpg1024("category 6-1024w.jpg");
                category6.setImageJpg2048("category 6-2048w.jpg");
                category6.setImageWebp512("category 6-512w.webp");
                category6.setImageWebp1024("category 6-1024w.webp");
                category6.setImageWebp2048("category 6-2048w.webp");
                category7.setImage("category 7.jpg");
                category7.setImageJpg512("category 7-512w.jpg");
                category7.setImageJpg1024("category 7-1024w.jpg");
                category7.setImageJpg2048("category 7-2048w.jpg");
                category7.setImageWebp512("category 7-512w.webp");
                category7.setImageWebp1024("category 7-1024w.webp");
                category7.setImageWebp2048("category 7-2048w.webp");
                category8.setImage("category 8.jpg");
                category8.setImageJpg512("category 8-512w.jpg");
                category8.setImageJpg1024("category 8-1024w.jpg");
                category8.setImageJpg2048("category 8-2048w.jpg");
                category8.setImageWebp512("category 8-512w.webp");
                category8.setImageWebp1024("category 8-1024w.webp");
                category8.setImageWebp2048("category 8-2048w.webp");
                category9.setImage("category 9.jpg");
                category9.setImageJpg512("category 9-512w.jpg");
                category9.setImageJpg1024("category 9-1024w.jpg");
                category9.setImageJpg2048("category 9-2048w.jpg");
                category9.setImageWebp512("category 9-512w.webp");
                category9.setImageWebp1024("category 9-1024w.webp");
                category9.setImageWebp2048("category 9-2048w.webp");
                category10.setImage("category 10.jpg");
                category10.setImageJpg512("category 10-512w.jpg");
                category10.setImageJpg1024("category 10-1024w.jpg");
                category10.setImageJpg2048("category 10-2048w.jpg");
                category10.setImageWebp512("category 10-512w.webp");
                category10.setImageWebp1024("category 10-1024w.webp");
                category10.setImageWebp2048("category 10-2048w.webp");
                category11.setImage("category 11.jpg");
                category11.setImageJpg512("category 11-512w.jpg");
                category11.setImageJpg1024("category 11-1024w.jpg");
                category11.setImageJpg2048("category 11-2048w.jpg");
                category11.setImageWebp512("category 11-512w.webp");
                category11.setImageWebp1024("category 11-1024w.webp");
                category11.setImageWebp2048("category 11-2048w.webp");
                category12.setImage("category 12.jpg");
                category12.setImageJpg512("category 12-512w.jpg");
                category12.setImageJpg1024("category 12-1024w.jpg");
                category12.setImageJpg2048("category 12-2048w.jpg");
                category12.setImageWebp512("category 12-512w.webp");
                category12.setImageWebp1024("category 12-1024w.webp");
                category12.setImageWebp2048("category 12-2048w.webp");

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

                mouse.addCategory(category1);
                mouse.addCategory(category4);
                mouse.setImage("computer_mouse-3.jpg");
                mouse.setImageWebp2048("computer_mouse-3-2048w.webp");
                mouse.setImageWebp512("computer_mouse-3-512w.webp");
                mouse.setImageWebp1024("computer_mouse-3-1024w.webp");
                mouse.setImageJpg2048("computer_mouse-3-2048w.jpg");
                mouse.setImageJpg512("computer_mouse-3-512w.jpg");
                mouse.setImageJpg1024("computer_mouse-3-1024w.jpg");

                Product wirelessMouse = new Product("Wireless Gaming Mouse",
                        "This wireless gaming mouse is perfect for gamers who want freedom of movement without sacrificing accuracy. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Wireless Gaming Mouse today and take your gaming to the next level!",
                        800, 10);

                wirelessMouse.addCategory(category1);
                wirelessMouse.addCategory(category4);
                wirelessMouse.setImage("computer_mouse_wireless.jpg");
                wirelessMouse.setImageWebp2048("computer_mouse_wireless-2048w.webp");
                wirelessMouse.setImageWebp512("computer_mouse_wireless-512w.webp");
                wirelessMouse.setImageWebp1024("computer_mouse_wireless-1024w.webp");
                wirelessMouse.setImageJpg2048("computer_mouse_wireless-2048w.jpg");
                wirelessMouse.setImageJpg512("computer_mouse_wireless-512w.jpg");
                wirelessMouse.setImageJpg1024("computer_mouse_wireless-1024w.jpg");


                Product wiredMouse = new Product("Wired Gaming Mouse",
                        "This wired gaming mouse is perfect for gamers who want a reliable, high-performance mouse without any lag or connectivity issues. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its durable construction and long cable, you won't have to worry about it breaking or running out of battery. Get the Wired Gaming Mouse today and take your gaming to the next level!",
                        600, 10);

                wiredMouse.addCategory(category1);
                wiredMouse.addCategory(category4);
                wiredMouse.setImage("computer_mouse-1.jpg");
                wiredMouse.setImageWebp2048("computer_mouse-1-2048w.webp");
                wiredMouse.setImageWebp512("computer_mouse-1-512w.webp");
                wiredMouse.setImageWebp1024("computer_mouse-1-1024w.webp");
                wiredMouse.setImageJpg2048("computer_mouse-1-2048w.jpg");
                wiredMouse.setImageJpg512("computer_mouse-1-512w.jpg");
                wiredMouse.setImageJpg1024("computer_mouse-1-1024w.jpg");

                productRepository.save(mouse);
                productRepository.save(wirelessMouse);
                productRepository.save(wiredMouse);

                Product headsetOffice = new Product("Headset for office and gaming",
                        "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                        100, 10);

                headsetOffice.addCategory(category1);
                headsetOffice.addCategory(category2);
                headsetOffice.addCategory(category3);
                headsetOffice.setImage("headset-1.jpg");
                headsetOffice.setImageWebp2048("headset-1-2048w.webp");
                headsetOffice.setImageWebp512("headset-1-512w.webp");
                headsetOffice.setImageWebp1024("headset-1-1024w.webp");
                headsetOffice.setImageJpg2048("headset-1-2048w.jpg");
                headsetOffice.setImageJpg512("headset-1-512w.jpg");
                headsetOffice.setImageJpg1024("headset-1-1024w.jpg");

                Product wiredHeadset = new Product("Wired Gaming Headset",
                        "If you prefer a wired connection for your gaming headset, the Wired Gaming Headset is the perfect choice. With its comfortable over-ear design, high-quality sound, and noise-cancelling microphone, you'll be able to hear and communicate clearly with your teammates. Its durable construction and long cable make it perfect for long gaming sessions, and its stylish design will make you the envy of all your friends. Get the Wired Gaming Headset today and take your gaming to the next level!",
                        1000, 10);

                wiredHeadset.addCategory(category1);
                wiredHeadset.addCategory(category3);
                wiredHeadset.setImage("headset-2.jpg");
                wiredHeadset.setImageWebp2048("headset-2-2048w.webp");
                wiredHeadset.setImageWebp512("headset-2-512w.webp");
                wiredHeadset.setImageWebp1024("headset-2-1024w.webp");
                wiredHeadset.setImageJpg2048("headset-2-2048w.jpg");
                wiredHeadset.setImageJpg512("headset-2-512w.jpg");
                wiredHeadset.setImageJpg1024("headset-2-1024w.jpg");

                Product gamingHeadset = new Product("Hot Gaming Headset",
                        "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                        1200, 0);

                gamingHeadset.addCategory(category1);
                gamingHeadset.addCategory(category3);
                gamingHeadset.setImage("headset_with_mic-1.jpg");
                gamingHeadset.setImageWebp2048("headset_with_mic-1-2048w.webp");
                gamingHeadset.setImageWebp512("headset_with_mic-1-512w.webp");
                gamingHeadset.setImageWebp1024("headset_with_mic-1-1024w.webp");
                gamingHeadset.setImageJpg2048("headset_with_mic-1-2048w.jpg");
                gamingHeadset.setImageJpg512("headset_with_mic-1-512w.jpg");
                gamingHeadset.setImageJpg1024("headset_with_mic-1-1024w.jpg");

                productRepository.save(headsetOffice);
                productRepository.save(wiredHeadset);
                productRepository.save(gamingHeadset);

                Product genericController = new Product("Generic Gaming Controller",
                        "The Generic Gaming Controller is the perfect accessory for your gaming needs! It features a sleek, modern design with easy to reach buttons and a comfortable grip. The controller is compatible with most gaming consoles and is a great choice for both casual and competitive gamers. The wired connection ensures a lag-free gaming experience and the adjustable sensitivity of the analog sticks allows you to customize your gaming experience. Get the Generic Gaming Controller today and take your gaming to the next level!",
                        300, 10);

                genericController.addCategory(category1);
                genericController.addCategory(category11);
                genericController.setImage("universal_controller-2.jpg");
                genericController.setImageWebp2048("universal_controller-2-2048w.webp");
                genericController.setImageWebp512("universal_controller-2-512w.webp");
                genericController.setImageWebp1024("universal_controller-2-1024w.webp");
                genericController.setImageJpg2048("universal_controller-2-2048w.jpg");
                genericController.setImageJpg512("universal_controller-2-512w.jpg");
                genericController.setImageJpg1024("universal_controller-2-1024w.jpg");

                productRepository.save(genericController);

                Product xbox = new Product("Xbox gaming console + controller",
                        "Experience the ultimate gaming experience with the Xbox Console! This sleek white Xbox console comes with the must-have Xbox wireless controller, so you can dive into the action right away. Enjoy stunning 4K visuals, immersive sound, and endless entertainment with the Xbox console. With access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. Plus, you can stream movies, shows, and music, and connect with friends online. Get ready for the ultimate gaming experience with the Xbox Console",
                        6800, 10);

                xbox.addCategory(category1);
                xbox.addCategory(category5);
                xbox.addCategory(category6);
                xbox.setImage("xbox_with_controller-1.jpg");
                xbox.setImageWebp512("xbox_with_controller-1-512w.webp");
                xbox.setImageWebp1024("xbox_with_controller-1-1024w.webp");
                xbox.setImageWebp2048("xbox_with_controller-1-2048w.webp");
                xbox.setImageJpg512("xbox_with_controller-1-512w.jpg");
                xbox.setImageJpg1024("xbox_with_controller-1-1024w.jpg");
                xbox.setImageJpg2048("xbox_with_controller-1-2048w.jpg");

                Product xboxSeriesX = new Product("Xbox Series X",
                        "Experience the most powerful gaming console ever made with the Xbox Series X. With stunning 4K visuals, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the Xbox Series X looks great in any gaming setup. Get the Xbox Series X today and take your gaming to the next level!",
                        9000, 10);

                xboxSeriesX.addCategory(category1);
                xboxSeriesX.addCategory(category5);
                xboxSeriesX.addCategory(category6);
                xboxSeriesX.setImage("xbox.jpg");
                xboxSeriesX.setImageWebp512("xbox-512w.webp");
                xboxSeriesX.setImageWebp1024("xbox-1024w.webp");
                xboxSeriesX.setImageWebp2048("xbox-2048w.webp");
                xboxSeriesX.setImageJpg512("xbox-512w.jpg");
                xboxSeriesX.setImageJpg1024("xbox-1024w.jpg");
                xboxSeriesX.setImageJpg2048("xbox-2048w.jpg");

                Product xboxSeriesS = new Product("Xbox Series S",
                        "Experience next-generation gaming with the Xbox Series S. With lightning-fast load times, high-quality visuals, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its compact design and customizable lighting, the Xbox Series S is perfect for any gaming setup. Get the Xbox Series S today and take your gaming to the next level!",
                        5000, 10);

                xboxSeriesS.addCategory(category1);
                xboxSeriesS.addCategory(category5);
                xboxSeriesS.addCategory(category6);
                xboxSeriesS.setImage("xbox_with_controller-2.jpg");
                xboxSeriesS.setImageWebp512("xbox_with_controller-2-512w.webp");
                xboxSeriesS.setImageWebp1024("xbox_with_controller-2-1024w.webp");
                xboxSeriesS.setImageWebp2048("xbox_with_controller-2-2048w.webp");
                xboxSeriesS.setImageJpg512("xbox_with_controller-2-512w.jpg");
                xboxSeriesS.setImageJpg1024("xbox_with_controller-2-1024w.jpg");
                xboxSeriesS.setImageJpg2048("xbox_with_controller-2-2048w.jpg");

                productRepository.save(xbox);
                productRepository.save(xboxSeriesX);
                productRepository.save(xboxSeriesS);

                Product xboxWirelessController = new Product("Xbox Wireless Controller",
                        "Experience the ultimate in wireless gaming with the Xbox Wireless Controller. With its comfortable design, precise control, and wireless connectivity, you'll have everything you need to dominate the competition. Plus, with its customizable lighting and easy-to-reach buttons, you can personalize your controller to match your gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Xbox Wireless Controller today and take your gaming to the next level!",
                        700, 10);

                xboxWirelessController.addCategory(category1);
                xboxWirelessController.addCategory(category6);
                xboxWirelessController.addCategory(category11);
                xboxWirelessController.setImage("xbox_controller-1.jpg");
                xboxWirelessController.setImageWebp2048("xbox_controller-1-2048w.webp");
                xboxWirelessController.setImageWebp512("xbox_controller-1-512w.webp");
                xboxWirelessController.setImageWebp1024("xbox_controller-1-1024w.webp");
                xboxWirelessController.setImageJpg2048("xbox_controller-1-2048w.jpg");
                xboxWirelessController.setImageJpg512("xbox_controller-1-512w.jpg");
                xboxWirelessController.setImageJpg1024("xbox_controller-1-1024w.jpg");

                productRepository.save(xboxWirelessController);

                Product gamingKeyboard = new Product("Gaming Keyboard",
                        "Take your gaming to the next level with the Gaming Keyboard. With its customizable lighting, programmable keys, and durable construction, you'll have everything you need to dominate the competition. Its ergonomic design ensures maximum comfort, even during long gaming sessions. And with its anti-ghosting technology, you can press multiple keys at once without any interference. Get the Gaming Keyboard today and take your gaming to the next level!",
                        1200, 10);

                gamingKeyboard.addCategory(category1);
                gamingKeyboard.addCategory(category9);
                gamingKeyboard.setImage("keyboard-1.jpg");
                gamingKeyboard.setImageWebp2048("keyboard-1-2048w.webp");
                gamingKeyboard.setImageWebp512("keyboard-1-512w.webp");
                gamingKeyboard.setImageWebp1024("keyboard-1-1024w.webp");
                gamingKeyboard.setImageJpg2048("keyboard-1-2048w.jpg");
                gamingKeyboard.setImageJpg512("keyboard-1-512w.jpg");
                gamingKeyboard.setImageJpg1024("keyboard-1-1024w.jpg");

                productRepository.save(gamingKeyboard);

                Product nintendoSwitch = new Product("Nintendo Switch",
                        "Experience the ultimate in portable gaming with the Nintendo Switch. With its versatile design, you can play your favorite games on the go or at home. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its detachable Joy-Con controllers, you can play with a friend or on your own. Get the Nintendo Switch today and take your gaming to the next level!",
                        5000, 10);

                nintendoSwitch.addCategory(category1);
                nintendoSwitch.addCategory(category5);
                nintendoSwitch.addCategory(category8);
                nintendoSwitch.setImage("nintendo-1.jpg");
                nintendoSwitch.setImageWebp2048("nintendo-1-2048w.webp");
                nintendoSwitch.setImageWebp512("nintendo-1-512w.webp");
                nintendoSwitch.setImageWebp1024("nintendo-1-1024w.webp");
                nintendoSwitch.setImageJpg2048("nintendo-1-2048w.jpg");
                nintendoSwitch.setImageJpg512("nintendo-1-512w.jpg");
                nintendoSwitch.setImageJpg1024("nintendo-1-1024w.jpg");

                productRepository.save(nintendoSwitch);

                Product playstationX = new Product("PlayStation X",
                        "Experience the ultimate gaming experience with the PlayStation 5. With stunning graphics, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the PlayStation 5 looksgreat in any gaming setup. Get the PlayStation 5 today and take your gaming to the next level!",
                        9000, 10);

                playstationX.addCategory(category1);
                playstationX.addCategory(category5);
                playstationX.addCategory(category7);
                playstationX.setImage("Playstation-1.jpg");
                playstationX.setImageWebp2048("Playstation-1-2048w.webp");
                playstationX.setImageWebp512("Playstation-1-512w.webp");
                playstationX.setImageWebp1024("Playstation-1-1024w.webp");
                playstationX.setImageJpg2048("Playstation-1-2048w.jpg");
                playstationX.setImageJpg512("Playstation-1-512w.jpg");
                playstationX.setImageJpg1024("Playstation-1-1024w.jpg");

                productRepository.save(playstationX);


                Product chargingBank1 = new Product("Charging Bank XS",
                        "Never run out of battery on your gaming devices with Charging Bank XS. With its high capacity battery, you can charge multiple devices at once. Plus, with its compact size, you can take it with you on the go. Get Charging Bank XS today and stay powered up!",
                        1000, 20);

                chargingBank1.addCategory(category2);
                chargingBank1.addCategory(category10);
                chargingBank1.addCategory(category12);
                chargingBank1.setImage("chargingBank-1.jpg");
                chargingBank1.setImageWebp2048("chargingBank-1-2048w.webp");
                chargingBank1.setImageWebp512("chargingBank-1-512w.webp");
                chargingBank1.setImageWebp1024("chargingBank-1-1024w.webp");
                chargingBank1.setImageJpg2048("chargingBank-1-2048w.jpg");
                chargingBank1.setImageJpg512("chargingBank-1-512w.jpg");
                chargingBank1.setImageJpg1024("chargingBank-1-1024w.jpg");


                Product chargingBank2 = new Product("Charging Bank S",
                        "Keep your gaming devices charged and ready to go with Charging Bank S. With its sleek design and multiple charging ports, you can charge multiple devices at once. Plus, with its fast charging technology, you can get back to gaming in no time. Get Charging Bank S today and stay powered up!",
                        1500, 15);

                chargingBank2.addCategory(category2);
                chargingBank2.addCategory(category10);
                chargingBank2.addCategory(category12);
                chargingBank2.setImage("chargingBank-2.jpg");
                chargingBank2.setImageWebp2048("chargingBank-2-2048w.webp");
                chargingBank2.setImageWebp512("chargingBank-2-512w.webp");
                chargingBank2.setImageWebp1024("chargingBank-2-1024w.webp");
                chargingBank2.setImageJpg2048("chargingBank-2-2048w.jpg");
                chargingBank2.setImageJpg512("chargingBank-2-512w.jpg");
                chargingBank2.setImageJpg1024("chargingBank-2-1024w.jpg");


                Product chargingBank3 = new Product("ChargingBank X",
                        "Take your charging game to the next level with Charging Bank X. With its advanced features, including wireless charging capabilities and a built-in LED display, you can charge your devices with ease and style. Plus, with its high capacity battery, you can charge multiple devices multiple times. Get Charging Bank X today and stay powered up like a pro!",
                        2500, 10);

                chargingBank3.addCategory(category2);
                chargingBank3.addCategory(category10);
                chargingBank3.addCategory(category12);
                chargingBank3.setImage("chargingBank-3.jpg");
                chargingBank3.setImageWebp2048("chargingBank-3-2048w.webp");
                chargingBank3.setImageWebp512("chargingBank-3-512w.webp");
                chargingBank3.setImageWebp1024("chargingBank-3-1024w.webp");
                chargingBank3.setImageJpg2048("chargingBank-3-2048w.jpg");
                chargingBank3.setImageJpg512("chargingBank-3-512w.jpg");
                chargingBank3.setImageJpg1024("chargingBank-3-1024w.jpg");


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
