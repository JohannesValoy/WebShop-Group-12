package no.ntnu.webshop.group12.webshop.tools;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.models.product.Product;

@Component
@Order(1)
@Profile("!prod")
public class MinInitilizer implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MinInitilizer.class);

    @Autowired
    private CreationHelperTool creationHelperTool;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Initializing database with minimum data");
        String[] categories = creationHelperTool.getCategories();
        for (int i = 1; i <= 4; i++) {
                creationHelperTool.detailCategoryAndSave(new Category(categories[i-1]));
        }

        Map<String, Category> categoryMap = creationHelperTool.getCategoryMap();

        Product mouse = new Product("Gaming Mouse",
                "This gaming mouse is packed with features that make it the perfect tool for any gaming session. Its convenient wire location keeps your gaming area clutter-free, while its stylish red light ensures your gaming setup looks great. Comfort and accuracy are guaranteed with its ergonomic design and precision optical tracking. Plus, with its adjustable weight system, you can fine-tune the mouse to your liking!",
                700, 10);

        creationHelperTool.detailProductAndSave(mouse, categoryMap.get("Gaming"), categoryMap.get("Computer Mouse"));

        Product wirelessMouse = new Product("Wireless Gaming Mouse",
                "This wireless gaming mouse is perfect for gamers who want freedom of movement without sacrificing accuracy. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its long battery life, you won't have to worry about running out of juice in the middle of a game. Get the Wireless Gaming Mouse today and take your gaming to the next level!",
                800, 10);

        creationHelperTool.detailProductAndSave(wirelessMouse, categoryMap.get("Gaming"),
                categoryMap.get("Computer Mouse"));

        Product wiredMouse = new Product("Wired Gaming Mouse",
                "This wired gaming mouse is perfect for gamers who want a reliable, high-performance mouse without any lag or connectivity issues. With its ergonomic design and precision optical tracking, you'll be able to dominate the competition with ease. Plus, its stylish design and customizable lighting make it a great addition to any gaming setup. And with its durable construction and long cable, you won't have to worry about it breaking or running out of battery. Get the Wired Gaming Mouse today and take your gaming to the next level!",
                600, 10);

        creationHelperTool.detailProductAndSave(wiredMouse, categoryMap.get("Gaming"),
                categoryMap.get("Computer Mouse"));

        Product headsetOffice = new Product("Headset for office and gaming",
                "This headset is the perfect all-around choice for those who are looking for comfortable, practical sound quality. Its active noise cancellation feature and grey color make it an ideal choice for an office environment. The headset is designed to be comfortable and practical, with a lightweight and ergonomic design that fits snugly on your head. It also has adjustable ear cups and a padded headband for maximum comfort. The active noise cancellation feature ensures that you hear only what you want to hear, blocking out any unwanted distractions. With this headset, you can expect the highest quality of sound for all your audio needs.",
                100, 10);

        creationHelperTool.detailProductAndSave(headsetOffice, categoryMap.get("Gaming"), categoryMap.get("Office"),
                categoryMap.get("Headset"));

        Product wiredHeadset = new Product("Wired Gaming Headset",
                "If you prefer a wired connection for your gaming headset, the Wired Gaming Headset is the perfect choice. With its comfortable over-ear design, high-quality sound, and noise-cancelling microphone, you'll be able to hear and communicate clearly with your teammates. Its durable construction and long cable make it perfect for long gaming sessions, and its stylish design will make you the envy of all your friends. Get the Wired Gaming Headset today and take your gaming to the next level!",
                1000, 10);

        creationHelperTool.detailProductAndSave(wiredHeadset, categoryMap.get("Gaming"), categoryMap.get("Headset"));

        Product gamingHeadset = new Product("Hot Gaming Headset",
                "Are you looking for the ultimate gaming headset? Look no further than the Flexible Gaming Headset! With top-notch sound quality and stylish lights on the sides, you'll be ready for your next gaming session. Plus, its flexible design is sure to make it comfortable for long hours of gaming. But watch out - it's so hot, your keyboard may melt! Get the Flexible Gaming Headset today and take your gaming to the next level.",
                1200, 0);

        creationHelperTool.detailProductAndSave(gamingHeadset, categoryMap.get("Gaming"), categoryMap.get("Headset"));

    }

}
