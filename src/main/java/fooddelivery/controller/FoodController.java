package fooddelivery.controller;

import fooddelivery.dto.CartItem;
import fooddelivery.dto.CheckoutForm;
import fooddelivery.entity.MenuItem;
import fooddelivery.entity.Restaurant;
import fooddelivery.repository.MenuItemRepository;
import fooddelivery.repository.RestaurantRepository;
import fooddelivery.service.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FoodController {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final FoodService foodService;

    public FoodController(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, FoodService foodService) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.foodService = foodService;
    }

    @ModelAttribute("cartCount")
    public int getCartCount(HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        return cart.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    private Map<Long, CartItem> getCart(HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/home")
    public String index(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if (search != null && !search.isEmpty()) {
            String q = search.toLowerCase();
            restaurants = restaurants.stream()
                    .filter(r -> r.getName().toLowerCase().contains(q) || r.getDescription().toLowerCase().contains(q))
                    .collect(Collectors.toList());
        }
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("searchTerm", search);
        return "index";
    }

    @GetMapping("/restaurant/{id}")
    public String restaurantMenu(@PathVariable Long id, @RequestParam(name = "search", required = false) String search, Model model) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant == null) return "redirect:/";

        List<MenuItem> items = menuItemRepository.findByRestaurantId(id);
        if (search != null && !search.isEmpty()) {
            String q = search.toLowerCase();
            items = items.stream()
                    .filter(i -> i.getName().toLowerCase().contains(q))
                    .collect(Collectors.toList());
        }

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("items", items);
        model.addAttribute("searchTerm", search);
        return "menu";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long itemId, @RequestParam Long restaurantId, HttpSession session, RedirectAttributes redirectAttributes) {
        MenuItem item = menuItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            Map<Long, CartItem> cart = getCart(session);
            if (cart.containsKey(itemId)) {
                cart.get(itemId).setQuantity(cart.get(itemId).getQuantity() + 1);
            } else {
                cart.put(itemId, new CartItem(item, 1));
            }
            redirectAttributes.addFlashAttribute("successMessage", "Added " + item.getName() + " to cart!");
        }
        return "redirect:/restaurant/" + restaurantId;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        double total = cart.values().stream().mapToDouble(c -> c.getItem().getPrice() * c.getQuantity()).sum();
        model.addAttribute("cartItems", cart.values());
        model.addAttribute("total", total);
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Long itemId, @RequestParam int delta, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        if (cart.containsKey(itemId)) {
            CartItem c = cart.get(itemId);
            c.setQuantity(c.getQuantity() + delta);
            if (c.getQuantity() <= 0) {
                cart.remove(itemId);
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute CheckoutForm form, HttpSession session, RedirectAttributes redirectAttributes) {
        Map<Long, CartItem> cart = getCart(session);
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }
        
        double total = cart.values().stream().mapToDouble(c -> c.getItem().getPrice() * c.getQuantity()).sum();
        
        try {
            String orderId = foodService.placeOrder(form, cart, total);
            session.removeAttribute("cart");
            redirectAttributes.addFlashAttribute("orderSuccess", orderId);
            return "redirect:/cart";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error placing order: " + e.getMessage());
            return "redirect:/cart";
        }
    }
}
