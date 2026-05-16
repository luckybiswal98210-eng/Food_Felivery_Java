package fooddelivery.dto;

import lombok.Data;

@Data
public class CheckoutForm {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String paymentMethod;
    private String location;
}
