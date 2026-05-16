package fooddelivery.repository;

import fooddelivery.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    java.util.List<CustomerOrder> findByCustomerEmail(String email);
}
