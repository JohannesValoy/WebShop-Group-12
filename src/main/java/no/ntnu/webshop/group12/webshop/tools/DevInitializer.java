package no.ntnu.webshop.group12.webshop.tools;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;


@Configuration
@Order(2)
@Profile("!test")
public class DevInitializer implements ApplicationListener<ApplicationReadyEvent>  {

        @Autowired
        private CreationHelperTool creationHelperTool;
        private final Logger logger = LoggerFactory.getLogger("DummyInit");
        
        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
                logger.info("Initializing dummy data for development environment");
                String[] categories = creationHelperTool.getCategories();
                for (int i = 5; i <= categories.length-1; i++) {
                        creationHelperTool.detailCategoryAndSave(new Category(categories[i]));
                }

                Map<String, Category> categoryMap = creationHelperTool.getCategoryMap();
                Product genericController = new Product("Generic Gaming Controller",
                                "The Generic Gaming Controller is the perfect accessory for your gaming needs! It features a sleek, modern design with easy to reach buttons and a comfortable grip. The controller is compatible with most gaming consoles and is a great choice for both casual and competitive gamers. The wired connection ensures a lag-free gaming experience and the adjustable sensitivity of the analog sticks allows you to customize your gaming experience. Get the Generic Gaming Controller today and take your gaming to the next level!",
                                300);

                creationHelperTool.detailProductAndSave(genericController, categoryMap.get("Gaming"), categoryMap.get("Controllers"));

                Product xbox = new Product("Xbox gaming console + controller",
                                "Experience the ultimate gaming experience with the Xbox Console! This sleek white Xbox console comes with the must-have Xbox wireless controller, so you can dive into the action right away. Enjoy stunning 4K visuals, immersive sound, and endless entertainment with the Xbox console. With access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. Plus, you can stream movies, shows, and music, and connect with friends online. Get ready for the ultimate gaming experience with the Xbox Console",
                                6800);

                creationHelperTool.detailProductAndSave(xbox, categoryMap.get("Gaming"), categoryMap.get("Xbox"), categoryMap.get("Console"));

                Product xboxSeriesX = new Product("Xbox Series X",
                                "Experience the most powerful gaming console ever made with the Xbox Series X. With stunning 4K visuals, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the Xbox Series X looks great in any gaming setup. Get the Xbox Series X today and take your gaming to the next level!",
                                9000);

                creationHelperTool.detailProductAndSave(xboxSeriesX, categoryMap.get("Gaming"), categoryMap.get("Xbox"), categoryMap.get("Console"));
                
                Product xboxSeriesS = new Product("Xbox Series S",
                                "Experience next-generation gaming with the Xbox Series S. With lightning-fast load times, high-quality visuals, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its compact design and customizable lighting, the Xbox Series S is perfect for any gaming setup. Get the Xbox Series S today and take your gaming to the next level!",
                                5000);

                creationHelperTool.detailProductAndSave(xboxSeriesS, categoryMap.get("Gaming"), categoryMap.get("Xbox"), categoryMap.get("Console"));

                Product xboxWirelessController = new Product("Xbox Wireless Controller",
                                "Experience the ultimate in wireless gaming with the Xbox Wireless Controller. With its comfortable design, precise control, and wireless connectivity, you'll have everything you need to dominate the competition. Plus, with its customizable lighting and easy-to-reach buttons, you can personalize your controller to match your gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Xbox Wireless Controller today and take your gaming to the next level!",
                                700);

                creationHelperTool.detailProductAndSave(xboxWirelessController, categoryMap.get("Gaming"), categoryMap.get("Xbox"), categoryMap.get("Controllers"));

                Product gamingKeyboard = new Product("Gaming Keyboard",
                                "Take your gaming to the next level with the Gaming Keyboard. With its customizable lighting, programmable keys, and durable construction, you'll have everything you need to dominate the competition. Its ergonomic design ensures maximum comfort, even during long gaming sessions. And with its anti-ghosting technology, you can press multiple keys at once without any interference. Get the Gaming Keyboard today and take your gaming to the next level!",
                                1200);

                creationHelperTool.detailProductAndSave(gamingKeyboard, categoryMap.get("Gaming"), categoryMap.get("Keyboard"));

                Product nintendoSwitch = new Product("Nintendo Switch",
                                "Experience the ultimate in portable gaming with the Nintendo Switch. With its versatile design, you can play your favorite games on the go or at home. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its detachable Joy-Con controllers, you can play with a friend or on your own. Get the Nintendo Switch today and take your gaming to the next level!",
                                5000);

                creationHelperTool.detailProductAndSave(nintendoSwitch, categoryMap.get("Gaming"), categoryMap.get("Nintendo"), categoryMap.get("Console"));

                Product playstationX = new Product("PlayStation X",
                                "Experience the ultimate gaming experience with the PlayStation 5. With stunning graphics, lightning-fast load times, and immersive sound, you'll feel like you're part of the game. Plus, with access to thousands of games, including the latest blockbusters, classic favorites, and more, there's something for everyone. And with its sleek design and customizable lighting, the PlayStation 5 looksgreat in any gaming setup. Get the PlayStation 5 today and take your gaming to the next level!",
                                9000);

                creationHelperTool.detailProductAndSave(playstationX, categoryMap.get("Gaming"), categoryMap.get("Playstation"), categoryMap.get("Console"));

                Product cups = new Product("Cups",
                                "Enjoy your favorite beverage in style with the Cups. With its sleek design and durable construction, you can use these cups for years to come. Plus, with its large capacity, you can enjoy your favorite beverage without having to refill it. Get the Cups today and enjoy your favorite beverage in style!",
                                100);

                creationHelperTool.detailProductAndSave(cups, categoryMap.get("Office"), categoryMap.get("Accessories"));


                Product batteriesAA = new Product("4x AA batteries",
                                "This 4x AA Battery Pack is the perfect solution for powering your electronic devices! With four high-capacity AA batteries, you can stay powered up for longer and get the most out of your gadgets. Our AA batteries are long-lasting and reliable, so you can trust that your device will stay powered for as long as you need. Plus, the lightweight and compact design of this battery pack makes it easy to carry and transport. Enjoy up to 10 hours of power with this 4x AA Battery Pack!",
                                80);

                creationHelperTool.detailProductAndSave(batteriesAA, categoryMap.get("Batteries"), categoryMap.get("Accessories"));
                
                Product chargingBank1 = new Product("Charging Bank XS",
                                "Never run out of battery on your gaming devices with Charging Bank XS. With its high capacity battery, you can charge multiple devices at once. Plus, with its compact size, you can take it with you on the go. Get Charging Bank XS today and stay powered up!",
                                1000);

                creationHelperTool.detailProductAndSave(chargingBank1, categoryMap.get("Office"), categoryMap.get("Accessories"), categoryMap.get("Batteries"));

                Product chargingBank2 = new Product("Charging Bank S",
                                "Keep your gaming devices charged and ready to go with Charging Bank S. With its sleek design and multiple charging ports, you can charge multiple devices at once. Plus, with its fast charging technology, you can get back to gaming in no time. Get Charging Bank S today and stay powered up!",
                                1500);

                creationHelperTool.detailProductAndSave(chargingBank2, categoryMap.get("Office"), categoryMap.get("Accessories"), categoryMap.get("Batteries"));

                Product chargingBank3 = new Product("Charging Bank X",
                                "Take your charging game to the next level with Charging Bank X. With its advanced features, including wireless charging capabilities and a built-in LED display, you can charge your devices with ease and style. Plus, with its high capacity battery, you can charge multiple devices multiple times. Get Charging Bank X today and stay powered up like a pro!",
                                2500);
                
                creationHelperTool.detailProductAndSave(chargingBank3, categoryMap.get("Office"), categoryMap.get("Accessories"), categoryMap.get("Batteries"));
        }

}
