package fooddelivery.controller;

import fooddelivery.entity.User;
import fooddelivery.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    
    private final UserRepository userRepository;
    private final fooddelivery.repository.CustomerOrderRepository orderRepository;

    public UserController(UserRepository userRepository, fooddelivery.repository.CustomerOrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/home";
        }
        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("error", "Email already registered");
            return "redirect:/signup";
        }
        user.setRole("ROLE_USER");
        userRepository.save(user);
        session.setAttribute("loggedInUser", user);
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        
        List<fooddelivery.entity.CustomerOrder> orders = orderRepository.findByCustomerEmail(user.getEmail());
        model.addAttribute("orders", orders);
        
        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "redirect:/";
    }
}
