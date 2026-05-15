package fooddelivery.dto;

import fooddelivery.entity.MenuItem;
import lombok.Data;

@Data
public class CartItem {
    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
