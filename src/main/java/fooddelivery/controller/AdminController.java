package fooddelivery.controller;

import fooddelivery.entity.User;
import fooddelivery.entity.CustomerOrder;
import fooddelivery.repository.UserRepository;
import fooddelivery.repository.CustomerOrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final CustomerOrderRepository orderRepository;

    public AdminController(UserRepository userRepository, CustomerOrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"ROLE_ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        List<User> users = userRepository.findAll();
        List<CustomerOrder> orders = orderRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("orders", orders);
        return "admin/dashboard";
    }

    @org.springframework.web.bind.annotation.PostMapping("/user/update")
    public String updateUser(@org.springframework.web.bind.annotation.ModelAttribute User user, HttpSession session, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || !"ROLE_ADMIN".equals(admin.getRole())) {
            return "redirect:/login";
        }

        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhone(user.getPhone());
            existingUser.setRole(user.getRole());
            userRepository.save(existingUser);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        }
        return "redirect:/admin/dashboard";
    }
}
