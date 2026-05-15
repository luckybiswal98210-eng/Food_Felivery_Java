package fooddelivery.service;

import fooddelivery.entity.MenuItem;
import fooddelivery.entity.Restaurant;
import fooddelivery.repository.MenuItemRepository;
import fooddelivery.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public DataSeeder(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (restaurantRepository.count() == 0) {
            seedRestaurants();
            seedMenuItems();
        }
    }

    private void seedRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(
                createRestaurant("Pizza Paradise", "Delicious pizzas", 4.5, "30-45 min", "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=500"),
                createRestaurant("Burger King", "Flame-grilled burgers", 4.2, "20-30 min", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500"),
                createRestaurant("Biryani House", "Authentic Hyderabadi & Lucknowi Biryani", 4.7, "40-50 min", "https://upload.wikimedia.org/wikipedia/commons/5/5a/%22Hyderabadi_Dum_Biryani%22.jpg"),
                createRestaurant("South Indian", "Crispy Dosas and South Indian Delicacies", 4.6, "25-35 min", "/images/south_indian.jpg"),
                createRestaurant("Chatpata Corner", "Flavors from the Streets", 4.3, "15-25 min", "/images/chatpata_corner.jpg"),
                createRestaurant("Sweet Tooth", "Indian Sweets, Cakes, and Desserts", 4.8, "20-30 min", "/images/sweet_tooth.jpg"),
                createRestaurant("Punjabi Dhaba", "Rich & Creamy North Indian Curries", 4.5, "35-45 min", "/images/punjabi_dhaba.png"),
                createRestaurant("Pasta Bar", "Italian Pastas & Garlic Breads", 4.4, "30-40 min", "https://images.unsplash.com/photo-1473093295043-cdd812d0e601?w=500")
        );
        restaurantRepository.saveAll(restaurants);
    }

    private Restaurant createRestaurant(String name, String description, Double rating, String deliveryTime, String imageUrl) {
        Restaurant r = new Restaurant();
        r.setName(name);
        r.setDescription(description);
        r.setRating(rating);
        r.setDeliveryTime(deliveryTime);
        r.setImageUrl(imageUrl);
        return r;
    }

    private void seedMenuItems() {
        List<MenuItem> items = Arrays.asList(
            createMenuItem(1L, "Margherita Pizza", "Classic cheese pizza", 149.00, "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=500"),
            createMenuItem(1L, "Veg Supreme Pizza", "Loaded with fresh veggies", 199.00, "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=500"),
            createMenuItem(1L, "Paneer Tikka Pizza", "Spicy paneer tikka toppings", 229.00, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=500"),
            createMenuItem(1L, "Farmhouse Pizza", "Mushroom, capsicum, tomato", 249.00, "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=500"),
            createMenuItem(1L, "Cheese Garlic Bread", "Loaded with mozzarella", 109.00, "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=500"),
            
            createMenuItem(2L, "Veg Whopper", "Signature large veg burger", 149.00, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500"),
            createMenuItem(2L, "Crispy Chicken Burger", "Crunchy fried chicken patty", 99.00, "https://images.unsplash.com/photo-1572802419224-296b0aeee0d9?w=500"),
            createMenuItem(2L, "Chicken Whopper", "Signature large chicken burger", 179.00, "https://images.unsplash.com/photo-1553979459-d2229ba7433b?w=500"),
            createMenuItem(2L, "Aloo Tikki Burger", "Classic Indian potato patty", 49.00, "https://images.unsplash.com/photo-1550547660-d9450f859349?w=500"),
            createMenuItem(2L, "Peri Peri Fries", "Spicy fries", 79.00, "https://images.unsplash.com/photo-1630384060421-cb20d0e0649d?w=500"),
            createMenuItem(2L, "French Fries", "Classic salted fries", 59.00, "https://images.unsplash.com/photo-1541592106381-b31e9677c0e5?w=500"),
            createMenuItem(2L, "Cold Coffee", "Chilled creamy coffee", 89.00, "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=500"),
            
            createMenuItem(3L, "Gongura Chicken Biryani", "Spicy & tangy chicken biryani", 190.00, "/images/gongura_chicken.jpg"),
            createMenuItem(3L, "Gongura Veg Biryani", "Spicy & tangy veg biryani", 150.00, "/images/gongura_veg.png"),
            createMenuItem(3L, "Mushroom Biryani", "Aromatic rice with fresh mushrooms", 160.00, "/images/mushroom_biryani.png"),
            createMenuItem(3L, "Chicken Dum Biryani", "Aromatic rice with tender chicken", 180.00, "https://upload.wikimedia.org/wikipedia/commons/5/5a/%22Hyderabadi_Dum_Biryani%22.jpg"),
            createMenuItem(3L, "Mutton Biryani", "Rich flavorful mutton pieces", 250.00, "https://images.unsplash.com/photo-1633945274405-b6c8069047b0?w=500"),
            createMenuItem(3L, "Veg Biryani", "Loaded with vegetables & paneer", 140.00, "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=500"),
            createMenuItem(3L, "Egg Biryani", "Spicy rice with boiled eggs", 130.00, "https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=500"),
            createMenuItem(3L, "Paneer Biryani", "Rich and flavorful paneer biryani", 160.00, "/images/paneer_biryani.png"),
            
            createMenuItem(4L, "Masala Dosa", "Crispy crepe with potato filling", 70.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Rameshwaram_Cafe_Dosa.jpg/500px-Rameshwaram_Cafe_Dosa.jpg"),
            createMenuItem(4L, "Plain Dosa", "Simple crispy crepe", 50.00, "/images/plain_dosa.png"),
            createMenuItem(4L, "Paneer Dosa", "Dosa stuffed with spiced paneer", 90.00, "/images/paneer_dosa.png"),
            createMenuItem(4L, "Chinese Dosa", "Indo-Chinese style fusion dosa", 100.00, "/images/chinese_dosa.png"),
            createMenuItem(4L, "Pav Bhaji Dosa", "Dosa with spicy pav bhaji filling", 110.00, "/images/pav_bhaji_dosa.png"),
            createMenuItem(4L, "Idli Sambar", "Steamed rice cakes with lentil soup", 60.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Idli_Sambar.JPG/500px-Idli_Sambar.JPG"),
            createMenuItem(4L, "Medu Vada", "Deep fried lentil donuts", 50.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Medu_Vadas.JPG/500px-Medu_Vadas.JPG"),
            createMenuItem(4L, "Uttapam", "Thick rice pancake with onion toppings", 80.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Mini_Uttappam.jpg/500px-Mini_Uttappam.jpg"),
            createMenuItem(4L, "Lemon Rice", "Tangy lemon flavored rice", 75.00, "/images/lemon_rice.png"),
            createMenuItem(4L, "Upma", "Savory semolina porridge", 45.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/A_photo_of_Upma.jpg/500px-A_photo_of_Upma.jpg"),
            createMenuItem(4L, "Ven Pongal", "Savory lentil and rice dish", 65.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Ven_pongal_with_sambar_and_chutney.jpg/500px-Ven_pongal_with_sambar_and_chutney.jpg"),
            
            createMenuItem(5L, "Pani Puri", "Spicy mint water balls", 40.00, "/images/pani_puri.png"),
            createMenuItem(5L, "Samosa", "Crispy potato-filled pastry", 30.00, "/images/samosa.png"),
            createMenuItem(5L, "Pav Bhaji", "Spicy mashed veggies with bread", 90.00, "https://images.unsplash.com/photo-1606491956689-2ea866880c84?w=500"),
            createMenuItem(5L, "Aloo Tikki Chaat", "Potato patties with yogurt & chutney", 60.00, "/images/aloo_tikki_chaat.png"),
            createMenuItem(5L, "Vada Pav", "Mumbai style potato burger", 25.00, "/images/vada_pav.png"),
            createMenuItem(5L, "Hakka Noodles", "Wok-tossed spicy noodles", 80.00, "/images/hakka_noodles.png"),
            createMenuItem(5L, "Spring Roll", "Crispy vegetable rolls", 60.00, "/images/spring_roll.png"),
            createMenuItem(5L, "Chilli Potato", "Crispy potato in spicy sauce", 70.00, "https://images.unsplash.com/photo-1541592106381-b31e9677c0e5?w=500"),
            createMenuItem(5L, "Masala Maggi", "Spicy street-style Maggi", 50.00, "https://images.unsplash.com/photo-1612929633738-8fe44f7ec841?w=500"),
            createMenuItem(5L, "Corn Chaat", "Spicy sweet corn mix", 55.00, "/images/corn_chaat.png"),
            createMenuItem(5L, "Veg Momos", "Steamed vegetable dumplings", 70.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Momo_nepal.jpg/500px-Momo_nepal.jpg"),
            createMenuItem(5L, "Veg Cutlet", "Crispy vegetable patties", 40.00, "/images/veg_cutlet.png"),
            
            createMenuItem(6L, "Gulab Jamun", "Sweet syrupy dough balls", 40.00, "/images/gulab_jamun.png"),
            createMenuItem(6L, "Rasmalai", "Cottage cheese in sweet milk", 60.00, "/images/rasmalai.png"),
            createMenuItem(6L, "Chocolate Brownie", "Warm fudgy brownie", 80.00, "https://images.unsplash.com/photo-1606890737304-57a1ca8a5b62?w=500"),
            createMenuItem(6L, "Vanilla Ice Cream", "Classic creamy scoop", 50.00, "/images/vanilla_icecream.png"),
            createMenuItem(6L, "Black Forest Cake", "Rich chocolate & cherry cake", 90.00, "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=500"),
            createMenuItem(6L, "Mysore Pak", "Rich & traditional sweet", 70.00, "/images/mysore_pak.png"),
            
            createMenuItem(7L, "Butter Chicken", "Creamy tomato gravy", 180.00, "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=500"),
            createMenuItem(7L, "Paneer Butter Masala", "Rich paneer gravy", 150.00, "/images/paneer_butter_masala.png"),
            createMenuItem(7L, "Dal Makhani", "Slow-cooked black lentils", 120.00, "/images/dal_makhani.png"),
            createMenuItem(7L, "Butter Naan", "Soft tandoori bread", 35.00, "/images/butter_naan.png"),
            createMenuItem(7L, "Jeera Rice", "Cumin flavored rice", 80.00, "/images/jeera_rice.jpg"),
            createMenuItem(7L, "Lassi", "Chilled refreshing yogurt drink", 50.00, "/images/lassi.png"),
            
            createMenuItem(8L, "White Sauce Pasta", "Creamy Alfredo pasta", 139.00, "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=500"),
            createMenuItem(8L, "Red Sauce Pasta", "Tangy Arrabbiata pasta", 129.00, "https://images.unsplash.com/photo-1555949258-eb67b1ef0ceb?w=500"),
            createMenuItem(8L, "Pink Sauce Pasta", "Mix of red and white sauce", 149.00, "https://images.unsplash.com/photo-1473093295043-cdd812d0e601?w=500")
        );
        menuItemRepository.saveAll(items);
    }

    private MenuItem createMenuItem(Long restaurantId, String name, String description, Double price, String imageUrl) {
        MenuItem item = new MenuItem();
        item.setRestaurantId(restaurantId);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setImageUrl(imageUrl);
        return item;
    }
}
