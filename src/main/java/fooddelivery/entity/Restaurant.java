package fooddelivery.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double rating;
    private String deliveryTime;
    
    @Column(length = 1000)
    private String imageUrl;
}
