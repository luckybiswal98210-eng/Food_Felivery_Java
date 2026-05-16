package fooddelivery.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderUuid;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String deliveryAddress;
    private Double totalAmount;
    private String status;
    private Date orderDate;
    private String paymentMethod;
    private String location;
}
