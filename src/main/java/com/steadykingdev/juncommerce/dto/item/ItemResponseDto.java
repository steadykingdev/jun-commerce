package com.steadykingdev.juncommerce.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import com.steadykingdev.juncommerce.entity.item.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class ItemResponseDto {

    private Long id;
    private String brand;
    private String itemName;
    private int itemPrice;

    private int stockQuantity;

    @QueryProjection
    public ItemResponseDto(Long id, String brand, String itemName, int itemPrice, int stockQuantity) {
        this.id = id;
        this.brand = brand;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(item.getId(), item.getBrand(), item.getItemName(), item.getItemPrice(), item.getStockQuantity());
    }
}
