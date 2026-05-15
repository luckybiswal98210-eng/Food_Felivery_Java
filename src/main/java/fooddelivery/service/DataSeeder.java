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
                createRestaurant("Dosa Plaza", "Crispy Dosas and South Indian Delicacies", 4.6, "25-35 min", "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
                createRestaurant("Chaat Corner", "Spicy Street Food & Snacks", 4.3, "15-25 min", "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=500"),
                createRestaurant("Sweet Tooth", "Indian Sweets, Cakes, and Desserts", 4.8, "20-30 min", "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=500"),
                createRestaurant("Punjabi Dhaba", "Rich & Creamy North Indian Curries", 4.5, "35-45 min", "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=500"),
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
            createMenuItem(2L, "Crispy Chicken Burger", "Crunchy fried chicken patty", 99.00, "https://images.unsplash.com/photo-1615887023516-9fc62cb3ae4b?w=500"),
            createMenuItem(2L, "Chicken Whopper", "Signature large chicken burger", 179.00, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500"),
            createMenuItem(2L, "Aloo Tikki Burger", "Classic Indian potato patty", 49.00, "https://images.unsplash.com/photo-1550547660-d9450f859349?w=500"),
            createMenuItem(2L, "Peri Peri Fries", "Spicy fries", 79.00, "https://images.unsplash.com/photo-1630384060421-cb20d0e0649d?w=500"),
            createMenuItem(2L, "French Fries", "Classic salted fries", 59.00, "https://images.unsplash.com/photo-1630384060421-cb20d0e0649d?w=500"),
            createMenuItem(2L, "Cold Coffee", "Chilled creamy coffee", 89.00, "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=500"),
            
            createMenuItem(3L, "Chicken Dum Biryani", "Aromatic rice with tender chicken", 180.00, "https://upload.wikimedia.org/wikipedia/commons/5/5a/%22Hyderabadi_Dum_Biryani%22.jpg"),
            createMenuItem(3L, "Mutton Biryani", "Rich flavorful mutton pieces", 250.00, "https://upload.wikimedia.org/wikipedia/commons/5/5a/%22Hyderabadi_Dum_Biryani%22.jpg"),
            createMenuItem(3L, "Veg Biryani", "Loaded with vegetables & paneer", 140.00, "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=500"),
            createMenuItem(3L, "Egg Biryani", "Spicy rice with boiled eggs", 130.00, "https://upload.wikimedia.org/wikipedia/commons/5/5a/%22Hyderabadi_Dum_Biryani%22.jpg"),
            createMenuItem(3L, "Chicken Tikka Kebab", "6 pcs of smokey chicken", 160.00, "https://images.unsplash.com/photo-1599487488170-d11ec9c172f0?w=500"),
            createMenuItem(3L, "Paneer Tikka", "Tandoori roasted paneer", 150.00, "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=500"),
            createMenuItem(3L, "Chicken 65", "Spicy deep-fried chicken", 130.00, "https://images.unsplash.com/photo-1606478961726-1b0722cc2c13?w=500"),
            
            createMenuItem(4L, "Masala Dosa", "Crispy crepe with potato filling", 70.00, "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
            createMenuItem(4L, "Plain Dosa", "Simple crispy crepe", 50.00, "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
            createMenuItem(4L, "Paneer Dosa", "Filled with spiced paneer", 110.00, "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
            createMenuItem(4L, "Idli Sambar (3 pcs)", "Steamed rice cakes", 60.00, "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
            createMenuItem(4L, "Medu Vada (2 pcs)", "Crispy lentil donuts", 50.00, "https://images.unsplash.com/photo-1604085572504-a392ddf0d86a?w=500"),
            createMenuItem(4L, "Uttapam", "Thick pancake with veggies", 80.00, "https://images.unsplash.com/photo-1589301760014-d929f39ce9b1?w=500"),
            
            createMenuItem(5L, "Pani Puri (10 pcs)", "Spicy mint water balls", 40.00, "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=500"),
            createMenuItem(5L, "Samosa (2 pcs)", "Crispy potato-filled pastry", 30.00, "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=500"),
            createMenuItem(5L, "Pav Bhaji", "Spicy mashed veggies with bread", 90.00, "https://images.unsplash.com/photo-1606491956689-2ea866880c84?w=500"),
            createMenuItem(5L, "Aloo Tikki Chaat", "Potato patties with yogurt & chutney", 60.00, "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=500"),
            createMenuItem(5L, "Vada Pav", "Mumbai style potato burger", 25.00, "https://images.unsplash.com/photo-1606491956689-2ea866880c84?w=500"),
            
            createMenuItem(6L, "Gulab Jamun (2 pcs)", "Sweet syrupy dough balls", 40.00, "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=500"),
            createMenuItem(6L, "Rasmalai (2 pcs)", "Cottage cheese in sweet milk", 60.00, "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=500"),
            createMenuItem(6L, "Chocolate Brownie", "Warm fudgy brownie", 80.00, "https://images.unsplash.com/photo-1606890737304-57a1ca8a5b62?w=500"),
            createMenuItem(6L, "Vanilla Ice Cream", "Classic creamy scoop", 50.00, "https://images.unsplash.com/photo-1570197781417-0c7a4e63e263?w=500"),
            createMenuItem(6L, "Black Forest Cake", "Rich chocolate & cherry cake", 90.00, "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=500"),
            
            createMenuItem(7L, "Butter Chicken", "Creamy tomato gravy", 180.00, "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=500"),
            createMenuItem(7L, "Paneer Butter Masala", "Rich paneer gravy", 150.00, "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=500"),
            createMenuItem(7L, "Dal Makhani", "Slow-cooked black lentils", 120.00, "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=500"),
            createMenuItem(7L, "Butter Naan", "Soft tandoori bread", 35.00, "https://images.unsplash.com/photo-1626200419188-f860d5b51a54?w=500"),
            createMenuItem(7L, "Jeera Rice", "Cumin flavored rice", 80.00, "https://images.unsplash.com/photo-1512058564366-18510be2db19?w=500"),
            
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
