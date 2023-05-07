package no.ntnu.webshop.group12.webshop.tools;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.WebpImage;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;
import no.ntnu.webshop.group12.webshop.repository.CategoryRepository;
import no.ntnu.webshop.group12.webshop.repository.ProductRepository;
import no.ntnu.webshop.group12.webshop.repository.WebpImageRepository;

@Component
@Profile("test")
public class TestInitializer implements ApplicationListener<ApplicationReadyEvent> {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;


        @Autowired
        private WebpImageRepository webpImageRepository;

        private final Logger logger = LoggerFactory.getLogger("DummyInit");

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
                logger.info("Initializing dummy data");

                final WebpImage.Builder webpBuilder = new WebpImage.Builder();

                ArrayList<WebpImage> categoryWebpImages = new ArrayList<>();
                categoryWebpImages.add(webpBuilder.imageAll("category 1").build());
                categoryWebpImages.add(webpBuilder.imageAll("category 2").build());
                categoryWebpImages.add(webpBuilder.imageAll("category 3").build());
                categoryWebpImages.add(webpBuilder.imageAll("category 4").build());
                categoryWebpImages.add(webpBuilder.imageAll("category 5").build());
                webpImageRepository.saveAll(categoryWebpImages);

                Category category1 = new Category("Gaming", categoryWebpImages.get(0));
                Category category2 = new Category("Office", categoryWebpImages.get(1));
                Category category3 = new Category("Headset", categoryWebpImages.get(2));
                Category category4 = new Category("Computer Mouse", categoryWebpImages.get(3));
                Category category5 = new Category("Console", categoryWebpImages.get(4));

                categoryRepository.save(category1);
                categoryRepository.save(category2);
                categoryRepository.save(category3);
                categoryRepository.save(category4);
                categoryRepository.save(category5);

                HashMap<String, WebpImage> productWebpImages = new HashMap<>();
                productWebpImages.put("Gaming Mouse", webpBuilder.imageAll("computer_mouse").build());
                productWebpImages.put("Wireless Gaming Mouse", webpBuilder.imageAll("computer_mouse_wireless").build());
                productWebpImages.put("Wired Gaming Mouse", webpBuilder.imageAll("computer_mouse-1").build());
                productWebpImages.put("Headset for office and gaming", webpBuilder.imageAll("headset-1").build());
                productWebpImages.put("Wired Gaming Headset", webpBuilder.imageAll("headset-2").build());
                productWebpImages.put("Hot Gaming Headset", webpBuilder.imageAll("headset_with_mic-1").build());

                webpImageRepository.saveAll(productWebpImages.values());

                Product mouse = new Product("Gaming Mouse",
                                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking!",
                                700, 10);

                mouse.addCategory(category1, category4);
                mouse.setImage(productWebpImages.get(mouse.getName()));

                Product wirelessMouse = new Product("Wireless Gaming Mouse",
                                "This wireless gaming mouse is perfect for gamers who want freedom of movement without sacrificing accuracy. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Wireless Gaming Mouse today and take your gaming to the next level!",
                                800, 10);

                wirelessMouse.addCategory(category1, category4);
                wirelessMouse.setImage(productWebpImages.get(wirelessMouse.getName()));

                Product wiredMouse = new Product("Wired Gaming Mouse",
                                "This wired gaming mouse is perfect for gamers who want a reliable, high-performance mouse without any lag or connectivity issues. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its durable construction and long cable, you won't have to worry about it breaking or running out of battery. Get the Wired Gaming Mouse today and take your gaming to the next level!",
                                600, 10);

                wiredMouse.addCategory(category1, category4);
                wiredMouse.setImage(productWebpImages.get(wiredMouse.getName()));

                productRepository.save(mouse);
                productRepository.save(wirelessMouse);
                productRepository.save(wiredMouse);

                Product headsetOffice = new Product("Headset for office and gaming",
                                "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                                100, 10);

                headsetOffice.addCategory(category1, category2, category3);
                headsetOffice.setImage(productWebpImages.get(headsetOffice.getName()));

                Product wiredHeadset = new Product("Wired Gaming Headset",
                                "If you prefer a wired connection for your gaming headset, the Wired Gaming Headset is the perfect choice. With its comfortable over-ear design, high-quality sound, and noise-cancelling microphone, you'll be able to hear and communicate clearly with your teammates. Its durable construction and long cable make it perfect for long gaming sessions, and its stylish design will make you the envy of all your friends. Get the Wired Gaming Headset today and take your gaming to the next level!",
                                1000, 10);

                wiredHeadset.addCategory(category1, category3);
                wiredHeadset.setImage(productWebpImages.get(wiredHeadset.getName()));

                Product gamingHeadset = new Product("Hot Gaming Headset",
                                "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                                1200, 0);

                gamingHeadset.addCategory(category1, category3);
                gamingHeadset.setImage(productWebpImages.get(gamingHeadset.getName()));

                productRepository.save(headsetOffice);
                productRepository.save(wiredHeadset);
                productRepository.save(gamingHeadset);
        }
}
