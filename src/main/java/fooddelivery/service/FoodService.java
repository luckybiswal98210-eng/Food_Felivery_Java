package fooddelivery.service;

import fooddelivery.entity.CustomerOrder;
import fooddelivery.entity.OrderItem;
import fooddelivery.dto.CartItem;
import fooddelivery.dto.CheckoutForm;
import fooddelivery.repository.CustomerOrderRepository;
import fooddelivery.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FoodService {

    private final CustomerOrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public FoodService(CustomerOrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public String placeOrder(CheckoutForm form, Map<Long, CartItem> cart, double totalAmount) {
        CustomerOrder order = new CustomerOrder();
        order.setOrderUuid(UUID.randomUUID().toString());
        order.setCustomerName(form.getName());
        order.setCustomerEmail(form.getEmail());
        order.setCustomerPhone(form.getPhone());
        order.setDeliveryAddress(form.getAddress());
        order.setTotalAmount(totalAmount);
        order.setStatus("pending");
        order.setOrderDate(new Date());
        order.setPaymentMethod(form.getPaymentMethod());
        order.setLocation(form.getLocation());

        order = orderRepository.save(order);

        for (CartItem cartItem : cart.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setMenuItemId(cartItem.getItem().getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getItem().getPrice());
            orderItemRepository.save(orderItem);
        }

        return order.getOrderUuid();
    }
}
