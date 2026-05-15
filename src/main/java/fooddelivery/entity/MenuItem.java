package fooddelivery.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;
    private String name;
    private String description;
    private Double price;

    @Column(length = 1000)
    private String imageUrl;
}
